package io.jenkins.plugins.designlibrary;

import hudson.ExtensionPoint;
import hudson.Functions;
import hudson.model.Action;
import hudson.model.Describable;
import jenkins.model.Jenkins;
import org.apache.commons.jelly.JellyContext;
import org.apache.commons.jelly.JellyException;
import org.apache.commons.jelly.JellyTagException;
import org.apache.commons.jelly.Script;
import org.apache.commons.jelly.XMLOutput;
import org.kohsuke.stapler.StaplerRequest;
import org.kohsuke.stapler.StaplerResponse;
import org.kohsuke.stapler.WebApp;
import org.kohsuke.stapler.jelly.DefaultScriptInvoker;
import org.kohsuke.stapler.jelly.JellyClassTearOff;
import org.kohsuke.stapler.jelly.JellyFacet;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Kohsuke Kawaguchi
 */
public abstract class UISample implements ExtensionPoint, Action, Describable<UISample> {
    public static List<Root.SearchResult> headings = new ArrayList<>();

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

    public List<Root.SearchResult> getHeadings(StaplerRequest request, StaplerResponse response) throws JellyException, IOException {
        if (headings.size() != 0) {
            return headings;
        }

        WebApp webApp = WebApp.getCurrent();
        final Script s = webApp.getMetaClass(this).getTearOff(JellyClassTearOff.class).findScript("index");

        if (s != null) {
            JellyFacet facet = webApp.getFacet(JellyFacet.class);
            request.setAttribute("mode", "main-panel");
            DefaultScriptInvoker dsi = new DefaultScriptInvoker();
            StringWriter sw = new StringWriter();
            XMLOutput xml = dsi.createXMLOutput(sw, true);

            facet.scriptInvoker.invokeScript(request, response, new Script() {
                @Override
                public Script compile() {
                    return this;
                }

                @Override
                public void run(JellyContext context, XMLOutput output) throws JellyTagException {
                    Functions.initPageVariables(context);
                    s.run(context, output);
                }
            }, this, xml);

            final Pattern pattern = Pattern.compile("<h2.*?id=\"(.*?)\".*?>(.*?)</h2>", Pattern.MULTILINE);
            final Matcher matcher = pattern.matcher(sw.toString());

            while (matcher.find()) {
                headings.add(new Root.SearchResult(matcher.group(2),
                        Jenkins.get().getRootUrl() + "design-library/" + getUrlName() + "#" + matcher.group(1)));
            }
        }

        return headings;
    }
}
