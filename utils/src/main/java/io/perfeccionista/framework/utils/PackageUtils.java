package io.perfeccionista.framework.utils;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class PackageUtils {

    private PackageUtils() {
    }

    public static Set<String> validatePackageSet(Set<String> packagesToValidate) {
        Set<String> allPackages = new HashSet<>(packagesToValidate);
        Set<String> duplicates = new HashSet<>();
        for (String currentPackage : allPackages) {
            for (String comparablePackage : allPackages) {
                if (!currentPackage.equals(comparablePackage) && currentPackage.contains(comparablePackage)) {
                    duplicates.add(currentPackage);
                }
            }
        }
        allPackages.removeAll(duplicates);
        return Collections.unmodifiableSet(allPackages);
    }

}
