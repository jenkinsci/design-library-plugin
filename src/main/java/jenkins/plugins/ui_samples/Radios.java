package jenkins.plugins.ui_samples;

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

    @Override
    public String getDescription() {
        return "Shows you how to use the progress bar widget that's used in the executor view and other places";
    }

    public List<UISample> getRadios() {
        return new ArrayList<>(Jenkins.get().getExtensionList(UISample.class)).subList(0, 4);
    }

    @Extension
    public static final class DescriptorImpl extends UISampleDescriptor {
    }
}

