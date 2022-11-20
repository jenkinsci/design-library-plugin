package io.jenkins.plugins.designlibrary;

import hudson.Extension;

@Extension
public class Layouts extends UISample {
    @Override
    public String getDisplayName() {
        return "Layouts";
    }

    @Override
    public String getIconFileName() {
        return "symbol-layouts";
    }

    @Extension
    public static final class DescriptorImpl extends UISampleDescriptor {
    }
}

