package io.perfeccionista.framework.value.processor;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.junit.platform.commons.util.ReflectionUtils;
import org.junit.platform.commons.util.StringUtils;
import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.attachment.Attachment;
import io.perfeccionista.framework.attachment.StringAttachmentEntry;
import io.perfeccionista.framework.datasource.DataConverter;
import io.perfeccionista.framework.datasource.DataSource;
import io.perfeccionista.framework.datasource.NamedDataConverterService;
import io.perfeccionista.framework.datasource.NamedDataSourceService;
import io.perfeccionista.framework.exceptions.ServiceNotFoundException;
import io.perfeccionista.framework.exceptions.StringValueParseException;

import java.lang.reflect.Method;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static io.perfeccionista.framework.exceptions.messages.EnvironmentMessages.DATA_CONVERTER_VALUE_NOT_FOUND;
import static io.perfeccionista.framework.exceptions.messages.EnvironmentMessages.DATA_SOURCE_VALUE_NOT_FOUND;
import static io.perfeccionista.framework.exceptions.messages.EnvironmentMessages.SERVICE_NOT_FOUND;
import static io.perfeccionista.framework.exceptions.messages.EnvironmentMessages.STRING_VALUE_COMPOUND_DATA_CONVERTER_KEY;
import static io.perfeccionista.framework.exceptions.messages.EnvironmentMessages.STRING_VALUE_COMPOUND_DATA_SOURCE_KEY;
import static io.perfeccionista.framework.exceptions.messages.EnvironmentMessages.STRING_VALUE_DATA_CONVERTER_INCORRECT_KEY_TYPE;
import static io.perfeccionista.framework.exceptions.messages.EnvironmentMessages.STRING_VALUE_DATA_CONVERTER_NAME_NOT_FOUND;
import static io.perfeccionista.framework.exceptions.messages.EnvironmentMessages.STRING_VALUE_DATA_SOURCE_INCORRECT_KEY_TYPE;
import static io.perfeccionista.framework.exceptions.messages.EnvironmentMessages.STRING_VALUE_PARSING_FAILED;
import static io.perfeccionista.framework.exceptions.messages.EnvironmentMessages.STRING_VALUE_PROCESSING_FAILED;
import static io.perfeccionista.framework.value.processor.TokenType.DATA_CONVERTER_CLOSED;
import static io.perfeccionista.framework.value.processor.TokenType.DATA_CONVERTER_OPEN;
import static io.perfeccionista.framework.value.processor.TokenType.DATA_SOURCE_CLOSED;
import static io.perfeccionista.framework.value.processor.TokenType.DATA_SOURCE_OPEN;
import static io.perfeccionista.framework.value.processor.TokenType.VALUE;

public class ValueExpressionProcessor {
    protected static final Pattern DATA_SOURCE_DECLARATION_PATTERN = Pattern.compile("^\\[(?<dataSourceDeclaration>.*?)]");
    protected static final Pattern DATA_CONVERTER_DECLARATION_PATTERN = Pattern.compile("^\\[(?<dataConverterDeclaration>.*?)]");
    protected static final Pattern DATA_CONVERTER_FORMAT_PATTERN = Pattern.compile("->(?<dataConverterFormat>.*$?)");

    private final Environment environment;
    private String expression;

    public ValueExpressionProcessor(@NotNull Environment environment) {
        this.environment = environment;
    }

    /**
     * Заменяем ссылки на ключах из источника данных на значения
     * - парсим на токены
     * - если токенов нет то возвращаем исходное выражение
     * - если они были,
     *
     * @param expression обрабатываемое исходное выражение
     * @return обработанное выражения
     */
    public Object processExpression(@NotNull String expression) {
        this.expression = expression;
        return processTokenizedExpression(TokenizedExpression.of(VALUE, tokenize(expression))).getResult();
    }

