import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;

import org.htmlunit.Page;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.jvnet.hudson.test.JenkinsRule;
import org.jvnet.hudson.test.junit.jupiter.WithJenkins;

@WithJenkins
@TestInstance(PER_CLASS)
class LlmContentTest {

    private JenkinsRule jenkins;

    @BeforeAll
    void beforeAll(JenkinsRule jenkins) {
        this.jenkins = jenkins;
    }

    @Test
    void llmsTxtContainsIndex() throws Exception {
        try (var webClient = jenkins.createWebClient()) {
            Page page = webClient.getPage(jenkins.getURL() + "design-library/llms.txt");
            String content = page.getWebResponse().getContentAsString();

            assertThat(content).startsWith("# Jenkins Design Library");
            assertThat(content).contains("## Components");
            assertThat(content).contains("## Patterns");
            assertThat(content).contains("buttons.md");
            assertThat(content).contains("cards.md");
        }
    }

    @Test
    void llmsAllTxtContainsCodeSnippets() throws Exception {
        try (var webClient = jenkins.createWebClient()) {
            Page page = webClient.getPage(jenkins.getURL() + "design-library/llms-all.txt");
            String content = page.getWebResponse().getContentAsString();

            assertThat(content).startsWith("# Jenkins Design Library");
            assertThat(content).contains("# Buttons");
            assertThat(content).contains("# Cards");
            assertThat(content).contains("# Colors");
            assertThat(content).contains("jenkins-button"); // Buttons/default.jelly
            assertThat(content).contains("l:card"); // Cards/card.jelly
            assertThat(content).contains("jenkins-alert"); // Banner/info.jelly
            assertThat(content).contains("f:checkbox"); // Checkboxes/checkbox.jelly
            assertThat(content).contains("f:toggleSwitch"); // ToggleSwitch/default.jelly
            assertThat(content).contains("```");
        }
    }

    @Test
    void unknownMdReturns404() throws Exception {
        try (var webClient = jenkins.createWebClient()) {
            webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
            Page page = webClient.getPage(jenkins.getURL() + "design-library/nonexistent.md");
            assertThat(page.getWebResponse().getStatusCode()).isEqualTo(404);
        }
    }

    @Test
    void existingComponentPagesStillWork() throws Exception {
        try (var webClient = jenkins.createWebClient().withJavaScriptEnabled(false)) {
            webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
            webClient.getOptions().setPrintContentOnFailingStatusCode(false);
            Page page = webClient.getPage(jenkins.getURL() + "design-library/buttons");
            assertThat(page.getWebResponse().getStatusCode()).isEqualTo(200);
        }
    }
}
