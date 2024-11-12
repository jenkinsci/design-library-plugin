package io.jenkins.plugins.designlibrary;

import hudson.Extension;

@Extension
public class Dropdowns extends UISample {
    @Override
    public String getIconFileName() {
        return "symbol-dropdowns";
    }

    @Extension
    public static final class DescriptorImpl extends UISampleDescriptor {}
}