    /**
     * Разбираем исходное выражение на токены. На выходы мы получим плоский список типизированных токенов
     *
     * @param expression - исходное выражение
     * @return стипизированный список токенов
     */
    Deque<Token> tokenize(String expression) {
        if (expression.length() == 0) {
            return new ArrayDeque<>();
        }

        final Deque<Token> tokens = new ArrayDeque<>();
        final Deque<TokenType> expressionContext = new ArrayDeque<>();

        StringBuilder tokenContent = new StringBuilder();
        int tokenContentStartPosition = 0;

        boolean escaped = false;
        boolean dataSource = false;
        boolean dataConverter = false;

        char[] symbols = expression.toCharArray();

        for (int i = 0; i < symbols.length; i++) {
            char currentChar = symbols[i];
            if (escaped) {
                tokenContent.append(currentChar);
            } else if (currentChar == '\\') {
                escaped = true;
                continue;
            } else if (currentChar == '$') {
                dataSource = true;
                continue;
            } else if (currentChar == '@') {
                dataConverter = true;
                continue;
            } else if (currentChar == '{') {
                if (dataSource) {
                    flushTokenContent(tokens, tokenContent.toString(), tokenContentStartPosition);
                    tokens.addLast(Token.of(DATA_SOURCE_OPEN, i - 1));
                    expressionContext.push(DATA_SOURCE_OPEN);
                    tokenContent = new StringBuilder();
                    tokenContentStartPosition = i + 1;
                } else if (dataConverter) {
                    flushTokenContent(tokens, tokenContent.toString(), tokenContentStartPosition);
                    tokens.addLast(Token.of(DATA_CONVERTER_OPEN, i - 1));
                    expressionContext.push(DATA_CONVERTER_OPEN);
                    tokenContent = new StringBuilder();
                    tokenContentStartPosition = i + 1;
                } else {
                    tokenContent.append(currentChar);
                }
            } else if (currentChar == '}' && DATA_SOURCE_OPEN == expressionContext.peekFirst()) {
                flushTokenContent(tokens, tokenContent.toString(), tokenContentStartPosition);
                tokens.addLast(Token.of(DATA_SOURCE_CLOSED, i));
                expressionContext.pollFirst();
                tokenContent = new StringBuilder();
                tokenContentStartPosition = i + 1;
            } else if (currentChar == '}' && DATA_CONVERTER_OPEN == expressionContext.peekFirst()) {
                flushTokenContent(tokens, tokenContent.toString(), tokenContentStartPosition);
                tokens.addLast(Token.of(DATA_CONVERTER_CLOSED, i));
                expressionContext.pollFirst();
                tokenContent = new StringBuilder();
                tokenContentStartPosition = i + 1;
            } else {
                tokenContent.append(currentChar);
            }
            escaped = false;
            dataSource = false;
            dataConverter = false;
        }

        if (!expressionContext.isEmpty()) {
            throw new StringValueParseException(STRING_VALUE_PARSING_FAILED.getMessage(expression)).setAttachment(Attachment.of(
                    StringAttachmentEntry.of("Token", expressionContext.getFirst().toString())));
        }

        if (tokenContent.length() > 0) {
            tokens.addLast(Token.of(VALUE, tokenContent.toString(), tokenContentStartPosition));
        }

        return tokens;
    }

    private void flushTokenContent(Deque<Token> tokens, String stringContent, int position) {
        if (stringContent.length() > 0) {
            tokens.addLast(Token.of(VALUE, stringContent, position));
        }
    }

