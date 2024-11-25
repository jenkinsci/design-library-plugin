package io.jenkins.plugins.designlibrary;

import hudson.Extension;

/**
 * @author Kohsuke Kawaguchi
 */
@Extension
public class Buttons extends UISample {
    @Override
    public String getIconFileName() {
        return "symbol-buttons";
    }

    @Override
    public String getDescription() {
        return "Hello";
    }

    @Extension
    public static final class DescriptorImpl extends UISampleDescriptor {}

    public static String magic() {
        return "<button class=\"jenkins-button\"><l:icon src=\"symbol-add\" /> With symbol</button>";
    }
}
