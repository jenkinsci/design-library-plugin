package io.jenkins.plugins.designlibrary;

import hudson.Extension;

@Extension
public class Spacing extends UISample {
    @Override
    public String getIconFileName() {
        return "symbol-spacing";
    }

    @Extension
    public static class DescriptorImpl extends UISampleDescriptor {
    }
}
