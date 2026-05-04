package io.jenkins.plugins.designlibrary;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.WriteListener;
import jakarta.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import org.kohsuke.accmod.Restricted;
import org.kohsuke.accmod.restrictions.NoExternalUse;
import org.kohsuke.stapler.ResponseImpl;
import org.kohsuke.stapler.Stapler;
import org.kohsuke.stapler.StaplerRequest2;

@Restricted(NoExternalUse.class)
class LlmContent {

    static String generateIndex(String baseUrl) {
        StringBuilder sb = new StringBuilder();
        sb.append("# Jenkins Design Library\n\n");
        sb.append("> A reference library of UI components and patterns for building Jenkins plugin interfaces.\n\n");
        sb.append("For complete documentation of all components with code examples, see [llms-all.txt](");
        sb.append(baseUrl).append("llms-all.txt).\n\n");

        for (Map.Entry<Category, List<UISample>> entry : UISample.getGrouped().entrySet()) {
            sb.append("## ").append(entry.getKey().getDisplayName()).append('\n');
            for (UISample sample : entry.getValue()) {
                sb.append("- [").append(sample.getDisplayName()).append("](");
                sb.append(baseUrl).append(sample.getUrlName()).append(".md): ");
                sb.append(sample.getDescription()).append('\n');
            }
            sb.append('\n');
        }

        return sb.toString();
    }

    static String generateAll() {
        StringBuilder sb = new StringBuilder();
        sb.append("# Jenkins Design Library\n\n");
        sb.append("> A reference library of UI components and patterns for building Jenkins plugin interfaces.\n\n");

        for (Map.Entry<Category, List<UISample>> entry : UISample.getGrouped().entrySet()) {
            sb.append("## ").append(entry.getKey().getDisplayName()).append("\n\n");
            for (UISample sample : entry.getValue()) {
                sb.append(generateComponentMarkdown(sample));
                sb.append("\n---\n\n");
            }
        }

        return sb.toString();
    }

    static String generateComponentMarkdown(UISample sample) {
        StaplerRequest2 currentRequest = Stapler.getCurrentRequest2();
        HttpServletResponse currentResponse = Stapler.getCurrentResponse2();
        if (currentRequest == null || currentResponse == null) {
            throw new IllegalStateException("No active Stapler request/response available for markdown rendering");
        }

        RequestDispatcher view;
        try {
            view = currentRequest.getView(sample, "index.jelly");
        } catch (IOException e) {
            throw new IllegalStateException("Failed to resolve index.jelly for " + sample.getDisplayName(), e);
        }
        if (view == null) {
            throw new IllegalStateException("Could not resolve index.jelly for " + sample.getDisplayName());
        }

        CapturingResponse response = new CapturingResponse(currentRequest, currentResponse);
        Object previousMarkdownView = currentRequest.getAttribute(UISample.MARKDOWN_VIEW_ATTRIBUTE);
        currentRequest.setAttribute(UISample.MARKDOWN_VIEW_ATTRIBUTE, true);

        try {
            view.forward(currentRequest, response);
        } catch (ServletException | IOException e) {
            throw new IllegalStateException("Failed to render markdown for " + sample.getDisplayName(), e);
        } finally {
            if (previousMarkdownView != null) {
                currentRequest.setAttribute(UISample.MARKDOWN_VIEW_ATTRIBUTE, previousMarkdownView);
            } else {
                currentRequest.removeAttribute(UISample.MARKDOWN_VIEW_ATTRIBUTE);
            }
        }

        return response.getBody();
    }

    private static final class CapturingResponse extends ResponseImpl {
        private final ByteArrayOutputStream outputBuffer = new ByteArrayOutputStream();
        private final PrintWriter writer =
                new PrintWriter(new OutputStreamWriter(outputBuffer, StandardCharsets.UTF_8), true);
        private final ServletOutputStream outputStream = new StringServletOutputStream(outputBuffer);
        private String contentType = "text/markdown;charset=UTF-8";
        private String characterEncoding = StandardCharsets.UTF_8.name();

        CapturingResponse(StaplerRequest2 request, HttpServletResponse response) {
            super(request.getStapler(), response);
        }

        @Override
        public ServletOutputStream getOutputStream() {
            return outputStream;
        }

        @Override
        public PrintWriter getWriter() {
            return writer;
        }

        @Override
        public void setContentType(String type) {
            contentType = type;
        }

        @Override
        public String getContentType() {
            return contentType;
        }

        @Override
        public void setCharacterEncoding(String charset) {
            characterEncoding = charset;
        }

        @Override
        public String getCharacterEncoding() {
            return characterEncoding;
        }

        String getBody() {
            writer.flush();
            return outputBuffer.toString(StandardCharsets.UTF_8);
        }
    }

    private static final class StringServletOutputStream extends ServletOutputStream {
        private final ByteArrayOutputStream output;

        StringServletOutputStream(ByteArrayOutputStream output) {
            this.output = output;
        }

        @Override
        public void write(int b) {
            output.write(b);
        }

        @Override
        public void flush() {
            // Nothing to flush for an in-memory byte buffer.
        }

        @Override
        public void close() {
            // The buffer stays readable after the render completes.
        }

        @Override
        public boolean isReady() {
            return true;
        }

        @Override
        public void setWriteListener(WriteListener writeListener) {
            // No async IO support needed for in-memory capture.
        }
    }
}
