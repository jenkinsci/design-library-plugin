package jenkins.plugins.ui_samples;

import hudson.Extension;

import java.util.Arrays;
import java.util.List;

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
}

