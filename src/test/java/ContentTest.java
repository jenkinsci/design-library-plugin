import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;

import io.jenkins.plugins.designlibrary.UISample;
import java.util.List;
import java.util.stream.Stream;
import org.htmlunit.html.DomNode;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.jvnet.hudson.test.JenkinsRule;
import org.jvnet.hudson.test.junit.jupiter.WithJenkins;

@WithJenkins
@TestInstance(PER_CLASS)
class ContentTest {

    private JenkinsRule jenkins;

    private List<UISample> samples;

    @BeforeAll
    void beforeAll(JenkinsRule jenkins) {
        this.jenkins = jenkins;
        this.samples = jenkins.getInstance().getExtensionList(UISample.class);
    }

    /**
     * Validate that descriptive elements end with a full stop or a colon
     */
    @ParameterizedTest
    @MethodSource("getPages")
    void validContent(String url) throws Exception {
        try (var webClient = jenkins.createWebClient().withJavaScriptEnabled(false)) {
            // We get a bunch of spam in our logs about missing CSS, let's ignore that
            webClient.getOptions().setPrintContentOnFailingStatusCode(false);
            webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);

            var page = webClient.goTo(url);
            var links = page.querySelectorAll(".jdl-section__description, .jdl-dos-donts td").stream()
                    .map(DomNode::getTextContent)
                    .toList();

            links.forEach(e -> assertThat(e)
                    .satisfiesAnyOf(listParam -> assertThat(listParam).endsWith("."), listParam -> assertThat(listParam)
                            .endsWith(":")));
        }
    }

    private Stream<String> getPages() {
        Stream<String> homePage = Stream.of("design-library");
        Stream<String> otherItems = samples.stream().map(e -> "design-library/" + e.getUrlName());
        return Stream.concat(homePage, otherItems);
    }
}
