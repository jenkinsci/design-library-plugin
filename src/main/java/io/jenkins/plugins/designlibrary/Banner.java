package io.jenkins.plugins.designlibrary;

import hudson.Extension;

@Extension
public class Banner extends UISample {
    @Override
    public String getDisplayName() {
        return "Banner";
    }

    @Override
    public String getIconFileName() {
        return "symbol-information-circle-outline plugin-ionicons-api";
    }

    @Override
    public String getDescription() {
        return "Banners are a handy way to display small snippets of text and actions.";
    }

    @Extension
    public static final class DescriptorImpl extends UISampleDescriptor {}
}