    /**
     * Рекурсивный метод для обработки токенизированного аргумента, полученного из метода {@link #tokenize(String)}
     * после разбора исходного выражения.
     * Для разбора содержимого каждого из ключей происходит рекурсивный вызов этого же метода с передачей внутрь оставшихся токенов.
     * Как только аргумент внутри ключа полностью разобран (в процессе обработки одного ключа у нас может возникнуть
     * необходимость обработки вложенных ключей и тогда мы снова будем вызывать рекурсивно этот метод),
     * мы выходим из текущего уровня рекурсии наверх с передачей обработанного токенизированного аргумента.
     *
     * @param tokenizedExpression аргумент, разобранный на токены
     */
    TokenizedExpressionResult processTokenizedExpression(TokenizedExpression tokenizedExpression) {

        final TokenType tokenContext = tokenizedExpression.getTokenContext();
        Deque<Token> rawTokens = tokenizedExpression.getRawTokens();
        String stringContent = "";
        Object objectContent = null;

        boolean processed = false;

        while (!processed) {

            if (rawTokens.isEmpty()) {
                break;
            }

            Token processedToken = rawTokens.pollFirst();
            TokenType tokenType = processedToken.getType();

            if (VALUE == tokenType) {
                if (null != objectContent) {
                    // Если у нас уже есть объект и мы к нему добавляем строку,
                    // то нужно принудительно привести его к строке и добавить конкатенируемую строку
                    stringContent = stringContent.concat(objectContent.toString());
                    objectContent = null;
                }
                stringContent = stringContent.concat(processedToken.getContent());
            } else if ((DATA_SOURCE_CLOSED == tokenType && DATA_SOURCE_OPEN == tokenContext)
                    || (DATA_CONVERTER_CLOSED == tokenType && DATA_CONVERTER_OPEN == tokenContext)) {
                processed = true;
            } else if (DATA_SOURCE_OPEN == tokenType || DATA_CONVERTER_OPEN == tokenType) {
                TokenizedExpressionResult expressionResult = processTokenizedExpression(TokenizedExpression.of(tokenType, rawTokens));
                rawTokens = expressionResult.getRawTokens();
                objectContent = expressionResult.getResult();
            } else {
                throw new StringValueParseException(STRING_VALUE_PROCESSING_FAILED.getMessage(expression)).setAttachment(Attachment.of(
                        StringAttachmentEntry.of("Tokenized expression", tokenizedExpression.toString()),
                        StringAttachmentEntry.of("Token", processedToken.toString())));
            }
        }

        if (DATA_SOURCE_OPEN == tokenContext) {
            return TokenizedExpressionResult.of(tokenContext, rawTokens, resolveDataSourceExpression(stringContent, objectContent));
        } else if (DATA_CONVERTER_OPEN == tokenContext) {
            return TokenizedExpressionResult.of(tokenContext, rawTokens, resolveDataConverterExpression(stringContent, objectContent));
        } else {
            if (null == objectContent) {
                return TokenizedExpressionResult.of(tokenContext, rawTokens, stringContent);
            } else if (stringContent.length() != 0) {
                return TokenizedExpressionResult.of(tokenContext, rawTokens, stringContent.concat((String) objectContent));
            } else {
                return TokenizedExpressionResult.of(tokenContext, rawTokens, objectContent);
            }
        }

    }

    // Process DataSource

    private Object resolveDataSourceExpression(String stringContent, Object objectContent) {
        DataSourceDeclaration dataSourceDeclaration = extractDataSourceDeclaration(stringContent);
        NamedDataSourceService dataSourceService = environment.getService(NamedDataSourceService.class)
                .orElseThrow(() -> new ServiceNotFoundException(SERVICE_NOT_FOUND.getMessage(NamedDataSourceService.class.getCanonicalName())));
        DataSource<?, ?> dataSource;
        String stringKey;
        if (dataSourceDeclaration.getName().isPresent()) {
            dataSource = dataSourceService.get(dataSourceDeclaration.getName().get());
            //noinspection ConstantConditions: Declaration is present if name is present
            stringKey = stringContent.replace(dataSourceDeclaration.getDeclaration(), "").trim();
        } else {
            dataSource = dataSourceService.getDefault();
            stringKey = stringContent.trim();
        }
        if (null != objectContent && StringUtils.isNotBlank(stringKey)) {
            throw new StringValueParseException(STRING_VALUE_COMPOUND_DATA_SOURCE_KEY.getMessage(expression)).setAttachment(Attachment.of(
                    StringAttachmentEntry.of("DataSource", dataSource.getClass().getCanonicalName()),
                    StringAttachmentEntry.of("String key content", stringContent),
                    StringAttachmentEntry.of("Object key content", objectContent.getClass().getCanonicalName())));
        }
        return getDataSourceValue(dataSource, null == objectContent ? stringKey : objectContent);
    }

