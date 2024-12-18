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

import edu.umd.cs.findbugs.annotations.NonNull;
import hudson.Extension;
import hudson.util.ComboBoxModel;
import hudson.util.ListBoxModel;
import org.kohsuke.stapler.QueryParameter;

/**
 * Adding auto-completion to the text box.
 *
 * @author Kohsuke Kawaguchi
 */
@Extension
public class Inputs extends UISample {

    @NonNull
    @Override
    public String getDisplayName() {
        return super.getDisplayName();
    }

    @Override
    public String getIconFileName() {
        return "symbol-textbox";
    }

    @Override
    public String getDescription() {
        return "Captures user input through various text or data entry formats.";
    }

    public String getShell() {
        return "echo \"Hello World\"";
    }

    @Extension
    public static final class DescriptorImpl extends UISampleDescriptor {

        public ListBoxModel doFillAlbumItems() {
            ListBoxModel m = new ListBoxModel();
            m.add("Yellow Submarine", "1");
            m.add("Abbey Road", "2");
            m.add("Let It Be", "3");
            return m;
        }

        public ComboBoxModel doFillTitleItems(@QueryParameter int album) {
            switch (album) {
                case 1:
                    return new ComboBoxModel("Yellow Submarine", "Only a Northern Song", "All You Need Is Love");
                case 2:
                    return new ComboBoxModel("Come Together", "Something", "I Want You");
                case 3:
                    return new ComboBoxModel("The One After 909", "Rocker", "Get Back");
                default:
                    // if no value is selected on the album, we'll get 0
                    return new ComboBoxModel();
            }
        }
    }
}
