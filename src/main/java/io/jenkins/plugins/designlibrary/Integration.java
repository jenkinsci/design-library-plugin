package io.jenkins.plugins.designlibrary;

import hudson.Extension;

@Extension
public class Integration extends UISample {

    @Override
    public String getIconFileName() {
        return "symbol-extension-puzzle-outline plugin-ionicons-api";
    }

    @Override
    public String getDescription() {
        return "Defines the palette for consistent use of color.";
    }

    @Override
    public Category getCategory() {
        return Category.PATTERN;
    }

    @Extension
    public static final class DescriptorImpl extends UISampleDescriptor {}
}