    // Improvement: Можно ускорить если переписать на парсер char-ов
    private DataSourceDeclaration extractDataSourceDeclaration(String stringContent) {
        Matcher dataSourceDeclarationMatcher = DATA_SOURCE_DECLARATION_PATTERN.matcher(stringContent.trim());
        if (dataSourceDeclarationMatcher.find()) {
            return DataSourceDeclaration.of(dataSourceDeclarationMatcher.group("dataSourceDeclaration"), dataSourceDeclarationMatcher.group());
        }
        return DataSourceDeclaration.defaultDataSource();
    }

    /**
     * Проверяем формат данных для ключа {@link DataSource} и получаем значение
     *
     * @param dataSource {@link DataSource} из которого необходимо получить значение
     * @param key        ключ для обработки
     * @return значение после обработки
     */
    private Object getDataSourceValue(@NotNull DataSource<?, ?> dataSource, @NotNull Object key) {
        Optional<Method> optionalMethod = ReflectionUtils.findMethods(dataSource.getClass(), method -> !method.isBridge()
                && "get".equals(method.getName())
                && method.getParameterCount() == 1
                && method.getParameterTypes()[0].isAssignableFrom(key.getClass())
        ).stream().findFirst();
        if (optionalMethod.isPresent()) {
            Object result = ReflectionUtils.invokeMethod(optionalMethod.get(), dataSource, key);
            if (null == result) {
                throw new StringValueParseException(DATA_SOURCE_VALUE_NOT_FOUND.getMessage(dataSource.getClass().getCanonicalName(), key.toString()));
            }
            return result;
        }
        throw new StringValueParseException(STRING_VALUE_DATA_SOURCE_INCORRECT_KEY_TYPE.getMessage(expression)).setAttachment(Attachment.of(
                StringAttachmentEntry.of("DataSource class", dataSource.getClass().getCanonicalName()),
                StringAttachmentEntry.of("Key class", key.getClass().getCanonicalName())));
    }

    // Process DataConverter

    private Object resolveDataConverterExpression(String stringContent, Object objectContent) {
        DataConverterDeclaration dataConverterDeclaration = extractDataConverterDeclaration(stringContent);
        NamedDataConverterService dataConverterService = environment.getService(NamedDataConverterService.class)
                .orElseThrow(() -> new ServiceNotFoundException(SERVICE_NOT_FOUND.getMessage(NamedDataConverterService.class.getCanonicalName())));
        DataConverter<?, ?> dataConverter = dataConverterService.get(dataConverterDeclaration.getName());
        String stringKey = stringContent.replace(dataConverterDeclaration.getDeclaration(), "").trim();
        if (null != objectContent && StringUtils.isNotBlank(stringKey)) {
            throw new StringValueParseException(STRING_VALUE_COMPOUND_DATA_CONVERTER_KEY.getMessage(expression)).setAttachment(Attachment.of(
                    StringAttachmentEntry.of("DataConverter", dataConverter.getClass().getCanonicalName()),
                    StringAttachmentEntry.of("String key content", stringContent),
                    StringAttachmentEntry.of("Object key content", objectContent.getClass().getCanonicalName())));
        }
        return getDataConverterValue(dataConverter, null == objectContent ? stringKey : objectContent, dataConverterDeclaration.getFormat());
    }

