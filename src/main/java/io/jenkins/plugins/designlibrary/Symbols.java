package io.jenkins.plugins.designlibrary;

import hudson.Extension;

/**
 * @author Kohsuke Kawaguchi
 */
@Extension
public class Symbols extends UISample {
    @Override
    public String getIconFileName() {
        return "symbol-symbols";
    }

    @Extension
    public static final class DescriptorImpl extends UISampleDescriptor {
    }
}

