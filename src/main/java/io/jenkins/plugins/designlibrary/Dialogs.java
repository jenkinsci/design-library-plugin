package io.jenkins.plugins.designlibrary;

import edu.umd.cs.findbugs.annotations.NonNull;
import hudson.Extension;
import hudson.util.ListBoxModel;

@Extension
public class Dialogs extends UISample {

    @NonNull
    @Override
    public String getDisplayName() {
        return super.getDisplayName();
    }

    @Override
    public String getIconFileName() {
        return "symbol-chatbox-ellipses-outline plugin-ionicons-api";
    }

    @Override
    public String getDescription() {
        return "Displays overlay windows for additional information or user input without navigating away.";
    }

    @Extension
    public static final class DescriptorImpl extends UISampleDescriptor {
        public ListBoxModel doFillFlavorItems() {
            return new ListBoxModel(
                    new ListBoxModel.Option("Apple"),
                    new ListBoxModel.Option("Acai"),
                    new ListBoxModel.Option("Banana"),
                    new ListBoxModel.Option("Strawberry"),
                    new ListBoxModel.Option("Vanilla"));
        }
    }
}
