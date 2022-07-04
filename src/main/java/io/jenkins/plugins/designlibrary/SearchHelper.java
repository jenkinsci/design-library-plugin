package io.jenkins.plugins.designlibrary;

import hudson.Functions;
import jenkins.model.Jenkins;
import org.apache.commons.jelly.JellyContext;
import org.apache.commons.jelly.JellyException;
import org.apache.commons.jelly.JellyTagException;
import org.apache.commons.jelly.Script;
import org.apache.commons.jelly.XMLOutput;
import org.jenkins.ui.icon.IconSet;
import org.kohsuke.stapler.Stapler;
import org.kohsuke.stapler.StaplerRequest;
import org.kohsuke.stapler.StaplerResponse;
import org.kohsuke.stapler.WebApp;
import org.kohsuke.stapler.jelly.DefaultScriptInvoker;
import org.kohsuke.stapler.jelly.JellyClassTearOff;
import org.kohsuke.stapler.jelly.JellyFacet;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class SearchHelper {

	private static final Map<UISample, List<SearchResult>> cachedHeadings = new HashMap<>();

	public static List<SearchResult> getSearchResults() {
		return UISample.getAll().stream()
				.map(sample -> {
					SearchResult searchResult = new SearchResult();
					searchResult.setIcon(IconSet.getSymbol(sample.getIconFileName().substring(7),
							"", "", "", "design-library", ""));
					searchResult.setTitle(sample.getDisplayName());
					searchResult.setUrl(Jenkins.get().getRootUrl() + "design-library/" + sample.getUrlName());
					try {
						searchResult.setChildren(SearchHelper.getHeadingsFromView(sample));
					} catch (RuntimeException | JellyException | IOException e) {
						throw new RuntimeException(e);
					}
					return searchResult;
				})
				.collect(Collectors.toList());
	}

	private static List<SearchResult> getHeadingsFromView(UISample uiSample) throws JellyException, IOException {
		if (cachedHeadings.containsKey(uiSample)) {
			return cachedHeadings.get(uiSample);
		}

		WebApp webApp = WebApp.getCurrent();
		final Script s = webApp.getMetaClass(uiSample).getTearOff(JellyClassTearOff.class).findScript("index");
		List<SearchResult> searchResults = new ArrayList<>();

		if (s != null) {
			StaplerRequest request = Stapler.getCurrentRequest();
			StaplerResponse response = Stapler.getCurrentResponse();
			JellyFacet facet = webApp.getFacet(JellyFacet.class);
			request.setAttribute("disableSearch", true);
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
			}, uiSample, xml);

			final Pattern pattern = Pattern.compile("<h2.*?id=\"(.*?)\".*?>(.*?)</h2>", Pattern.MULTILINE);
			final Matcher matcher = pattern.matcher(sw.toString());

			while (matcher.find()) {
				searchResults.add(new SearchResult(matcher.group(2),
						Jenkins.get().getRootUrl() + "design-library/" + uiSample.getUrlName() + "#" + matcher.group(1)));
			}
		}

		cachedHeadings.put(uiSample, searchResults);

		return searchResults;
	}

	public static class SearchResult {
		private String icon;
		private String title;
		private String url;
		private List<SearchResult> children;

		public SearchResult() {
		}

		public SearchResult(String title, String url) {
			this.title = title;
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

		public List<SearchResult> getChildren() {
			return children;
		}

		public void setChildren(List<SearchResult> children) {
			this.children = children;
		}
	}
}
