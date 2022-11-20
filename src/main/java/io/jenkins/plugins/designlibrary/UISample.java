package io.jenkins.plugins.designlibrary;

import static org.apache.commons.io.IOUtils.copy;

import hudson.ExtensionPoint;
import hudson.model.Action;
import hudson.model.Describable;

import java.util.ArrayList;
import java.util.List;

import jenkins.model.Jenkins;

/**
 * @author Kohsuke Kawaguchi
 */
public abstract class UISample implements ExtensionPoint, Action, Describable<UISample> {
    public String getIconFileName() {
        return "symbol-document-outline plugin-ionicons-api";
    }

    public String getUrlName() {
        return getClass().getSimpleName();
    }

    /**
     * Default display name.
     */
    public String getDisplayName() {
        return getClass().getSimpleName();
    }

    public UISampleDescriptor getDescriptor() {
        return (UISampleDescriptor) Jenkins.get().getDescriptorOrDie(getClass());
    }

    public static List<UISample> getAll() {
        return new ArrayList<>(Jenkins.get().getExtensionList(UISample.class));
    }

    public UISample getPreviousPage() {
        try {
            return getAll().get(getAll().indexOf(this) - 1);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    public UISample getNextPage() {
        try {
            return getAll().get(getAll().indexOf(this) + 1);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }
}
