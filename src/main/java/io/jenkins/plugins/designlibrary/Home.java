package io.jenkins.plugins.designlibrary;

import hudson.Extension;
import hudson.PluginWrapper;
import hudson.model.RootAction;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
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

    @Override
    public boolean isPrimaryAction() {
        return true;
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
        PluginWrapper plugin = jenkins.getPluginManager().getPlugin("design-library");
        if (plugin != null) {
            return plugin.getVersion();
        }
        return "Version not available";
    }

    /**
     * Generates a dynamic gradient for the Home cards
     */
    public String buildGradientVar() {
        return GradientFactory.buildGradientVar();
    }

    public static final class GradientFactory {

        private static final int LAYERS = 10;

        private static final String[] COLOURS = {
            "var(--light-orange)",
            "var(--light-cyan)",
            "var(--light-pink)",
            "var(--light-red)",
            "var(--light-yellow)",
            "var(--light-purple)",
            "var(--light-teal)",
            "var(--light-indigo)",
            "var(--light-brown)"
        };

        private GradientFactory() {}

        public static String buildGradientVar() {
            StringBuilder css = new StringBuilder("--aurora").append(": \n  ");

            ThreadLocalRandom rnd = ThreadLocalRandom.current();
            for (int i = 0; i < LAYERS; i++) {
                int x = rnd.nextInt(101);
                int y = rnd.nextInt(101);
                String colour = COLOURS[rnd.nextInt(COLOURS.length)];

                css.append(String.format("radial-gradient(at %d%% %d%%, %s 0, transparent 50%%)", x, y, colour));

                css.append(i < LAYERS - 1 ? ",\n  " : ";");
            }
            return css.toString();
        }
    }
}
