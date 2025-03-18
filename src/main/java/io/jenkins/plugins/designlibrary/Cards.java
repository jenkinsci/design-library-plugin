package io.jenkins.plugins.designlibrary;

import hudson.Extension;

@Extension
public class Cards extends UISample {

    @Override
    public String getIconFileName() {
        return "symbol-cards plugin-design-library";
    }

    @Override
    public String getDescription() {
        return "Use cards to surface related information and controls to users.";
    }

    @Override
    public String getSince() {
        return "2.479.1";
    }

    @Extension
    public static final class DescriptorImpl extends UISampleDescriptor {}
}
