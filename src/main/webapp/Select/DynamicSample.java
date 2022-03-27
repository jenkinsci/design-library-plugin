public static final class DescriptorImpl extends UISampleDescriptor {
    public ListBoxModel doFillStateItems(@QueryParameter String country) {
        ListBoxModel model = new ListBoxModel();
        for (String state : asList("A", "B", "C")) {
            model.add(String.format("State %s in %s", state, country),
                    country + ':' + state);
        }
        return model;
    }

    public ListBoxModel doFillCityItems(
            @QueryParameter String country,
            @QueryParameter String state
    ) {
        ListBoxModel model = new ListBoxModel();
        for (String city : asList("X", "Y", "Z")) {
            model.add(String.format("City %s in %s %s", city, state, country),
                    state + ':' + city);
        }
        return model;
    }
}
