package io.jenkins.plugins.designlibrary;

import hudson.Extension;
import hudson.model.RootAction;
import net.sf.json.JSONArray;

import java.util.List;

/**
 * Entry point to all the UI samples.
 * 
 * @author Kohsuke Kawaguchi
 */
@Extension
public class Root implements RootAction {
    public String getIconFileName() {
        return "symbol-design-library plugin-design-library";
    }

    public String getDisplayName() {
        return "Design Library";
    }

    public String getUrlName() {
        return "design-library";
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

    public JSONArray getSearch() {
        return UISample.getSearch();
    }
}
