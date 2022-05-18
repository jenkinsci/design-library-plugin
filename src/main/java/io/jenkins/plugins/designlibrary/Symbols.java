package io.jenkins.plugins.designlibrary;

import hudson.Extension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Extension
public class Symbols extends UISample {
    @Override
    public String getIconFileName() {
        return "symbol-symbols";
    }

    public List<String> getSymbols() {
        List<String> symbols = Arrays.asList("trash", "search", "rss", "ribbon", "reload", "plugins", "jenkins", "folder");
        Collections.shuffle(symbols);
        return symbols;
    }

    public String getSymbol() {
        return "symbol-" + getSymbols().get(0);
    }

    @Extension
    public static final class DescriptorImpl extends UISampleDescriptor {
    }
}

