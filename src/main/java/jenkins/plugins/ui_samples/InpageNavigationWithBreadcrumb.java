package jenkins.plugins.ui_samples;

import hudson.Extension;

import java.util.Arrays;
import java.util.List;

/**
 * @author Kohsuke Kawaguchi
 */
@Extension
public class InpageNavigationWithBreadcrumb extends UISample {
    @Override
    public String getDescription() {
        return "Adds in-page navigation with extra breadcrumb";
    }

    @Extension
    public static final class DescriptorImpl extends UISampleDescriptor {
    }
}


