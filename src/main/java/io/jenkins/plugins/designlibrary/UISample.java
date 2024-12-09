package io.jenkins.plugins.designlibrary;

import edu.umd.cs.findbugs.annotations.NonNull;
import hudson.ExtensionPoint;
import hudson.model.Action;
import hudson.model.Describable;
import io.jenkins.plugins.prism.PrismConfiguration;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;
import jenkins.model.Jenkins;
import org.kohsuke.accmod.Restricted;
import org.kohsuke.accmod.restrictions.NoExternalUse;

/**
 * @author Kohsuke Kawaguchi
 */
public abstract class UISample implements ExtensionPoint, Action, Describable<UISample> {

    /**
     * Gets the URL-friendly name of the UI sample.
     * Converts the display name to lowercase and replaces spaces with dashes.
     *
     * @return a URL-friendly string representation of the display name.
     */
    public String getUrlName() {
        return getDisplayName().replaceAll(" ", "-").toLowerCase();
    }

    /**
     * Gets the default display name for the UI sample.
     * This is typically the simple class name of the implementing class.
     *
     * @return the display name.
     */
    public @NonNull String getDisplayName() {
        return getClass().getSimpleName();
    }

    /**
     * Retrieves the category of this UI sample.
     * Defaults to {@link Category#COMPONENT}.
     *
     * @return the category of the UI sample.
     */
    public Category getCategory() {
        return Category.COMPONENT;
    }

    /**
     * Provides a description for the UI sample.
     * The description is displayed at the top of every UI page for the sample.
     *
     * @return the description of the UI sample.
     */
    public abstract String getDescription();

    /**
     * Gets the version of Jenkins where this UI sample was introduced.
     * By default, returns null if version information is irrelevant.
     *
     * @return the version as a string, or null if irrelevant.
     */
    public String getSince() {
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
        return getGrouped().values().stream().flatMap(Collection::stream).toList();
    }

    public static Map<Category, List<UISample>> getGrouped() {
        List<UISample> uiSamples = new ArrayList<>(Jenkins.get().getExtensionList(UISample.class));

        uiSamples.sort(Comparator.comparing(UISample::getDisplayName));

        return new TreeMap<>(uiSamples.stream().collect(Collectors.groupingBy(UISample::getCategory)));
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
