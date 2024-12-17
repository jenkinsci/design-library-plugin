public static final class ChoiceEntry extends Entry {

  private final String choice;

  @DataBoundConstructor public ChoiceEntry(String choice) {
    this.choice = choice;
  }

  public String getChoice() {
    return choice;
  }

  @Extension public static class DescriptorImpl extends Descriptor<Entry> {

    @Override public String getDisplayName() {
      return "Choice Entry";
    }

    public ListBoxModel doFillChoiceItems() {
      return new ListBoxModel().add("good").add("bad").add("ugly");
    }

  }
}
