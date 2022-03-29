public class Sample {
    public ListUISample getRadios() {
        return new ArrayList(Jenkins.get().getExtensionList(UISample.class))
                .subList(0, 4);
    }
}