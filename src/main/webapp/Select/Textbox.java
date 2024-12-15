public class Sample {
  public AutoCompletionCandidates doAutoCompleteState(@QueryParameter String value) {
    AutoCompletionCandidates c = new AutoCompletionCandidates();
    for (String state : STATES) {
      if (state.toLowerCase().startsWith(value.toLowerCase())) {
        c.add(state);
      }
    }
    return c;
  }
}