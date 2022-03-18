package jenkins.plugins.ui_samples;

import hudson.Extension;

/**
 * @author Kohsuke Kawaguchi
 */
@Extension
public class Progress extends UISample {
    @Override
    public String getIconFileName() {
        return "symbol-progress";
    }

    @Override
    public String getDescription() {
        return "Shows you how to use the progress bar widget that's used in the executor view and other places";
    }

    @Extension
    public static final class DescriptorImpl extends UISampleDescriptor {
    }
}

