package io.jenkins.plugins.designlibrary;

import hudson.Extension;

/**
 * @author Kohsuke Kawaguchi
 */
@Extension
public class Checkboxes extends UISample {
    @Override
    public String getIconFileName() {
        return "symbol-checkboxes";
    }

    @Extension
    public static final class DescriptorImpl extends UISampleDescriptor {
    }
}

