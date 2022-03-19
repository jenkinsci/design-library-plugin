package jenkins.plugins.ui_samples;

import hudson.Extension;

import java.util.Arrays;
import java.util.List;

/**
 * @author Kohsuke Kawaguchi
 */
@Extension
public class Buttons extends UISample {
    @Override
    public String getIconFileName() {
        return "symbol-buttons";
    }

    @Extension
    public static final class DescriptorImpl extends UISampleDescriptor {
    }
}

