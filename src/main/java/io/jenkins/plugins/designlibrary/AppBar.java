package io.jenkins.plugins.designlibrary;

import hudson.Extension;

@Extension
public class AppBar extends UISample {
    @Override
    public String getDisplayName() {
        return "App bars";
    }

    @Override
    public String getIconFileName() {
        return "symbol-app-bar";
    }

    @Extension
    public static final class DescriptorImpl extends UISampleDescriptor {
    }
}

