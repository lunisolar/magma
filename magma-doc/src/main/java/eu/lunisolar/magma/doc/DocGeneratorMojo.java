/*
 * This file is part of "lunisolar-magma".
 *
 * (C) Copyright 2014-2016 Lunisolar (http://lunisolar.eu/).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package eu.lunisolar.magma.doc;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import javax.annotation.Nonnull;
import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.*;
import java.util.*;

@Mojo(name = "magma-doc", threadSafe = false)
public class DocGeneratorMojo extends AbstractMojo {

    @Parameter(required = true)
    private String sorcePath;

    @Parameter(required = true)
    private String destinationPath;

    @Parameter(required = false)
    private String include = "*/Example*Test.java";

    @Parameter
    private Map<String, String> injectables;

    @Override
    public void execute() throws MojoExecutionException {

        List<Path> files = null;
        try {
            files = getPaths();
        } catch (IOException e) {
            throw new MojoExecutionException("Cannot get the file list.", e);
        }

        if (injectables == null) {
            injectables = new HashMap<>();
        }

        for (Path file : files) {
            try {
                String fileName = file.getFileName().toString();

                injectables.put("_templateFile_", fileName);

                Map<String, String> processedInjectables = processInjectables(injectables);

                new FileProcessor(file, Paths.get(destinationPath).resolve(fileName + ".MD"), processedInjectables).processFile();
            } catch (Exception ex) {
                throw new MojoExecutionException("Cannot process the file: " + file, ex);
            }
        }
    }

    private static Map<String, String> processInjectables(Map<String, String> input) {

        Map<String, String> preocessed;
        int changeCount = 0;

        do {
            changeCount = 0;
            preocessed = new HashMap<>();

            for (Map.Entry<String, String> entryOuter : input.entrySet()) {
                if (preocessed.containsKey(entryOuter.getKey())) {
                    throw new RuntimeException("Injectable map contains already key: " + entryOuter.getKey());
                }

                String value = entryOuter.getValue();

                for (Map.Entry<String, String> entryInner : input.entrySet()) {
                    String replaceMark = String.format("$%s$", entryInner.getKey());
                    if (entryOuter.getValue().contains(replaceMark)) {
                        value = value.replace(replaceMark, entryInner.getValue());
                        changeCount++;
                    }
                }

                preocessed.put(entryOuter.getKey(), value);

            }

            input = preocessed;
        } while (changeCount > 0);

        return preocessed;
    }

    @Nonnull private List<Path> getPaths() throws IOException {
        Path sourcePath = Paths.get(sorcePath);

        final List<Path> files = new ArrayList<>();

        Files.walkFileTree(sourcePath, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                if (!attrs.isDirectory()) {
                    files.add(file);
                }
                return FileVisitResult.CONTINUE;
            }
        });

        return files;
    }

}
