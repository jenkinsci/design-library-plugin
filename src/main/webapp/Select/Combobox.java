public class SimpleCombobox extends UISampleDescriptor {
    private static final String[] STATES = new String[]{
            "Alabama",
            "..."
    };

    public ComboBoxModel doFillStateItems() {
        return new ComboBoxModel(STATES);
    }
}
