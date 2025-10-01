package factory;

public abstract class Person {
    protected String name;

    public Person(String name) {   // ✅ needed for super(name)
        this.name = name;
    }

    public String getName() { return name; }
    public abstract String getType();
}
