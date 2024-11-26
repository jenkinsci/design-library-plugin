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
        return "App bars provide the page heading as well as important actions for the current page.";
    }

    @Extension
    public static final class DescriptorImpl extends UISampleDescriptor {}
}
