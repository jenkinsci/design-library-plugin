package jenkins.plugins.ui_samples;

import hudson.Extension;
import org.kohsuke.stapler.bind.JavaScriptMethod;

/**
 * "Export" Java objects to JavaScript in the browser as a proxy object, so that
 * you can make ajax-calls to the server later.
 *
 * @author Kohsuke Kawaguchi
 */
@Extension
public class JavaScriptProxy extends UISample {
    private int i;

    /**
     * The annotation exposes this method to JavaScript proxy.
     */
    @JavaScriptMethod
    public int increment(int n) {
        return i+=n;
    }

    @Extension
    public static final class DescriptorImpl extends UISampleDescriptor {
    }
}
