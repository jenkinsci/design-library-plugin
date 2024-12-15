package io.jenkins.plugins.designlibrary;

import hudson.Extension;

@Extension
public class Menu extends UISample {

    @Override
    public String getIconFileName() {
        return "symbol-dropdown-menu plugin-design-library";
    }

    @Override
    public String getDescription() {
        return "Menus allow you to group similar controls under one roof. They're an effective way to de-clutter your page whilst offering users the actions they need.";
    }

    @Extension
    public static final class DescriptorImpl extends UISampleDescriptor {}
}
