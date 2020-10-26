package io.perfeccionista.framework.bdd.parameters;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.pagefactory.extractor.table.WebTableValueExtractor;

public class WebTableValueExtractorParameterImpl implements WebTableValueExtractorParameter {

    private final Environment environment;
    private final String rawInput;

    public WebTableValueExtractorParameterImpl(Environment environment, String rawInput) {
        this.environment = environment;
        this.rawInput = rawInput;
    }

    @Override
    public WebTableValueExtractor<String> createExtractorFor(String webTableColumnName, String webCellElementName) {
        // TODO: Тут мы откуда-то получаем распорсенный по тексту rawInput соответствующий велью экстрактор холдер и создаем из него велью экстрактор
        // WebListBlockValueExtractorHolder holder = bddEngineService.getByName(rawInput);
        // return holder.getForElement(blockElementName);
        return null;
    }

    @Override
    public String getRaw() {
        return rawInput;
    }

}
