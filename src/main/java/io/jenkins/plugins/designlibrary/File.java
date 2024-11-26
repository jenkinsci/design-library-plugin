package io.jenkins.plugins.designlibrary;

import hudson.Extension;

@Extension
public class File extends UISample {

    @Override
    public String getIconFileName() {
        return "symbol-cloud-upload-outline plugin-ionicons-api";
    }

    @Override
    public String getDescription() {
        return "Enables file upload or management functionality.";
    }

    @Extension
    public static final class DescriptorImpl extends UISampleDescriptor {}
}
