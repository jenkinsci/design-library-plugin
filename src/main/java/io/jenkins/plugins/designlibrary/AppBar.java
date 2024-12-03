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

    @Override
    public String getDescription() {
        return "Frames your page and contains your most important actions.";
    }

    @Extension
    public static final class DescriptorImpl extends UISampleDescriptor {}
}
