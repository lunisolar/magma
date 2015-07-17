/*
 * This file is part of "lunisolar-magma".
 *
 * (C) Copyright 2015 Lunisolar (http://lunisolar.eu/).
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

import javax.annotation.Nonnull;
import java.io.*;
import java.nio.charset.*;
import java.nio.file.*;
import java.util.*;
import java.util.regex.*;

import static java.util.stream.Collectors.*;
import static org.apache.commons.lang3.StringEscapeUtils.escapeHtml4;

/**
 * @author Jakub Wach
 */
public class FileProcessor {

    public static final Charset UTF_8          = Charset.forName("utf8");
    public static final Pattern INJECT         = Pattern.compile("\\s*\\/\\/\\s*>inject<\\:([A-Za-z0-9]+)");
    public static final Pattern TRANSFORM      = Pattern.compile("\\s*\\/\\/\\s*>transform-to-MD<");
    public static final Pattern JAVA_DOC_BEGIN = Pattern.compile("\\s*\\/\\*\\*\\s*");
    public static final Pattern JAVA_DOC_END   = Pattern.compile("\\s*\\*\\/\\s*");
    public static final Pattern EXAMPLE        = Pattern.compile("\\s*\\/\\/\\s*>example<");

    private final Path file;
    private final Path outPath;

    private Map<String, String> injectables;

    private Stack<LineContext> stack = new Stack<>();

    private final ByteArrayOutputStream bout = new ByteArrayOutputStream();
    private final OutputStreamWriter    out  = new OutputStreamWriter(bout);

    private final List<String> lineBuffer = new ArrayList<>(250);

    public FileProcessor(Path inFile, Path outPath, Map<String, String> injectables) {
        this.file = inFile;
        this.outPath = outPath;
        this.injectables = injectables;
    }

    public void processFile() throws IOException {
        BufferedReader reader = Files.newBufferedReader(file, UTF_8);

        enterContext(this::fileRootContext);

        String line;
        while ((line = reader.readLine()) != null) {
            LineContext peek = stack.peek();
            peek.processLine(line);
        }

        out.flush();
        out.close();

        if (bout.size() > 0) {
            try (FileOutputStream newFile = new FileOutputStream(new File(outPath.toString()))) {
                newFile.write(bout.toByteArray());
            }
        }
    }

    private void fileRootContext(String line) {
        if (TRANSFORM.matcher(line).matches()) {
            enterContext(this::rootContext);
        }
    }

    private void rootContext(String line) throws IOException {
        if (JAVA_DOC_BEGIN.matcher(line).matches()) {
            enterContext(this::javaDocComment);
            print("");
            return;
        }

        if (EXAMPLE.matcher(line).matches()) {
            enterContext(this::example);
            print("```Java");
            print("");
            return;
        }

        Matcher matcher = INJECT.matcher(line);
        if (matcher.matches()) {
            String key = matcher.group(1);

            String injectStr = injectables.get(key);

            if (injectStr == null) {
                throw new RuntimeException(String.format("Injectable is missing for key: %s", key));
            }

            print(injectStr);
            return;
        }

        if (line.startsWith("///")) {
            print(line.substring(3));
        }
    }

    private void example(String line) throws IOException {

        if (EXAMPLE.matcher(line).matches()) {
            exit();
            flushBuffer();
            print("```");
            print("");
            return;
        }

        buffer(line);
    }

    private void javaDocComment(String line) throws IOException {
        if (JAVA_DOC_END.matcher(line).matches()) {
            exit();
            print("");
            return;
        }

        print(escapeHtml4(line));
    }

    private void print(String line) {
        try {
            out.append(line.replaceFirst("^\\s*\\*\\s*", ""));
            out.append("\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    void enterContext(LineContext newContext) {
        stack.add(newContext);
    }

    void exit() {
        stack.pop();
    }

    void buffer(String line) {
        //int whitePrefix = numberOfPrefixSpaces(line);
        String str = line; //halfIndent(line, whitePrefix);
        lineBuffer.add(str);
    }

    private String halfIndent(String line, int whitePrefix) {
        return (whitePrefix > 0 && whitePrefix != Integer.MAX_VALUE) ? line.substring(whitePrefix / 2) : line;
    }

    void flushBuffer() {
        final Optional<Integer> charsToCut = lineBuffer.stream().map(this::numberOfPrefixSpaces).min(Integer::compare);

        if (charsToCut.isPresent()) {
            List<String> newLines = lineBuffer.stream().map(l -> cutPrefix(l, charsToCut.get())).collect(toList());
            newLines.forEach(this::print);
        } else {
            lineBuffer.forEach(this::print);
        }

        lineBuffer.clear();

    }

    @Nonnull private String cutPrefix(String l, int size) {
        if (size == Integer.MAX_VALUE) {
            return l;
        }

        if (l.length() > size) {
            return l.substring(size);
        } else {
            return l;
        }
    }

    private int numberOfPrefixSpaces(String line) {

        if (line.length() == 0) {
            return Integer.MAX_VALUE;
        }
        int i = 0;
        while (i < line.length() && Character.isWhitespace(line.charAt(i))) {
            i++;
        }
        return i;
    }

    @FunctionalInterface
    private interface LineContext {

        void processLine(String line) throws IOException;

    }
}
