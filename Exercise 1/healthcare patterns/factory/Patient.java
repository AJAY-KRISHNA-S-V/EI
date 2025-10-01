package factory;

public class Patient extends Person {
    private int age;
    private int bmi;
    private int bp;

    public Patient(String name, int age, int bmi, int bp) {
        super(name);   // ✅ uses Person constructor
        this.age = age;
        this.bmi = bmi;
        this.bp = bp;
    }

    public int getAge() { return age; }
    public int getBmi() { return bmi; }
    public int getBp() { return bp; }

    @Override
    public String getType() {
        return "Patient";
    }

    @Override
    public String toString() {
        return "Patient{name='" + name + "', age=" + age + ", BMI=" + bmi + ", BP=" + bp + "}";
    }
}
