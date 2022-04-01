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
        return "symbol-toggle-switch";
    }

    @Extension
    public static final class DescriptorImpl extends UISampleDescriptor {
    }
}

