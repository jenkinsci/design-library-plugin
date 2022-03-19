@Extension
public static class DescriptorImpl extends Descriptor<State> {
    public FormValidation doCheckName(
            @QueryParameter String value,
            @RelativePath("capital") @QueryParameter String name
    ) {
        /*
        @RelativePath("capital") @QueryParameter
         ... is short for
        @RelativePath("capital") @QueryParameter("name")
         ... and thus can be thought of "capital/name"

        so this matches the current city name entered as the capital of this state
        */
        if (name == null) {
            return FormValidation.ok();
        }

        return FormValidation.ok("Are you sure " + name + " is a capital of " + value + "?");
    }
}