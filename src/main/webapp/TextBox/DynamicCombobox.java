public class DynamicCombobox extends UISampleDescriptor {
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
