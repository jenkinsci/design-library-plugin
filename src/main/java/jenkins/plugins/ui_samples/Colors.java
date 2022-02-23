package jenkins.plugins.ui_samples;

import hudson.Extension;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Kohsuke Kawaguchi
 */
@Extension
public class Colors extends UISample {
    @Override
    public String getIconFileName() {
        return "symbol-colors";
    }

    @Override
    public String getDescription() {
        return "Shows you how to use the progress bar widget that's used in the executor view and other places";
    }

    public List<String> getColors() {
        return Arrays.asList("red", "green", "orange", "yellow", "blue", "indigo", "violet");
    }

    public List<SourceFile> getSourceFiles() {
        // TODO: generate this from index
        return Arrays.asList(
                // new SourceFile(getClass().getSimpleName() + ".java"),  // nothing interesting in the Java source file
                new SourceFile("index.groovy"));
    }

    @Extension
    public static final class DescriptorImpl extends UISampleDescriptor {
    }
}

