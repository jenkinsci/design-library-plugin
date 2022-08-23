package io.jenkins.plugins.designlibrary;

import hudson.Extension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Extension
public class Colors extends UISample {
    @Override
    public String getIconFileName() {
        return "symbol-water-outline plugins-ionicons-api";
    }

    public List<Semantic> getSemantics() {
        return Arrays.asList(
                new Semantic("Success", "Use for success states", "success-color"),
                new Semantic("Warning", "Use for warning states", "warning-color"),
                new Semantic("Error", "Use for error states", "error-color"),
                new Semantic("Build", "Use for build kickoff", "build-color", "play"),
                new Semantic("Destructive", "Use for destructive actions", "destructive-color", "trash")
        );
    }

    public List<Color> getColors() {
        List<Color> colors = Arrays.asList(
                new Color("Red", "red"),
                new Color("Green", "green"),
                new Color("Orange", "orange"),
                new Color("Yellow",  "yellow"),
                new Color("Blue",  "blue"),
                new Color("Indigo",  "indigo"),
                new Color("Purple",  "purple"),
                new Color("Pink",  "pink"),
                new Color("Brown",  "brown")
        );
        List<Color> completeList = new ArrayList<>();
        colors.forEach(color -> {
            completeList.add(new Color("Light " + color.name, "light-" + color.className));
            completeList.add(new Color(color.name, color.className));
            completeList.add(new Color("Dark " + color.name, "dark-" + color.className));
        });
        return completeList;
    }

    @Extension
    public static final class DescriptorImpl extends UISampleDescriptor {
    }

    public static final class Semantic {
        private String name;
        private String description;
        private String variable;
        private String symbol;

        public Semantic(String name, String description, String variable) {
            this.name = name;
            this.description = description;
            this.variable = variable;
        }

        public Semantic(String name, String description, String variable, String symbol) {
            this(name, description, variable);
            this.symbol = symbol;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getVariable() {
            return variable;
        }

        public void setVariable(String variable) {
            this.variable = variable;
        }

        public String getSymbol() {
            return symbol;
        }

        public void setSymbol(String symbol) {
            this.symbol = symbol;
        }
    }

    public static final class Color {
        private String name;
        private String className;
        private String variable;

        public Color(String name, String className) {
            this.name = name;
            this.className = className;
            this.variable = "color-" + className;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getClassName() {
            return className;
        }

        public void setClassName(String className) {
            this.className = className;
        }

        public String getVariable() {
            return variable;
        }

        public void setVariable(String variable) {
            this.variable = variable;
        }
    }
}

