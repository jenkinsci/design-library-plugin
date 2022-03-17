package jenkins.plugins.ui_samples;

import hudson.DescriptorExtensionList;
import hudson.Extension;
import hudson.ExtensionPoint;
import hudson.model.Describable;
import hudson.model.Descriptor;
import hudson.util.XStream2;
import jenkins.model.Jenkins;
import org.kohsuke.stapler.DataBoundConstructor;
import org.kohsuke.stapler.StaplerRequest;
import org.kohsuke.stapler.StaplerResponse;

import javax.servlet.ServletException;
import java.io.IOException;

@Extension
public class Spacing extends UISample {
    @Override
    public String getIconFileName() {
        return "symbol-spacing";
    }

    @Extension
    public static class DescriptorImpl extends UISampleDescriptor {
    }
}
