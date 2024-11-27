package io.jenkins.plugins.designlibrary;

public enum Category {
    COMPONENT("Components"),
    PATTERN("Patterns");

    Category(String displayName) {
        this.displayName = displayName;
    }

    private final String displayName;

    public String getDisplayName() {
        return displayName;
    }
}
