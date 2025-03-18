package io.jenkins.plugins.designlibrary;

import hudson.Extension;

/**
 * @author Kohsuke Kawaguchi
 */
@Extension
public class Notifications extends UISample {
    @Override
    public String getIconFileName() {
        return "symbol-notifications-outline plugin-ionicons-api";
    }

    @Override
    public String getDescription() {
        return "Jenkins can display in-page notifications with a simple-to-use JavaScript API.";
    }

    @Extension
    public static final class DescriptorImpl extends UISampleDescriptor {}
}
