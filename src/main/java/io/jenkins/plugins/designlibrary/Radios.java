package io.jenkins.plugins.designlibrary;

import hudson.Extension;
import java.util.ArrayList;
import java.util.List;
import jenkins.model.Jenkins;

@Extension
public class Radios extends UISample {
    @Override
    public String getIconFileName() {
        return "symbol-radio-button-on-outline plugin-ionicons-api";
    }

    @Override
    public String getDescription() {
        return "Allows users to select a single option from a group.";
    }

    public List<UISample> getRadios() {
        return new ArrayList<>(Jenkins.get().getExtensionList(UISample.class)).subList(0, 4);
    }

    @Extension
    public static final class DescriptorImpl extends UISampleDescriptor {}
}
