public class Sample {
    private static final String[] STATES = new String[]{
            "Alabama",
            "..."
    };

    public ComboBoxModel doFillStateItems() {
        return new ComboBoxModel(STATES);
    }
}