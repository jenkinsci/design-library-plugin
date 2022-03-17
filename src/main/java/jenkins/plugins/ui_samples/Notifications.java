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

    @Override
    public String getDescription() {
        return "Notification bar shows a transient message on the top of the page";
    }

    @Extension
    public static final class DescriptorImpl extends UISampleDescriptor {
    }
}


