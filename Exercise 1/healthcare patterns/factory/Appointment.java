package factory;

public class Appointment extends Person {
    public Appointment(String id) {
        super(id);   // ✅ call parent constructor
    }

    @Override
    public String getType() {
        return "Appointment";
    }

    @Override
    public String toString() {
        return "Appointment{id='" + name + "'}";
    }
}
