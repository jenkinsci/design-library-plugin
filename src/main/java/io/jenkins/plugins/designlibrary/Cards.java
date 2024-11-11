package io.jenkins.plugins.designlibrary;

import hudson.Extension;

@Extension
public class Cards extends UISample {

    @Override
    public String getDisplayName() {
        return "Cards";
    }

    @Override
    public String getIconFileName() {
        return "symbol-cards";
    }

    @Extension
    public static final class DescriptorImpl extends UISampleDescriptor {
    }
}

