public DescriptorExtensionList<Fruit, Descriptor<Fruit>> getFruitDescriptors() {
    return Jenkins.get().getDescriptorList(Fruit.class);
}

public abstract static class Fruit implements ExtensionPoint, Describable<Fruit> {
    protected String name;

    protected Fruit(String name) {
        this.name = name;
    }

    public Descriptor<Fruit> getDescriptor() {
        return Jenkins.get().getDescriptor(getClass());
    }
}

public static class FruitDescriptor extends Descriptor<Fruit> {}

public static class Apple extends Fruit {
    private final int seeds;

    @DataBoundConstructor
    public Apple(int seeds) {
        super("Apple");
        this.seeds = seeds;
    }

    public int getSeeds() {
        return seeds;
    }

    @Extension
    public static final class DescriptorImpl extends FruitDescriptor {}
}

public static class Banana extends Fruit {
    private final boolean yellow;

    @DataBoundConstructor
    public Banana(boolean yellow) {
        super("Banana");
        this.yellow = yellow;
    }

    public boolean isYellow() {
        return yellow;
    }

    @Extension
    public static final class DescriptorImpl extends FruitDescriptor {}
}
