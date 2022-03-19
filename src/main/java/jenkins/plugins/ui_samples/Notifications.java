package jenkins.plugins.ui_samples;

import hudson.Extension;

/**
 * @author Kohsuke Kawaguchi
 */
@Extension
public class Notifications extends UISample {
    @Override
    public String getIconFileName() {
        return "symbol-notifications";
    }

    @Extension
    public static final class DescriptorImpl extends UISampleDescriptor {
    }
}


