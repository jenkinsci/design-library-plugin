import org.kohsuke.stapler.bind.JavaScriptMethod;

public class Foo {
    private int i;

    @JavaScriptMethod
    public int increment(int n) {
        return i += n;
    }
}
