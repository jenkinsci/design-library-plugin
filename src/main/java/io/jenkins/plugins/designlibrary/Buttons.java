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

    @Extension
    public static final class DescriptorImpl extends UISampleDescriptor {}
}
