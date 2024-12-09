package io.jenkins.plugins.designlibrary;

import hudson.Extension;

@Extension
public class Dropdowns extends UISample {
    @Override
    public String getIconFileName() {
        return "symbol-dropdowns";
    }

    @Override
    public String getDescription() {
        return "Dropdowns allow you to group similar controls under one roof. They're an effective way to de-clutter your page whilst offering users the actions they need.";
    }

    @Extension
    public static final class DescriptorImpl extends UISampleDescriptor {}
}
