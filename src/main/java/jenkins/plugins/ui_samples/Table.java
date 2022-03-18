package jenkins.plugins.ui_samples;

import hudson.Extension;

import java.util.Arrays;
import java.util.List;

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
        return "Shows you how to use the progress bar widget that's used in the executor view and other places";
    }

    @Extension
    public static final class DescriptorImpl extends UISampleDescriptor {
    }
}
