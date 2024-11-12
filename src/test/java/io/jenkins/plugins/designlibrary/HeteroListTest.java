package io.jenkins.plugins.designlibrary;

import static org.assertj.core.api.Assertions.assertThat;

import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableList;
import hudson.XmlFile;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.jvnet.hudson.test.JenkinsRule;

public class HeteroListTest {

    public static final String CONFIGURE_URL = "configure";
    public static final String CONFIG_FORM_NAME = "config";

    @Rule
    public JenkinsRule j = new JenkinsRule();

    @Test
    @Ignore("HtmlUnit doesn't work with prism or async code in sample.js")
    public void Hetero_Radio_Roundtrip() throws Exception {
        HeteroList.Entry firstRadioEntry = new HeteroList.HeteroRadioEntry(new HeteroList.SimpleEntry("first"));
        HeteroList.Entry secondRadioEntry = new HeteroList.HeteroRadioEntry(new HeteroList.SimpleEntry("second"));

        List<HeteroList.Entry> savedEntries = configRoundtrip(firstRadioEntry, secondRadioEntry);

        Assertions.assertThat(savedEntries).extracting("entry.text").containsExactly("first", "second");
    }

    private List<HeteroList.Entry> configRoundtrip(HeteroList.Entry... entries) throws Exception {
        HeteroList heteroList = new HeteroList();
        XmlFile configFile = heteroList.getConfigFile();
        heteroList.setConfig(new HeteroList.Config(ImmutableList.copyOf(entries)));
        configFile.write(heteroList);
        long savedManually = configFile.getFile().lastModified();

        configRoundtrip(heteroList);
        long savedViaWebInterface = configFile.getFile().lastModified();

        assertThat(savedManually).isLessThan(savedViaWebInterface);

        HeteroList saved = new HeteroList();

        return saved.getConfig().getEntries();
    }

    private void configRoundtrip(UISample uiSample) throws Exception {
        j.submit(j.createWebClient().goTo(getUiSampleConfigureUrl(uiSample)).getFormByName(CONFIG_FORM_NAME));
    }

    private String getUiSampleConfigureUrl(UISample uiSample) {
        return Joiner.on('/').join(new Root().getUrlName(), uiSample.getUrlName(), CONFIGURE_URL);
    }
}
