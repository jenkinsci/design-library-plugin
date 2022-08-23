package io.jenkins.plugins.designlibrary;

import hudson.Extension;

/**
 * @author Kohsuke Kawaguchi
 */
@Extension
public class Notifications extends UISample {
    @Override
    public String getIconFileName() {
        return "symbol-notifications-outline plugins-ionicons-api";
    }

    @Extension
    public static final class DescriptorImpl extends UISampleDescriptor {
    }
}


