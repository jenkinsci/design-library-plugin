package jenkins.plugins.ui_samples;

import hudson.Extension;

import java.util.Arrays;
import java.util.List;

/**
 * Syntax-highlighted text area (powered by CodeMirror).
 *
 * @author Kohsuke Kawaguchi
 */
@Extension
public class SyntaxHighlightedTextArea extends UISample {
    @Override
    public String getDescription() {
        return "Syntax-highlighted text area powered by CodeMirror";
    }

    @Extension
    public static final class DescriptorImpl extends UISampleDescriptor {
    }
}
