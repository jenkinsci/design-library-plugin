/*
 * The MIT License
 *
 * Copyright 2013 Jesse Glick.
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

import com.google.common.collect.ImmutableList;
import edu.umd.cs.findbugs.annotations.NonNull;
import hudson.Extension;
import hudson.model.AbstractDescribableImpl;
import hudson.model.Descriptor;
import hudson.util.ListBoxModel;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import jenkins.model.Jenkins;
import org.kohsuke.accmod.Restricted;
import org.kohsuke.accmod.restrictions.NoExternalUse;
import org.kohsuke.stapler.DataBoundConstructor;

@Extension
public class RepeatableList extends UISample {

    @Override
    public String getIconFileName() {
        return "symbol-list-outline plugin-ionicons-api";
    }

    @NonNull
    @Override
    public String getDisplayName() {
        return "Repeatable list";
    }

    @Override
    public String getDescription() {
        return "Displays lists with varying content types within the same container.";
    }

    @Extension
    public static final class DescriptorImpl extends UISampleDescriptor {}

    @Restricted(NoExternalUse.class)
    public Config getConfig() {
        return new Config(List.of(new ChoiceEntry(""), new SimpleEntry(""), new HeteroRadioEntry(new ChoiceEntry(""))));
    }

    public static final class Config extends AbstractDescribableImpl<Config> {

        private final List<Entry> entries;

        @DataBoundConstructor
        public Config(List<Entry> entries) {
            this.entries = entries != null ? new ArrayList<>(entries) : Collections.emptyList();
        }

        public List<Entry> getEntries() {
            return Collections.unmodifiableList(entries);
        }

        @Extension
        public static class DescriptorImpl extends Descriptor<Config> {}
    }

    public abstract static class Entry extends AbstractDescribableImpl<Entry> {}

    public static final class SimpleEntry extends Entry {

        private final String text;

        @DataBoundConstructor
        public SimpleEntry(String text) {
            this.text = text;
        }

        public String getText() {
            return text;
        }

        @Extension
        public static class DescriptorImpl extends Descriptor<Entry> {
            @Override
            public String getDisplayName() {
                return "Textbox";
            }
        }
    }

    public static final class ChoiceEntry extends Entry {

        private final String choice;

        @DataBoundConstructor
        public ChoiceEntry(String choice) {
            this.choice = choice;
        }

        public String getChoice() {
            return choice;
        }

        @Extension
        public static class DescriptorImpl extends Descriptor<Entry> {

            @Override
            public String getDisplayName() {
                return "Select";
            }

            public ListBoxModel doFillChoiceItems() {
                return new ListBoxModel().add("good").add("bad").add("ugly");
            }
        }
    }

    public static final class HeteroRadioEntry extends Entry {
        private final Entry entry;

        @DataBoundConstructor
        public HeteroRadioEntry(Entry entry) {
            this.entry = entry;
        }

        public Entry getEntry() {
            return entry;
        }

        @Extension
        public static class DescriptorImpl extends Descriptor<Entry> {

            @Override
            public String getDisplayName() {
                return "Radio";
            }

            public List<Descriptor<Entry>> getEntryDescriptors() {
                Jenkins jenkins = Jenkins.get();
                return ImmutableList.of(
                        jenkins.getDescriptorOrDie(ChoiceEntry.class), jenkins.getDescriptorOrDie(SimpleEntry.class));
            }
        }
    }
}
