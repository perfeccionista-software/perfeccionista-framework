package io.perfeccionista.framework.value.string;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.value.ValueDeclaration;
import io.perfeccionista.framework.value.string.checker.StringContainsChecker;
import io.perfeccionista.framework.value.string.checker.StringContainsIgnoreCaseChecker;
import io.perfeccionista.framework.value.string.checker.StringEmptyValueChecker;
import io.perfeccionista.framework.value.string.checker.StringEqualsChecker;
import io.perfeccionista.framework.value.string.checker.StringEqualsIgnoreCaseChecker;
import io.perfeccionista.framework.value.string.checker.StringLengthChecker;
import io.perfeccionista.framework.value.string.checker.StringRegularExpressionChecker;
import io.perfeccionista.framework.value.string.transformer.ApostrophesToQuotationMarksTransformer;
import io.perfeccionista.framework.value.string.transformer.RemoveLineBreaksTransformer;
import io.perfeccionista.framework.value.string.transformer.RemoveSpacesTransformer;
import io.perfeccionista.framework.value.string.transformer.RemoveTabsTransformer;
import io.perfeccionista.framework.value.string.transformer.ToLowerCaseTransformer;
import io.perfeccionista.framework.value.string.transformer.ToUpperCaseTransformer;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RuStringValue extends DefaultStringValue {

    public RuStringValue(StringChecker stringChecker) {
        super(stringChecker);
    }

    //    Pattern CONDITIONS_PATTERN = Pattern.compile("^\\((?<conditions>.*?)\\)");
//
//
//
////    protected AbstractStringValue(Environment environment, String expected) {
////        this.environment = environment;
////        this.rawExpected = expected;
////        this.valueDeclaration = parseRawExpected(expected);
////        this.stringChecker = resolveChecker(valueDeclaration);
////        this.expectedValueTransformers.addAll(resolveExpectedValueTransformers(valueDeclaration));
////        this.actualValueTransformers.addAll(resolveActualValueTransformers(valueDeclaration));
////    }
//
//
//
//    //        this.environment = environment;
////        this.rawExpected = expected;
////        this.valueDeclaration = parseRawExpected(expected);
//
//
//    // TODO: На эту логику нужны тесты для каждого кейса
//    public @NotNull ValueDeclaration parseRawExpected(String rawExpected) {
//        Matcher conditionsMatcher = CONDITIONS_PATTERN.matcher(rawExpected);
//        if (conditionsMatcher.find()) {
//            return ValueDeclaration.of(
//                    rawExpected.replaceFirst(CONDITIONS_PATTERN.pattern(), ""),
//                    conditionsMatcher.group("conditions"),
//                    conditionsMatcher.group()
//            );
//        } else {
//            if ("\\".equals(rawExpected.substring(0, 1))) {
//                return ValueDeclaration.of(rawExpected.substring(1));
//            } else {
//                return ValueDeclaration.of(rawExpected);
//            }
//        }
//    }
//
//
//
//    //    protected abstract @NotNull StringChecker resolveChecker(ValueDeclaration valueDeclaration);
////
////    protected abstract Deque<UnaryOperator<String>> resolveExpectedValueTransformers(ValueDeclaration valueDeclaration);
////
////    protected abstract Deque<UnaryOperator<String>> resolveActualValueTransformers(ValueDeclaration valueDeclaration);
//
//    private RuStringValue(Environment environment, String rawExpected) {
//        super(environment, rawExpected);
//    }
//
//    public static RuStringValue of(@NotNull Environment environment, @NotNull String rawExpected) {
//        return new RuStringValue(environment, rawExpected);
//    }
//
//    // TODO: На эту логику нужны тесты для каждого кейса
//    @Override
//    protected @NotNull StringChecker resolveChecker(ValueDeclaration valueDeclaration) {
//        if (valueDeclaration.getValueCondition().isEmpty()) {
//            return new StringEqualsChecker();
//        } else if (valueDeclaration.getValueCondition().get().contains("подстрока без учета регистра")
//                || valueDeclaration.getValueCondition().get().contains("подстроку без учета регистра")) {
//            return new StringContainsIgnoreCaseChecker();
//        } else if (valueDeclaration.getValueCondition().get().contains("подстрок")) {
//            return new StringContainsChecker();
//        } else if (valueDeclaration.getValueCondition().get().contains("текст без учета регистра")) {
//            return new StringEqualsIgnoreCaseChecker();
//        } else if (valueDeclaration.getValueCondition().get().contains("текст")) {
//            return new StringEqualsChecker();
//        } else if (valueDeclaration.getValueCondition().get().contains("пуст")) {
//            return new StringEmptyValueChecker();
//        } else if (valueDeclaration.getValueCondition().get().contains("длина строки")) {
//            return new StringLengthChecker();
//        } else if (valueDeclaration.getValueCondition().get().contains("регулярное выражение")) {
//            return new StringRegularExpressionChecker();
//        } else if (valueDeclaration.getValueCondition().get().contains("символ")) {
//            return new StringSymbolsChecker();
//        }
//        return new StringEqualsChecker();
//    }
//
//    // TODO: На эту логику нужны тесты для каждого кейса
//    // Improvement: Добавить учет порядка объявления трансформеров по позиции вхождения
//    @Override
//    protected Deque<StringTransformer> resolveTransformers(ValueDeclaration valueDeclaration) {
//        Deque<StringTransformer> transformers = new ArrayDeque<>();
//        transformers.add(new ApostrophesToQuotationMarksTransformer());
//        if (valueDeclaration.getValueCondition().isEmpty()) {
//            return transformers;
//        }
//        if (valueDeclaration.getValueCondition().get().contains("без переносов строк")) {
//            transformers.add(new RemoveLineBreaksTransformer());
//        }
//        if (valueDeclaration.getValueCondition().get().contains("без табуляции")) {
//            transformers.add(new RemoveTabsTransformer());
//        }
//        if (valueDeclaration.getValueCondition().get().contains("без пробелов")) {
//            transformers.add(new RemoveSpacesTransformer());
//        }
//        if (valueDeclaration.getValueCondition().get().contains("в верхнем регистре")) {
//            transformers.add(new ToUpperCaseTransformer());
//        }
//        if (valueDeclaration.getValueCondition().get().contains("в нижнем регистре")) {
//            transformers.add(new ToLowerCaseTransformer());
//        }
//        return transformers;
//    }

}




