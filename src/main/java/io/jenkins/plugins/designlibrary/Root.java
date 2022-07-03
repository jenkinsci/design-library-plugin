package io.jenkins.plugins.designlibrary;

import hudson.Extension;
import hudson.model.RootAction;
import hudson.util.HttpResponses;
import jenkins.model.Jenkins;
import jenkins.model.ModelObjectWithContextMenu;
import net.sf.json.JSONArray;
import org.jenkins.ui.icon.IconSet;
import org.kohsuke.stapler.HttpResponse;
import org.kohsuke.stapler.Stapler;
import org.kohsuke.stapler.StaplerRequest;
import org.kohsuke.stapler.StaplerResponse;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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

    public UISample getDynamic(String name) {
        for (UISample ui : getAll()) {
            String urlName = ui.getUrlName();
            if (urlName != null && urlName.equals(name))
                return ui;
        }
        return null;
    }

    public static class SearchResult {
        private String icon;
        private String title;
        private String description;
        private String url;
        private List<SearchResult> children;

        public SearchResult() {
        }

        public SearchResult(String title, String description, String url) {
            this.title = title;
            this.description = description;
            this.url = url;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public List<SearchResult> getChildren() {
            return children;
        }

        public void setChildren(List<SearchResult> children) {
            this.children = children;
        }
    }

    public HttpResponse doSearch() {
        final StaplerRequest request = Stapler.getCurrentRequest();
        final String query = request.getParameter("query");

        List<SearchResult> searchResults = getAll().stream()
                .filter(sample -> sample.getDisplayName().toLowerCase().contains(query.toLowerCase()))
                .limit(5)
                .map(sample -> {
            SearchResult searchResult = new SearchResult();
            searchResult.setIcon(IconSet.getSymbol(sample.getIconFileName().substring(7),
                    "", "", "", "design-library", ""));
            searchResult.setTitle(sample.getDisplayName());
            searchResult.setUrl(Jenkins.get().getRootUrl() + "design-library/" + sample.getUrlName());
            searchResult.setChildren(Collections.singletonList(
                    new SearchResult("Tropic Morning News", "Hello world", "")));
            return searchResult;
        })
                .collect(Collectors.toList());

        return HttpResponses.okJSON(JSONArray.fromObject(searchResults));
    }

    public List<UISample> getAll() {
        return UISample.getAll();
    }

    public ContextMenu doContextMenu(StaplerRequest request, StaplerResponse response) {
        return null;
    }
}
