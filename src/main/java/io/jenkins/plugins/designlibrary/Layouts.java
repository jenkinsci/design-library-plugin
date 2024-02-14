package io.jenkins.plugins.designlibrary;

import hudson.Extension;
import jenkins.management.Badge;

@Extension
public class Layouts extends UISample {
    @Override
    public String getDisplayName() {
        return "Layouts";
    }

    @Override
    public String getIconFileName() {
        return "symbol-layouts";
    }

    public Badge getBadge() {
        return new Badge("123", "A tooltip describing the badge", Badge.Severity.INFO);
    }

    @Extension
    public static final class DescriptorImpl extends UISampleDescriptor {
    }
}

