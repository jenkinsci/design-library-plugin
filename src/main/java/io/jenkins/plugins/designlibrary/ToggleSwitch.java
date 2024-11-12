package io.jenkins.plugins.designlibrary;

import hudson.Extension;

@Extension
public class ToggleSwitch extends UISample {
    @Override
    public String getDisplayName() {
        return "Toggle switch";
    }

    @Override
    public String getIconFileName() {
        return "symbol-toggle-outline plugin-ionicons-api";
    }

    @Extension
    public static final class DescriptorImpl extends UISampleDescriptor {}
}
