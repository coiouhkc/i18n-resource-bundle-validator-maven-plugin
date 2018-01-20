package org.abratuhi.i18n;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.IteratorUtils;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class I18nPropertiesValidator {

    public boolean isConsistent(String basenameRegex) throws IOException {
        String baseDir = basenameRegex.substring(0, basenameRegex.lastIndexOf('/'));
        String baseFileRegex = basenameRegex.substring(basenameRegex.lastIndexOf('/') + 1);

        Set<String> mergedKeys = Files.list(Paths.get(baseDir))
                .filter(path -> path.getFileName().toFile().getName().matches(baseFileRegex))
                .map(Path::toFile)
                .map(file -> {
                    try {
                        return new PropertiesConfiguration(file);
                    } catch (ConfigurationException e) {
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .map(propertiesConfiguration -> IteratorUtils.toList(propertiesConfiguration.getKeys()))
                .flatMap(Collection::stream)
                .collect(Collectors.toSet());

        return Files.list(Paths.get(baseDir))
                .filter(path -> path.getFileName().toFile().getName().matches(baseFileRegex))
                .map(Path::toFile)
                .map(file -> {
                    try {
                        return new PropertiesConfiguration(file);
                    } catch (ConfigurationException e) {
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .map(propertiesConfiguration -> CollectionUtils.containsAll(IteratorUtils.toList(propertiesConfiguration.getKeys()), mergedKeys))
                .reduce(true, (aBoolean, aBoolean2) -> aBoolean & aBoolean2);
    }
}
