package io.jenkins.plugins.designlibrary;

import hudson.Extension;

@Extension
public class Spacing extends UISample {
    @Override
    public String getIconFileName() {
        return "symbol-filter-outline plugin-ionicons-api";
    }

    @Override
    public String getDescription() {
        return "Defines consistent padding, margins, and gaps between elements.";
    }

    @Override
    public Category getCategory() {
        return Category.PATTERN;
    }

    @Extension
    public static class DescriptorImpl extends UISampleDescriptor {}
}
