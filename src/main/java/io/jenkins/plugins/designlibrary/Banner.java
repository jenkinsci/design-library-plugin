package io.jenkins.plugins.designlibrary;

import hudson.Extension;

@Extension
public class Banner extends UISample {
    @Override
    public String getDisplayName() {
        return "Alert";
    }

    @Override
    public String getIconFileName() {
        return "symbol-warning-outline plugin-ionicons-api";
    }

    @Extension
    public static final class DescriptorImpl extends UISampleDescriptor {
    }
}

