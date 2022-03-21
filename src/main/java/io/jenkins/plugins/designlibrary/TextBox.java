/*
 * The MIT License
 *
 * Copyright (c) 2010, InfraDNA, Inc.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package io.jenkins.plugins.designlibrary;

import hudson.Extension;
import hudson.model.AutoCompletionCandidates;
import hudson.util.ComboBoxModel;
import hudson.util.ListBoxModel;
import org.kohsuke.stapler.QueryParameter;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Adding auto-completion to the text box.
 *
 * @author Kohsuke Kawaguchi
 */
@Extension
public class TextBox extends UISample {
    @Override
    public String getIconFileName() {
        return "symbol-textbox";
    }

    @Extension
    public static final class DescriptorImpl extends UISampleDescriptor {
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

        public ComboBoxModel doFillStateItems() {
            return new ComboBoxModel(STATES);
        }

        public ListBoxModel doFillAlbumItems() {
            ListBoxModel m = new ListBoxModel();
            m.add("Yellow Submarine","1");
            m.add("Abbey Road","2");
            m.add("Let It Be","3");
            return m;
        }

        public ComboBoxModel doFillTitleItems(@QueryParameter int album) {
            switch (album) {
                case 1:
                    return new ComboBoxModel("Yellow Submarine","Only a Northern Song","All You Need Is Love");
                case 2:
                    return new ComboBoxModel("Come Together","Something","I Want You");
                case 3:
                    return new ComboBoxModel("The One After 909","Rocker","Get Back");
                default:
                    // if no value is selected on the album, we'll get 0
                    return new ComboBoxModel();
            }
        }
    }

    public Set<String> getStatesNames() {
        return new HashSet<> (Arrays.asList(STATES));
    }

    private static final String[] STATES = new String[]{
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