    // Improvement: Можно ускорить если переписать на парсер char-ов
    private DataConverterDeclaration extractDataConverterDeclaration(String stringContent) {
        Matcher dataConverterDeclarationMatcher = DATA_CONVERTER_DECLARATION_PATTERN.matcher(stringContent.trim());
        if (dataConverterDeclarationMatcher.find()) {
            String dataConverterDeclaration = dataConverterDeclarationMatcher.group("dataConverterDeclaration");
            Matcher dataConverterFormatMatcher = DATA_CONVERTER_FORMAT_PATTERN.matcher(dataConverterDeclaration);
            if (dataConverterFormatMatcher.find()) {
                String dataConverterFormat = dataConverterFormatMatcher.group("dataConverterFormat").trim();
                String dataConverterName = dataConverterDeclaration.replace(dataConverterFormatMatcher.group(), "").trim();
                return DataConverterDeclaration.of(dataConverterName, dataConverterFormat, dataConverterDeclarationMatcher.group());
            } else {
                return DataConverterDeclaration.of(dataConverterDeclaration.trim(), dataConverterDeclarationMatcher.group());
            }
        }
        throw new StringValueParseException(STRING_VALUE_DATA_CONVERTER_NAME_NOT_FOUND.getMessage(expression)).setAttachment(Attachment.of(
                StringAttachmentEntry.of("String key content", stringContent)));
    }

    /**
     * Проверяем формат данных для ключа {@link DataConverter} и получаем значение
     *
     * @param dataConverter {@link DataConverter} из которого необходимо получить значение
     * @param key           ключ для обработки
     * @return значение после обработки
     */
    private Object getDataConverterValue(@NotNull DataConverter<?, ?> dataConverter, @NotNull Object key, @Nullable String format) {
        Optional<Method> optionalMethod = ReflectionUtils.findMethods(dataConverter.getClass(), method -> !method.isBridge()
                && "convert".equals(method.getName())
                && method.getParameterCount() == 2
                && method.getParameterTypes()[0].isAssignableFrom(key.getClass())
                && method.getParameterTypes()[1].isAssignableFrom(String.class)
        ).stream().findFirst();
        if (optionalMethod.isPresent()) {
            Object result = ReflectionUtils.invokeMethod(optionalMethod.get(), dataConverter, key, format);
            if (null == result) {
                throw new StringValueParseException(
                        DATA_CONVERTER_VALUE_NOT_FOUND.getMessage(dataConverter.getClass().getCanonicalName(), key.toString(), format));
            }
            return result;
        }
        throw new StringValueParseException(STRING_VALUE_DATA_CONVERTER_INCORRECT_KEY_TYPE.getMessage(expression)).setAttachment(Attachment.of(
                StringAttachmentEntry.of("DataConverter class", dataConverter.getClass().getCanonicalName()),
                StringAttachmentEntry.of("Key class", key.getClass().getCanonicalName())));
    }

    protected static class DataSourceDeclaration {

        private final String name;
        private final String declaration;

        private DataSourceDeclaration(@Nullable String name, @Nullable String declaration) {
            this.name = name;
            this.declaration = declaration;
        }

        public static DataSourceDeclaration of(@NotNull String name, @NotNull String declaration) {
            return new DataSourceDeclaration(name, declaration);
        }

        public static DataSourceDeclaration defaultDataSource() {
            return new DataSourceDeclaration(null, null);
        }

        public Optional<String> getName() {
            return Optional.ofNullable(name);
        }

        public @Nullable String getDeclaration() {
            return declaration;
        }

    }

    protected static class DataConverterDeclaration {

        private final String name;
        private final String format;
        private final String declaration;

        private DataConverterDeclaration(@NotNull String name, @Nullable String format, @NotNull String declaration) {
            this.name = name;
            this.format = format;
            this.declaration = declaration;
        }

        public static DataConverterDeclaration of(@NotNull String name, @NotNull String declaration) {
            return new DataConverterDeclaration(name, null, declaration);
        }

        public static DataConverterDeclaration of(@NotNull String name, @NotNull String format, @NotNull String declaration) {
            return new DataConverterDeclaration(name, format, declaration);
        }

        public @NotNull String getName() {
            return name;
        }

        public @Nullable String getFormat() {
            return format;
        }

        public @NotNull String getDeclaration() {
            return declaration;
        }

    }

}
