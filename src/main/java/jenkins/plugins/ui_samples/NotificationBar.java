package jenkins.plugins.ui_samples;

import hudson.Extension;

import java.util.Arrays;
import java.util.List;

/**
 * @author Kohsuke Kawaguchi
 */
@Extension
public class NotificationBar extends UISample {
    @Override
    public String getDescription() {
        return "Notification bar shows a transient message on the top of the page";
    }

    @Extension
    public static final class DescriptorImpl extends UISampleDescriptor {
    }
}


