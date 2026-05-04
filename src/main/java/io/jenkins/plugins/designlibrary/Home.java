package io.jenkins.plugins.designlibrary;

import hudson.Extension;
import hudson.PluginWrapper;
import hudson.model.RootAction;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import jenkins.model.Jenkins;
import org.kohsuke.accmod.Restricted;
import org.kohsuke.accmod.restrictions.NoExternalUse;
import org.kohsuke.stapler.StaplerRequest2;
import org.kohsuke.stapler.StaplerResponse2;

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
        String normalizedName = name != null && name.endsWith(".md") ? name.substring(0, name.length() - 3) : name;
        for (UISample ui : getAll()) {
            String urlName = ui.getUrlName();
            if (urlName != null && urlName.equals(normalizedName)) {
                return ui;
            }
        }
        return null;
    }

    public void doDynamic(StaplerRequest2 req, StaplerResponse2 rsp) throws IOException {
        String restOfPath = req.getRestOfPath();
        if (restOfPath.startsWith("/")) {
            restOfPath = restOfPath.substring(1);
        }

        String content = resolveLlmContent(restOfPath, req);
        if (content != null) {
            rsp.setContentType("text/plain;charset=UTF-8");
            try (PrintWriter w = rsp.getWriter()) {
                w.write(content);
            }
            return;
        }

        rsp.sendError(404);
    }

    private String resolveLlmContent(String name, StaplerRequest2 req) {
        String baseUrl = req.getContextPath() + "/" + getUrlName() + "/";

        if ("llms.txt".equals(name)) {
            return LlmContent.generateIndex(baseUrl);
        }

        if ("llms-all.txt".equals(name)) {
            return LlmContent.generateAll();
        }

        return null;
    }

    /**
     * Gets the plugin version number for the plugin for the home page
     * @return the plugin version
     */
    @Restricted(NoExternalUse.class)
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

    private static final class GradientFactory {

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
