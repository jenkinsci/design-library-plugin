package io.jenkins.plugins.designlibrary;

import edu.umd.cs.findbugs.annotations.NonNull;
import hudson.Extension;

@Extension
public class ToggleSwitch extends UISample {

    @Override
    public @NonNull String getDisplayName() {
        return "Toggle switch";
    }

    @Override
    public String getIconFileName() {
        return "symbol-toggle-outline plugin-ionicons-api";
    }

    @Override
    public String getDescription() {
        return "Enables users to switch between two states, such as on or off.";
    }

    @Extension
    public static final class DescriptorImpl extends UISampleDescriptor {}
}
