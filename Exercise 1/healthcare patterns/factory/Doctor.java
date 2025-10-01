package factory;

public class Doctor extends Person {
    public Doctor(String name) {
        super(name);   // ✅ call parent constructor
    }

    @Override
    public String getType() {
        return "Doctor";
    }

    @Override
    public String toString() {
        return "Doctor{name='" + name + "'}";
    }
}
