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
        return "Displays informative messages when no data is available.";
    }

    @Extension
    public static final class DescriptorImpl extends UISampleDescriptor {}
}
