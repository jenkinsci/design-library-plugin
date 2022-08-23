package io.jenkins.plugins.designlibrary;

import hudson.Extension;
import jenkins.util.ProgressiveRendering;
import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Kohsuke Kawaguchi
 */
@Extension
public class Progress extends UISample {
    @Override
    public String getIconFileName() {
        return "symbol-hourglass-outline plugin-ionicons-api";
    }

    public ProgressiveRendering factor(final String numberS) {
        return new ProgressiveRendering() {
            final List<Integer> newFactors = new LinkedList<Integer>();
            @Override protected void compute() throws Exception {
                int number;
                try {
                    number = Integer.parseInt(numberS); // try entering a nonnumeric value!
                } catch (NumberFormatException e) {
                    number = Integer.MAX_VALUE;
                }
                // Deliberately inefficient:
                for (int i = 1; i <= number; i++) {
                    if (canceled()) {
                        return;
                    }
                    if (i % 1000000 == 0) {
                        Thread.sleep(10); // take a breather
                    }
                    if (number % i == 0) {
                        synchronized (this) {
                            newFactors.add(i);
                        }
                    }
                    progress(((double) i) / number);
                }
            }
            @Override protected synchronized JSON data() {
                JSONArray r = new JSONArray();
                for (int i : newFactors) {
                    r.add(i);
                }
                newFactors.clear();
                return new JSONObject().accumulate("newfactors", r);
            }
        };
    }

    @Extension
    public static final class DescriptorImpl extends UISampleDescriptor {
    }
}

