package io.jenkins.plugins.designlibrary;

import static java.util.Arrays.asList;

import hudson.DescriptorExtensionList;
import hudson.Extension;
import hudson.ExtensionPoint;
import hudson.model.Describable;
import hudson.model.Descriptor;
import hudson.util.ListBoxModel;
import hudson.util.XStream2;
import java.io.IOException;
import javax.servlet.ServletException;
import jenkins.model.Jenkins;
import org.kohsuke.stapler.DataBoundConstructor;
import org.kohsuke.stapler.QueryParameter;
import org.kohsuke.stapler.StaplerRequest;
import org.kohsuke.stapler.StaplerResponse;

/**
 * @author Alan.Harder@oracle.com
 */
@Extension
public class Select extends UISample {
    @Override
    public String getIconFileName() {
        return "symbol-select";
    }

    public Fruit getFruit() {
        // Could return currently configured/saved item here to initialized form with this data
        return null;
    }

    public DescriptorExtensionList<Fruit, Descriptor<Fruit>> getFruitDescriptors() {
        return Jenkins.get().getDescriptorList(Fruit.class);
    }

    // Process form data and show it as serialized XML
    public void doSubmit(StaplerRequest req, StaplerResponse rsp) throws ServletException, IOException {
        // '$class' in form data tells Stapler which Fruit subclass to use,
        // older versions of Jenkins/Stapler used 'stapler-class'
        Fruit fruit = req.bindJSON(Fruit.class, req.getSubmittedForm().getJSONObject("fruit"));
        rsp.setContentType("text/plain");
        new XStream2().toXML(fruit, rsp.getWriter());
    }

    @Extension
    public static final class DescriptorImpl extends UISampleDescriptor {

        public ListBoxModel doFillFruitItems() {
            return new ListBoxModel(new ListBoxModel.Option("Apple"), new ListBoxModel.Option("Banana"));
        }

        public ListBoxModel doFillStateItems(@QueryParameter String country) {
            ListBoxModel m = new ListBoxModel();
            for (String s : asList("A", "B", "C"))
                m.add(String.format("State %s in %s", s, country), country + ':' + s);
            return m;
        }

        public ListBoxModel doFillCityItems(@QueryParameter String country, @QueryParameter String state) {
            ListBoxModel m = new ListBoxModel();
            for (String s : asList("X", "Y", "Z"))
                m.add(String.format("City %s in %s %s", s, state, country), state + ':' + s);
            return m;
        }
    }

    public abstract static class Fruit implements ExtensionPoint, Describable<Fruit> {
        protected String name;

        protected Fruit(String name) {
            this.name = name;
        }

        public Descriptor<Fruit> getDescriptor() {
            return Jenkins.get().getDescriptor(getClass());
        }
    }

    public static class FruitDescriptor extends Descriptor<Fruit> {}

    public static class Apple extends Fruit {
        private final int seeds;

        @DataBoundConstructor
        public Apple(int seeds) {
            super("Apple");
            this.seeds = seeds;
        }

        public int getSeeds() {
            return seeds;
        }

        @Extension
        public static final class DescriptorImpl extends FruitDescriptor {}
    }

    public static class Banana extends Fruit {
        private final boolean yellow;

        @DataBoundConstructor
        public Banana(boolean yellow) {
            super("Banana");
            this.yellow = yellow;
        }

        public boolean isYellow() {
            return yellow;
        }

        @Extension
        public static final class DescriptorImpl extends FruitDescriptor {}
    }
}
