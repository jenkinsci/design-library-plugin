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
        return "Organizes data into sortable rows and columns for easy viewing and comparison.";
    }

    @Extension
    public static final class DescriptorImpl extends UISampleDescriptor {}
}
