package io.jenkins.plugins.designlibrary;

import hudson.Extension;
import hudson.PluginWrapper;
import hudson.model.RootAction;
import java.util.List;
import java.util.Map;
import jenkins.model.Jenkins;

/**
 * Entry point to all the UI samples.
 *
 * @author Kohsuke Kawaguchi
 */
@Extension
public class Home implements RootAction {

    public String getIconFileName() {
        return "symbol-design-library plugin-design-library";
    }

    public String getDisplayName() {
        return "Design Library";
    }

    public String getUrlName() {
        return "design-library";
    }

    public List<UISample> getAll() {
        return UISample.getAll();
    }

    public static Map<Category, List<UISample>> getGrouped() {
        return UISample.getGrouped();
    }

    /**
     * Dynamically retrieves the appropriate UI sample based on the current URL
     */
    public UISample getDynamic(String name) {
        for (UISample ui : getAll()) {
            String urlName = ui.getUrlName();
            if (urlName != null && urlName.equals(name)) {
                return ui;
            }
        }
        return null;
    }

    public String getPluginVersion() {
        Jenkins jenkins = Jenkins.get();
        if (jenkins != null) {
            PluginWrapper plugin = jenkins.getPluginManager().getPlugin("design-library");
            return plugin.getVersion();
        }
        return "Version not available"; // Fallback message if version is not found
    }
}
