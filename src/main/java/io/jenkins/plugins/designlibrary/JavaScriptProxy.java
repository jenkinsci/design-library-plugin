package io.jenkins.plugins.designlibrary;

import edu.umd.cs.findbugs.annotations.NonNull;
import hudson.Extension;
import org.kohsuke.stapler.bind.JavaScriptMethod;

/**
 * "Export" Java objects to JavaScript in the browser as a proxy object, so that
 * you can make ajax-calls to the server later.
 *
 * @author Kohsuke Kawaguchi
 */
@Extension
public class JavaScriptProxy extends UISample {
    private int i;

    @Override
    public String getIconFileName() {
        return "symbol-logo-javascript plugin-ionicons-api";
    }

    @Override
    public @NonNull String getDisplayName() {
        return "JavaScript Proxy";
    }

    @Override
    public String getDescription() {
        return "Captures user input through various text or data entry formats.";
    }

    @Override
    public Category getCategory() {
        return Category.PATTERN;
    }

    /**
     * The annotation exposes this method to JavaScript proxy.
     */
    @JavaScriptMethod
    public int increment(int n) {
        return i += n;
    }

    @Extension
    public static final class DescriptorImpl extends UISampleDescriptor {}
}
