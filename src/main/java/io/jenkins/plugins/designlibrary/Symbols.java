package io.jenkins.plugins.designlibrary;

import hudson.Extension;
import java.util.Arrays;
import java.util.Collections;
import java.util.Stack;

@Extension
public class Symbols extends UISample {
    @Override
    public String getIconFileName() {
        return "symbol-star-outline plugin-ionicons-api";
    }

    @Override
    public String getDescription() {
        return "Jenkins Symbols are an extensive and consistent collection of icons for use in Jenkins and plugins.";
    }

    public Stack<String> getSymbols() {
        Stack<String> symbols = new Stack<>();
        symbols.addAll(Arrays.asList(
                "trash",
                "search",
                "rss",
                "ribbon",
                "reload",
                "plugins",
                "jenkins",
                "folder",
                "play",
                "fingerprint",
                "details",
                "people",
                "terminal",
                "edit",
                "cloud",
                "analytics",
                "computer",
                "download",
                "hammer",
                "key"));
        Collections.shuffle(symbols);
        return symbols;
    }

    @Extension
    public static final class DescriptorImpl extends UISampleDescriptor {}
}
