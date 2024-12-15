public static final class SimpleEntry extends Entry {

  private final String text;

  @DataBoundConstructor public SimpleEntry(String text) {
    this.text = text;
  }

  public String getText() {
    return text;
  }

  @Extension public static class DescriptorImpl extends Descriptor<Entry> {
    @Override public String getDisplayName() {
      return "Simple Entry";
    }
  }

}
