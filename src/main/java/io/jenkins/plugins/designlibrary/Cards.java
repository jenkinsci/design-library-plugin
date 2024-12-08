package io.jenkins.plugins.designlibrary;

import hudson.Extension;

@Extension
public class Cards extends UISample {

    @Override
    public String getIconFileName() {
        return "symbol-cards";
    }

    @Override
    public String getDescription() {
        return "Use cards to surface related information and controls to users.";
    }

    @Extension
    public static final class DescriptorImpl extends UISampleDescriptor {}
}
