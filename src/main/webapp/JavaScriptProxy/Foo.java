import org.kohsuke.stapler.bind.JavaScriptMethod;

public class Foo {
    @JavaScriptMethod
    public int add(int x, int y) {
        return x+y;
    }
}
