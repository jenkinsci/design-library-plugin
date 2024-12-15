public static final class DescriptorImpl extends UISampleDescriptor {
    public ListBoxModel doFillStateItems() {
        ListBoxModel m = new ListBoxModel();
        for (String s : asList("A", "B", "C")) {
            m.add(String.format("State %s", s), s);
        }
        return m;
    }

    public ListBoxModel doFillCityItems(@QueryParameter String state) {
        ListBoxModel m = new ListBoxModel();
        for (String s : asList("X", "Y", "Z")) {
            m.add(String.format("City %s in %s", s, state), state + ':' + s);
        }
        return m;
    }
}
