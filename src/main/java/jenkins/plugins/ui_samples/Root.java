package jenkins.plugins.ui_samples;

import hudson.Extension;
import hudson.model.RootAction;
import jenkins.model.ModelObjectWithContextMenu;
import org.kohsuke.stapler.StaplerRequest;
import org.kohsuke.stapler.StaplerResponse;

import java.util.List;

/**
 * Entry point to all the UI samples.
 * 
 * @author Kohsuke Kawaguchi
 */
@Extension
public class Root implements RootAction, ModelObjectWithContextMenu {
    public String getIconFileName() {
        return "symbol-ui-samples";
    }

    public String getDisplayName() {
        return "UI Samples";
    }

    public String getUrlName() {
        return "ui-samples";
    }

    public UISample getDynamic(String name) {
        for (UISample ui : getAll()) {
            String urlName = ui.getUrlName();
            if (urlName != null && urlName.equals(name))
                return ui;
        }
        return null;
    }

    public List<UISample> getAll() {
        return UISample.getAll();
    }

    public ContextMenu doContextMenu(StaplerRequest request, StaplerResponse response) {
        return null;
    }
}
