package io.perfeccionista.framework.bdd.parameters;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.pagefactory.extractor.list.WebListBlockValueExtractor;

public class WebListValueExtractorParameterImpl implements WebListValueExtractorParameter {

    private final Environment environment;
    private final String rawInput;

    public WebListValueExtractorParameterImpl(Environment environment, String rawInput) {
        this.environment = environment;
        this.rawInput = rawInput;
    }

    @Override
    public WebListBlockValueExtractor<String> createExtractorFor(String blockElementName) {
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
