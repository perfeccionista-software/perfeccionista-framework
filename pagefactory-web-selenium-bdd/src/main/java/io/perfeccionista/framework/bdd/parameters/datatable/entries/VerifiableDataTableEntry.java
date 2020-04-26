package io.perfeccionista.framework.bdd.parameters.datatable.entries;

import java.util.Map;
import java.util.stream.Collectors;

public interface VerifiableDataTableEntry {

    Map<String, String> verify(Map<String, String> entry);

    default String mapToString(Map<String, String> stringMap) {
        return "Entry = {" + stringMap.entrySet().stream()
                .map(entry -> entry.getKey().concat(" = ").concat(entry.getValue()))
                .collect(Collectors.joining("; ")) + '}';
    }

}
