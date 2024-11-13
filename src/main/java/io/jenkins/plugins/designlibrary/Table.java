package io.jenkins.plugins.designlibrary;

import hudson.Extension;

/**
 * @author Kohsuke Kawaguchi
 */
@Extension
public class Table extends UISample {
    @Override
    public String getIconFileName() {
        return "symbol-table";
    }

    @Extension
    public static final class DescriptorImpl extends UISampleDescriptor {}
}
