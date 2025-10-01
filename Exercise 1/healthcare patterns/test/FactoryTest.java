package test;

import factory.MedicalRecordFactory;
import factory.Person;
import factory.Patient;
import factory.Doctor;
import factory.Appointment;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FactoryTest {
    @Test
    void testCreatePatient() {
        Person p = MedicalRecordFactory.create("Patient", "Alice");
        assertTrue(p instanceof Patient);
        assertEquals("Alice", p.getName());
    }

    @Test
    void testCreateDoctor() {
        Person d = MedicalRecordFactory.create("Doctor", "Dr. Rao");
        assertTrue(d instanceof Doctor);
        assertEquals("Dr. Rao", d.getName());
    }

    @Test
    void testCreateAppointment() {
        Person a = MedicalRecordFactory.create("Appointment", "Appt-123");
        assertTrue(a instanceof Appointment);
        assertEquals("Appt-123", a.getName());
    }

    @Test
    void testInvalidTypeThrows() {
        assertThrows(IllegalArgumentException.class,
            () -> MedicalRecordFactory.create("Unknown", "X"));
    }
}

