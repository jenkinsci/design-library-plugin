package io.jenkins.plugins.designlibrary;

import hudson.ExtensionPoint;
import hudson.model.Action;
import hudson.model.Describable;
import io.jenkins.plugins.prism.PrismConfiguration;
import java.util.ArrayList;
import java.util.List;
import jenkins.model.Jenkins;
import org.kohsuke.accmod.Restricted;
import org.kohsuke.accmod.restrictions.NoExternalUse;

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

    public String getDescription() {
        return null;
    }

    public UISampleDescriptor getDescriptor() {
        return (UISampleDescriptor) Jenkins.get().getDescriptorOrDie(getClass());
    }

    @Restricted(NoExternalUse.class)
    public PrismConfiguration getPrismConfiguration() {
        return PrismConfiguration.getInstance();
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
