package io.jenkins.plugins.designlibrary;

import hudson.Extension;

@Extension
public class Spacing extends UISample {
    @Override
    public String getIconFileName() {
        return "symbol-filter-outline plugin-ionicons-api";
    }

    @Extension
    public static class DescriptorImpl extends UISampleDescriptor {
    }
}
