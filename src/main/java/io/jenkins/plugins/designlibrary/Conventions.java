package io.jenkins.plugins.designlibrary;

import hudson.Extension;

@Extension
public class Conventions extends UISample {
    @Override
    public String getIconFileName() {
        return "symbol-school-outline plugin-ionicons-api";
    }

    @Override
    public String getDescription() {
        return "Provides reusable design principles and standards for maintaining consistency.";
    }

    @Extension
    public static final class DescriptorImpl extends UISampleDescriptor {}
}
