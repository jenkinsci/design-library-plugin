package jenkins.plugins.ui_samples;

import hudson.Extension;

import java.util.Arrays;
import java.util.List;

/**
 * @author Kohsuke Kawaguchi
 */
@Extension
public class Symbols extends UISample {
    @Override
    public String getIconFileName() {
        return "symbol-symbols";
    }

    @Extension
    public static final class DescriptorImpl extends UISampleDescriptor {
    }
}

