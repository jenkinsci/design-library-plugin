package io.jenkins.plugins.designlibrary;

import hudson.Extension;
import hudson.RelativePath;
import hudson.model.AbstractDescribableImpl;
import hudson.model.Descriptor;
import hudson.util.FormValidation;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.kohsuke.stapler.DataBoundConstructor;
import org.kohsuke.stapler.QueryParameter;

/**
 * How to access values of the nearby input fields when you do form field validation.
 *
 * @author Kohsuke Kawaguchi
 */
@Extension
public class Validation extends UISample {
    @Override
    public String getIconFileName() {
        return "symbol-checkmark-circle-outline plugin-ionicons-api";
    }

    @Override
    public String getDescription() {
        return "Ensures user inputs meet specified criteria or rules before submission.";
    }

    @Override
    public Category getCategory() {
        return Category.PATTERN;
    }

    private List<State> states = new ArrayList<State>(Arrays.asList(
            new State(
                    "California",
                    new City("Sacramento"),
                    Arrays.asList(new City("San Francisco"), new City("Los Angeles"))),
            new State("New York", new City("New York"), Arrays.asList(new City("Albany"), new City("Ithaca")))));

    public Validation() {}

    @DataBoundConstructor
    public Validation(List<State> states) {
        this.states = states;
    }

    public List<State> getStates() {
        return states;
    }

    public static class State extends AbstractDescribableImpl<State> {
        private final String name;
        private final City capital;
        private final List<City> cities;

        @DataBoundConstructor
        public State(String name, City capital, List<City> cities) {
            this.name = name;
            this.capital = capital;
            this.cities = cities;
        }

        public String getName() {
            return name;
        }

        public City getCapital() {
            return capital;
        }

        public List<City> getCities() {
            return cities;
        }

        @Extension
        public static class DescriptorImpl extends Descriptor<State> {
            public FormValidation doCheckName(
                    @QueryParameter String value, @RelativePath("capital") @QueryParameter String name) {
                /*
                @RelativePath("capital") @QueryParameter
                 ... is short for
                @RelativePath("capital") @QueryParameter("name")
                 ... and thus can be thought of "capital/name"

                so this matches the current city name entered as the capital of this state
                */
                if (name == null) {
                    return FormValidation.ok();
                }

                return FormValidation.ok("Are you sure " + name + " is a capital of " + value + "?");
            }
        }
    }

    public static class City extends AbstractDescribableImpl<City> {
        private final String name;

        @DataBoundConstructor
        public City(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        @Extension
        public static class DescriptorImpl extends Descriptor<City> {
            public FormValidation doCheckName(
                    @QueryParameter String value, @RelativePath("..") @QueryParameter String name) {
                /*
                @RelativePath("..") @QueryParameter
                 ... is short for
                @RelativePath("..") @QueryParameter("name")
                 ... and thus can be thought of "../name"

                in the UI, fields for city is wrapped inside those of state, so "../name" binds
                to the name field in the state.
                */

                if (name == null || value == null || value.contains(name)) return FormValidation.ok();
                return FormValidation.warning("City name doesn't contain " + name);
            }
        }
    }

    @Extension
    public static class DescriptorImpl extends UISampleDescriptor {}
}
