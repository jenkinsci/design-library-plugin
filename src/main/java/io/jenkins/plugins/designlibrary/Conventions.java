package io.jenkins.plugins.designlibrary;

import hudson.Extension;

@Extension
public class Conventions extends UISample {
    @Override
    public String getIconFileName() {
        return "symbol-school-outline plugin-ionicons-api";
    }

    @Extension
    public static final class DescriptorImpl extends UISampleDescriptor {
    }
}

