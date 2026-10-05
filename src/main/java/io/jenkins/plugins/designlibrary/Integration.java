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
        return "Guidance for integrating your plugin further into Jenkins.";
    }

    @Override
    public Category getCategory() {
        return Category.PATTERN;
    }

    @Override
    public String getSince() {
        return "2.515";
    }

    @Extension
    public static final class DescriptorImpl extends UISampleDescriptor {}
}
