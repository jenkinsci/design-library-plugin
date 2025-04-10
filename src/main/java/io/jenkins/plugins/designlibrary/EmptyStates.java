package io.jenkins.plugins.designlibrary;

import edu.umd.cs.findbugs.annotations.NonNull;
import hudson.Extension;

@Extension
public class EmptyStates extends UISample {

    @Override
    public @NonNull String getDisplayName() {
        return "Empty States";
    }

    @Override
    public String getIconFileName() {
        return "symbol-trash-bin-outline plugin-ionicons-api";
    }

    @Override
    public String getDescription() {
        return "For when there is no data, no configuration or no plugins installed that support the required feature an empty state can be provided instead.";
    }

    @Extension
    public static final class DescriptorImpl extends UISampleDescriptor {}
}
