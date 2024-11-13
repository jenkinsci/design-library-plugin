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

    @Extension
    public static final class DescriptorImpl extends UISampleDescriptor {}
}
