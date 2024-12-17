import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;

import io.jenkins.plugins.designlibrary.UISample;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import org.htmlunit.html.DomNode;
import org.htmlunit.html.HtmlAnchor;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.jvnet.hudson.test.JenkinsRule;
import org.jvnet.hudson.test.junit.jupiter.WithJenkins;

@WithJenkins
@TestInstance(PER_CLASS)
class ValidUrlsTest {

    private JenkinsRule jenkins;

    private List<UISample> samples;

    @BeforeAll
    void beforeAll(JenkinsRule jenkins) {
        this.jenkins = jenkins;
        this.samples = jenkins.getInstance().getExtensionList(UISample.class);
    }

    /**
     * Validate that relative URLs on a page actually link to a {@link UISample}
     */
    @ParameterizedTest
    @MethodSource("getPages")
    void validUrls(String url) throws Exception {
        try (var webClient = jenkins.createWebClient().withJavaScriptEnabled(false)) {
            // We get a bunch of spam in our logs about missing CSS, let's ignore that
            webClient.getOptions().setPrintContentOnFailingStatusCode(false);
            webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);

            List<String> validUrls =
                    new ArrayList<>(samples.stream().map(UISample::getUrlName).toList());

            var page = webClient.goTo(url);
            var links = page.querySelectorAll(".jdl-section a");

            if (links.isEmpty()) {
                System.out.println("🤷 No URLs on this page");
                return;
            }

            links.stream().filter(this::filterUrl).map(this::cleanseUrl).forEach(link -> {
                System.out.println("🕵️ Checking URL: " + link);
                assertThat(validUrls)
                        .withFailMessage(() -> "'" + link + "' isn't a valid URL, it needs to be one of:\n"
                                + String.join("\n", validUrls))
                        .contains(link);
            });
        }
    }

    private Stream<String> getPages() {
        // Create a stream with the first item "design-library"
        Stream<String> firstItem = Stream.of("design-library");

        // Generate the rest of the items
        Stream<String> otherItems = samples.stream().map(e -> "design-library/" + e.getUrlName());

        // Combine both streams
        return Stream.concat(firstItem, otherItems);
    }

    private boolean filterUrl(DomNode anchor) {
        var url = ((HtmlAnchor) anchor).getHrefAttribute();
        var response = !url.startsWith("http")
                && !url.startsWith("#")
                && !url.equals(".")
                && !url.equals("..")
                && !url.equals("twoColumn")
                && !url.equals("oneColumn")
                && !url.equals("fullscreen");

        if (!response) {
            System.out.println("🤫 Ignoring URL: " + url);
        }

        return response;
    }

    private String cleanseUrl(DomNode anchor) {
        var url = ((HtmlAnchor) anchor).getHrefAttribute();
        return url.replace("../", "");
    }
}
