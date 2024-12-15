package io.jenkins.plugins.designlibrary;

import hudson.Extension;
import jenkins.management.Badge;

@Extension
public class Layouts extends UISample {

    @Override
    public String getIconFileName() {
        return "symbol-layouts";
    }

    @Override
    public String getDescription() {
        return "Predefined layout structures and patterns to organize page content.";
    }

    @Override
    public Category getCategory() {
        return Category.PATTERN;
    }

    public Badge getBadge() {
        return new Badge("123", "A tooltip describing the badge", Badge.Severity.INFO);
    }

    @Extension
    public static final class DescriptorImpl extends UISampleDescriptor {}
}
