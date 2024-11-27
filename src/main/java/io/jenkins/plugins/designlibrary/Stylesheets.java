package io.jenkins.plugins.designlibrary;

import hudson.Extension;

@Extension
public class Stylesheets extends UISample {
    @Override
    public String getDisplayName() {
        return "Stylesheets";
    }

    @Override
    public String getIconFileName() {
        return "symbol-school-outline plugin-ionicons-api";
    }

    @Override
    public String getDescription() {
        return "Provides reusable design principles and standards for maintaining consistency.";
    }

    @Override
    public Category getCategory() {
        return Category.PATTERN;
    }

    @Extension
    public static final class DescriptorImpl extends UISampleDescriptor {}
}
