package io.jenkins.plugins.designlibrary;

import hudson.Extension;
import hudson.util.ListBoxModel;

@Extension
public class Dialogs extends UISample {
    @Override
    public String getIconFileName() {
        return "symbol-chatbox-ellipses-outline plugin-ionicons-api";
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
