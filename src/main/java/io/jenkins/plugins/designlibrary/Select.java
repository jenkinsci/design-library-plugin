package io.jenkins.plugins.designlibrary;

import static java.util.Arrays.asList;

import hudson.DescriptorExtensionList;
import hudson.Extension;
import hudson.ExtensionPoint;
import hudson.model.AutoCompletionCandidates;
import hudson.model.Describable;
import hudson.model.Descriptor;
import hudson.util.ComboBoxModel;
import hudson.util.ListBoxModel;
import jenkins.model.Jenkins;
import org.kohsuke.stapler.DataBoundConstructor;
import org.kohsuke.stapler.QueryParameter;

/**
 * @author Alan.Harder@oracle.com
 */
@Extension
public class Select extends UISample {
    @Override
    public String getIconFileName() {
        return "symbol-select";
    }

    @Override
    public String getDescription() {
        return "Provides a dropdown for choosing one option from a predefined list.";
    }

    public Fruit getFruit() {
        // Could return currently configured/saved item here to initialized form with this data
        return null;
    }

    public DescriptorExtensionList<Fruit, Descriptor<Fruit>> getFruitDescriptors() {
        return Jenkins.get().getDescriptorList(Fruit.class);
    }

    @Extension
    public static final class DescriptorImpl extends UISampleDescriptor {

        public ListBoxModel doFillFruitItems() {
            return new ListBoxModel(new ListBoxModel.Option("Apple"), new ListBoxModel.Option("Banana"));
        }

        public ListBoxModel doFillStateItems() {
            ListBoxModel m = new ListBoxModel();
            for (String s : asList("A", "B", "C")) {
                m.add(String.format("State %s", s), s);
            }
            return m;
        }

        public ComboBoxModel doFillState2Items() {
            return new ComboBoxModel(STATES);
        }

        public ListBoxModel doFillCityItems(@QueryParameter String state) {
            ListBoxModel m = new ListBoxModel();
            for (String s : asList("X", "Y", "Z")) {
                m.add(String.format("City %s in %s", s, state), state + ':' + s);
            }
            return m;
        }

        /**
         * This method provides auto-completion items for the 'state' field.
         * Stapler finds this method via the naming convention.
         *
         * @param value
         *      The text that the user entered.
         */
        public AutoCompletionCandidates doAutoCompleteState(@QueryParameter String value) {
            AutoCompletionCandidates c = new AutoCompletionCandidates();
            for (String state : STATES)
                if (state.toLowerCase().startsWith(value.toLowerCase())) {
                    c.add(state);
                }
            return c;
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

    private static final String[] STATES = new String[] {
        "Alabama",
        "Alaska",
        "Arizona",
        "Arkansas",
        "California",
        "Colorado",
        "Connecticut",
        "Delaware",
        "Florida",
        "Georgia",
        "Hawaii",
        "Idaho",
        "Illinois",
        "Indiana",
        "Iowa",
        "Kansas",
        "Kentucky",
        "Louisiana",
        "Maine",
        "Maryland",
        "Massachusetts",
        "Michigan",
        "Minnesota",
        "Mississippi",
        "Missouri",
        "Montana",
        "Nebraska",
        "Nevada",
        "New Hampshire",
        "New Jersey",
        "New Mexico",
        "New York",
        "North Carolina",
        "North Dakota",
        "Ohio",
        "Oklahoma",
        "Oregon",
        "Pennsylvania",
        "Rhode Island",
        "South Carolina",
        "South Dakota",
        "Tennessee",
        "Texas",
        "Utah",
        "Vermont",
        "Virginia",
        "Washington",
        "West Virginia",
        "Wisconsin",
        "Wyoming"
    };
}
