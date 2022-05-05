package io.jenkins.plugins.designlibrary;

import hudson.Extension;

import java.util.Arrays;
import java.util.List;

@Extension
public class Colors extends UISample {
    @Override
    public String getIconFileName() {
        return "symbol-colors";
    }

    public List<String> getColors() {
         return Arrays.asList("red", "green", "orange", "yellow", "blue", "indigo", "violet", "brown");
    }

    @Extension
    public static final class DescriptorImpl extends UISampleDescriptor {
    }
}

