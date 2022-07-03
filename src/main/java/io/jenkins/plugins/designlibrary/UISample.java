package io.jenkins.plugins.designlibrary;

import static org.apache.commons.io.IOUtils.copy;

import hudson.ExtensionPoint;
import hudson.Functions;
import hudson.model.Action;
import hudson.model.Actionable;
import hudson.model.Describable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jenkins.model.Jenkins;
import jenkins.model.ModelObjectWithContextMenu;
import org.apache.commons.jelly.JellyContext;
import org.apache.commons.jelly.JellyException;
import org.apache.commons.jelly.JellyTagException;
import org.apache.commons.jelly.Script;
import org.apache.commons.jelly.XMLOutput;
import org.kohsuke.stapler.StaplerRequest;
import org.kohsuke.stapler.StaplerResponse;
import org.kohsuke.stapler.WebApp;
import org.kohsuke.stapler.jelly.JellyClassTearOff;
import org.kohsuke.stapler.jelly.JellyFacet;
import org.xml.sax.helpers.DefaultHandler;

/**
 * @author Kohsuke Kawaguchi
 */
public abstract class UISample implements ExtensionPoint, Action, Describable<UISample> {
    public String getIconFileName() {
        return "symbol-sample";
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
