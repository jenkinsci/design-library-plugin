package io.jenkins.plugins.designlibrary;

import hudson.Extension;
import jenkins.model.Jenkins;

import java.util.ArrayList;
import java.util.List;

@Extension
public class Radios extends UISample {
    @Override
    public String getIconFileName() {
        return "symbol-radios";
    }

    public List<UISample> getRadios() {
        return new ArrayList<>(Jenkins.get().getExtensionList(UISample.class)).subList(0, 4);
    }

    @Extension
    public static final class DescriptorImpl extends UISampleDescriptor {
    }
}

