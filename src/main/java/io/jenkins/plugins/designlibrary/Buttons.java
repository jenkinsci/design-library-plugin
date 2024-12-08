package io.jenkins.plugins.designlibrary;

import hudson.Extension;

/**
 * @author Kohsuke Kawaguchi
 */
@Extension
public class Buttons extends UISample {
    @Override
    public String getIconFileName() {
        return "symbol-buttons";
    }

    @Override
    public String getDescription() {
        return "Triggers specific actions or commands with a click or tap.";
    }

    @Extension
    public static final class DescriptorImpl extends UISampleDescriptor {}
}
