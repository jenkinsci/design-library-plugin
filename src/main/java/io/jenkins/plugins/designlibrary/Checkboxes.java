package io.jenkins.plugins.designlibrary;

import hudson.Extension;

/**
 * @author Kohsuke Kawaguchi
 */
@Extension
public class Checkboxes extends UISample {
    @Override
    public String getIconFileName() {
        return "symbol-checkbox-outline plugin-ionicons-api";
    }

    @Override
    public String getDescription() {
        return "Allows users to select one or more options from a list.";
    }

    @Extension
    public static final class DescriptorImpl extends UISampleDescriptor {}
}
