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

    @Override
    public String getDescription() {
        return "Displays structured data in rows and columns, with support for sorting and badges.";
    }

    @Extension
    public static final class DescriptorImpl extends UISampleDescriptor {}
}
