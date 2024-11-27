package io.jenkins.plugins.designlibrary;

import hudson.Extension;
import hudson.model.RootAction;
import java.util.List;
import java.util.Map;
import jenkins.model.ModelObjectWithContextMenu;
import org.kohsuke.stapler.StaplerRequest;
import org.kohsuke.stapler.StaplerResponse;

/**
 * Entry point to all the UI samples.
 *
 * @author Kohsuke Kawaguchi
 */
@Extension
public class Home implements RootAction, ModelObjectWithContextMenu {

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
        for (UISample s : getAll()) {
            String iconFilename = s.getIconFileName() + " plugin-design-library";
            menu.add(new MenuItem()
                    .withDisplayName(s.getDisplayName())
                    .withIconClass(iconFilename)
                    .withContextRelativeUrl("/" + getUrlName() + "/" + s.getUrlName()));
        }

        return menu.from(this, request, response);
    }

    public UISample getDynamic(String name) {
        for (UISample ui : getAll()) {
            String urlName = ui.getUrlName();
            if (urlName != null && urlName.equals(name)) return ui;
        }
        return null;
    }

    public List<UISample> getAll() {
        return UISample.getAll();
    }

    public static Map<Category, List<UISample>> getGrouped() {
        return UISample.getGrouped();
    }
}
