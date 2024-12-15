public static final class Config extends AbstractDescribableImpl<Config> {

  private final List<Entry> entries;

  @DataBoundConstructor public Config(List<Entry> entries) {
    this.entries = entries != null ? new ArrayList<Entry>(entries) : Collections.<Entry>emptyList();
  }

  public List<Entry> getEntries() {
    return Collections.unmodifiableList(entries);
  }

  @Extension public static class DescriptorImpl extends Descriptor<Config> {}
}
