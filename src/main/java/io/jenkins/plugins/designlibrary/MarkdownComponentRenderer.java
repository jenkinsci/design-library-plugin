package io.jenkins.plugins.designlibrary;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Renders shared Design Library sample components for markdown output.
 */
public final class MarkdownComponentRenderer {

    private static final Pattern DOS_DONTS_ROW_PATTERN =
            Pattern.compile("<tr\\b[^>]*>(.*?)</tr>", Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
    private static final Pattern DOS_DONTS_CELL_PATTERN =
            Pattern.compile("<t[dh]\\b[^>]*>(.*?)</t[dh]>", Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
    private static final Pattern HTML_TAG_PATTERN = Pattern.compile("<[^>]+>", Pattern.DOTALL);
    private static final Pattern NUMERIC_ENTITY_PATTERN = Pattern.compile("&#(x?)([0-9a-fA-F]+);");

    public String layout(String displayName, String description, String category, String since, String body) {
        StringBuilder markdown = new StringBuilder();
        appendBlock(markdown, displayName == null || displayName.isBlank() ? null : "# " + displayName);
        appendBlock(markdown, quote(description));
        appendBlock(markdown, metadata(category, since));
        appendBlock(markdown, normalizeBlock(body));
        return markdown.toString();
    }

    public String section(String title, String description, String body) {
        StringBuilder markdown = new StringBuilder();
        appendBlock(markdown, title == null || title.isBlank() ? null : "## " + normalizeInline(title));
        appendBlock(markdown, description);
        appendBlock(markdown, normalizeBlock(body));
        return block(markdown.toString());
    }

    public String group(String body) {
        return block(normalizeBlock(body));
    }

    public String code(String language, String code) {
        String normalizedLanguage = language == null || language.isBlank() ? "xml" : language;
        String normalizedCode = normalizeCode(code);
        return block("```" + normalizedLanguage + "\n" + normalizedCode + "\n```");
    }

    public String codePanes(String body) {
        return block(normalizeBlock(body));
    }

    public String codePane(String title, String body) {
        StringBuilder markdown = new StringBuilder();
        appendBlock(markdown, title == null || title.isBlank() ? null : "### " + normalizeInline(title));
        appendBlock(markdown, normalizeBlock(body));
        return block(markdown.toString());
    }

    public String paragraph(String body) {
        String normalizedBody = normalizeInline(body);
        return normalizedBody.isEmpty() ? "" : block(normalizedBody);
    }

    public String list(String body) {
        return block(normalizeBlock(body));
    }

    public String listItem(String body) {
        String normalizedBody = normalizeInline(body);
        return normalizedBody.isEmpty() ? "" : "- " + normalizedBody + "\n";
    }

    public String dosDonts(String title, String doLabel, String dontLabel, String body) {
        DosDontsContent content = dosDontsContent(body);
        if (content.isEmpty()) {
            return "";
        }

        StringBuilder markdown = new StringBuilder();
        appendBlock(markdown, title == null || title.isBlank() ? null : "### " + normalizeInline(title));
        appendBlock(markdown, markdownListBlock(doLabel, content.getDos()));
        appendBlock(markdown, markdownListBlock(dontLabel, content.getDonts()));
        return block(markdown.toString());
    }

    public DosDontsContent dosDontsContent(String body) {
        return parseDosDonts(body);
    }

    public String colorsSemanticTable(List<Colors.Semantic> semantics) {
        List<String[]> rows = new ArrayList<>();
        for (Colors.Semantic semantic : semantics) {
            rows.add(new String[] {
                semantic.getName(),
                semantic.getDescription(),
                "`jenkins-!-" + semantic.getVariable() + "`",
                "`var(--" + semantic.getVariable() + ")`"
            });
        }
        return markdownTable(new String[] {"Name", "Description", "CSS Class", "CSS Variable"}, rows);
    }

    public String colorsPaletteTable(List<Colors.Color> colors) {
        List<String[]> rows = new ArrayList<>();
        for (Colors.Color color : colors) {
            rows.add(new String[] {
                color.getName(), "`jenkins-!-" + color.getVariable() + "`", "`var(--" + color.getClassName() + ")`"
            });
        }
        return markdownTable(new String[] {"Name", "CSS Class", "CSS Variable"}, rows);
    }

    private String quote(String text) {
        String normalized = normalizeInline(text);
        return normalized.isEmpty() ? "" : "> " + normalized;
    }

    private String metadata(String category, String since) {
        StringBuilder metadata = new StringBuilder();
        String normalizedCategory = normalizeInline(category);
        String normalizedSince = normalizeInline(since);
        if (!normalizedCategory.isEmpty()) {
            metadata.append("**Category:** ").append(normalizedCategory);
        }
        if (!normalizedSince.isEmpty()) {
            if (!metadata.isEmpty()) {
                metadata.append('\n');
            }
            metadata.append("**Since:** ").append(normalizedSince);
        }
        return metadata.toString();
    }

    private String block(String text) {
        if (text == null || text.isBlank()) {
            return "";
        }
        return text.stripTrailing() + "\n\n";
    }

    private void appendBlock(StringBuilder markdown, String block) {
        if (block == null || block.isBlank()) {
            return;
        }
        if (!markdown.isEmpty()) {
            markdown.append('\n').append('\n');
        }
        markdown.append(block.stripTrailing());
    }

    private String normalizeCode(String text) {
        String normalized = normalizeNewlines(text);
        return normalized.replaceFirst("\\n+$", "");
    }

    private String normalizeInline(String text) {
        String normalized = normalizeNewlines(text);
        normalized = normalized.replaceAll("(?m)^[ \\t]+", "");
        normalized = normalized.replaceAll("[ \\t]*\\n[ \\t]*", " ");
        normalized = normalized.replaceAll(" {2,}", " ");
        return normalized.trim();
    }

    private String markdownListBlock(String label, List<String> items) {
        if (items.isEmpty()) {
            return "";
        }

        StringBuilder markdown = new StringBuilder();
        markdown.append("**").append(normalizeInline(label)).append("**\n");
        for (String item : items) {
            String normalizedItem = normalizeInline(stripHtml(item));
            if (normalizedItem.isEmpty()) {
                continue;
            }
            markdown.append("- ").append(normalizedItem).append('\n');
        }
        return markdown.toString().stripTrailing();
    }

    private String markdownTable(String[] headers, List<String[]> rows) {
        StringBuilder markdown = new StringBuilder();
        markdown.append("| ");
        markdown.append(String.join(" | ", headers));
        markdown.append(" |\n|");
        for (int i = 0; i < headers.length; i++) {
            markdown.append("---|");
        }
        markdown.append('\n');
        for (String[] row : rows) {
            markdown.append("| ");
            for (int i = 0; i < headers.length; i++) {
                if (i > 0) {
                    markdown.append(" | ");
                }
                String cell = i < row.length ? row[i] : "";
                markdown.append(escapeMarkdownTableCell(cell));
            }
            markdown.append(" |\n");
        }
        return markdown.toString().stripTrailing();
    }

    private String normalizeBlock(String text) {
        String normalized = normalizeNewlines(text);
        normalized = normalized.replaceAll("(?m)^[ \\t]+$", "");
        normalized = normalized.replaceAll("(?m)^[ \\t]+(?=(?:#{1,6}\\s|```|> |- |\\d+\\. ))", "");
        normalized = normalized.replaceFirst("^(?:\\n)+", "");
        return normalized.replaceFirst("(?:\\n)+$", "");
    }

    private String escapeMarkdownTableCell(String text) {
        return normalizeInline(text).replace("|", "\\|");
    }

    private DosDontsContent parseDosDonts(String body) {
        DosDontsContent content = new DosDontsContent();
        Matcher rowMatcher = DOS_DONTS_ROW_PATTERN.matcher(normalizeNewlines(body));
        while (rowMatcher.find()) {
            Matcher cellMatcher = DOS_DONTS_CELL_PATTERN.matcher(rowMatcher.group(1));
            List<String> cells = new ArrayList<>();
            while (cellMatcher.find()) {
                String cell = cellMatcher.group(1).trim();
                if (!cell.isEmpty()) {
                    cells.add(cell);
                }
            }
            if (!cells.isEmpty()) {
                content.dos.add(cells.get(0));
            }
            if (cells.size() > 1) {
                content.donts.add(cells.get(1));
            }
        }
        return content;
    }

    private String stripHtml(String text) {
        String normalized = normalizeNewlines(text);
        normalized = normalized.replaceAll("(?i)<br\\s*/?>", " ");
        normalized = HTML_TAG_PATTERN.matcher(normalized).replaceAll(" ");
        normalized = decodeNumericEntities(normalized);
        normalized = normalized
                .replace("&nbsp;", " ")
                .replace("&lt;", "<")
                .replace("&gt;", ">")
                .replace("&amp;", "&")
                .replace("&quot;", "\"")
                .replace("&apos;", "'");
        normalized = normalized.replaceAll("\\s+", " ");
        return normalized.trim();
    }

    private String decodeNumericEntities(String text) {
        Matcher matcher = NUMERIC_ENTITY_PATTERN.matcher(text);
        StringBuffer decoded = new StringBuffer();
        while (matcher.find()) {
            int codePoint = matcher.group(1).isEmpty()
                    ? Integer.parseInt(matcher.group(2))
                    : Integer.parseInt(matcher.group(2), 16);
            matcher.appendReplacement(decoded, Matcher.quoteReplacement(new String(Character.toChars(codePoint))));
        }
        matcher.appendTail(decoded);
        return decoded.toString();
    }

    private String normalizeNewlines(String text) {
        if (text == null) {
            return "";
        }
        return text.replace("\r\n", "\n").replace('\r', '\n');
    }

    public static final class DosDontsContent {
        private final List<String> dos = new ArrayList<>();
        private final List<String> donts = new ArrayList<>();

        public List<String> getDos() {
            return dos;
        }

        public List<String> getDonts() {
            return donts;
        }

        public boolean isEmpty() {
            return dos.isEmpty() && donts.isEmpty();
        }
    }
}
