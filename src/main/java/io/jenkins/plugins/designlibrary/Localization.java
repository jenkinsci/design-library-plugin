package io.jenkins.plugins.designlibrary;

import hudson.Extension;

@Extension
public class Localization extends UISample {

    @Override
    public String getIconFileName() {
        return "symbol-language-outline plugin-ionicons-api";
    }

    @Override
    public String getDescription() {
        return Messages.Localization_description();
    }

    public String getMessage() {
        return Messages.Localization_message();
    }

    public String getGreet() {
        return Messages.Localization_greet("World");
    }

    @Override
    public Category getCategory() {
        return Category.PATTERN;
    }

    @Extension
    public static final class DescriptorImpl extends UISampleDescriptor {}
}
