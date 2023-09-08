package io.jenkins.plugins.designlibrary;

import hudson.Extension;
import hudson.Functions;
import hudson.model.RootAction;

import java.util.List;
import jenkins.model.Jenkins;
import jenkins.model.ModelObjectWithContextMenu;
import org.jenkins.ui.icon.IconSet;
import org.jenkinsci.Symbol;
import org.kohsuke.stapler.StaplerRequest;
import org.kohsuke.stapler.StaplerResponse;

/**
 * Entry point to all the UI samples.
 * 
 * @author Kohsuke Kawaguchi
 */
@Extension
public class Root implements RootAction, ModelObjectWithContextMenu {
    public String getIconFileName() {
        return "symbol-design-library plugin-design-library";
    }

    public String getDisplayName() {
        return "Design Library";
    }

    public String getUrlName() {
        return "design-library";
    }


    @Override
    public ContextMenu doContextMenu(StaplerRequest request, StaplerResponse response) throws Exception {
        ContextMenu menu = new ContextMenu();
        String url = Jenkins.get().getRootUrl() + getUrlName();
        for (UISample s: getAll()) {
            String iconFilename = s.getIconFileName() + " plugin-design-library";
            menu.add(new MenuItem().withDisplayName(s.getDisplayName())
                    .withIconClass(iconFilename)
                    .withContextRelativeUrl(url + "/" + s.getUrlName())
            );
        }

        return menu.from(this, request, response);
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
}
