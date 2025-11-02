package healthadmin;

import java.time.LocalDate;
import java.util.*;
import java.util.function.Predicate;

class Hospital {
    private String name;
    private final List<Patient> patients = new ArrayList<>();
    private final List<Staff> staffList = new ArrayList<>();
    private final List<Appointment> appointments = new ArrayList<>();

    public Hospital(String name) {
        this.name = name;
    }

    // Method overloading + varargs
    public void addPatients(Patient... pats) {
        for (var p : pats) {
            patients.add(p);
            System.out.println("✅ Patient Registered: " + p.getName());
        }
    }

    public void addStaff(Staff s) {
        staffList.add(s);
        System.out.println("✅ Staff Registered: " + s.getName());
    }

    public void scheduleAppointment(String patientName, String staffId, LocalDate date, AppointmentType type)
            throws InvalidAppointmentException {
        var patient = patients.stream().filter(p -> p.getName().equalsIgnoreCase(patientName)).findFirst();
        var staff = staffList.stream().filter(s -> {
            if (s instanceof Doctor d) return d.getId().equalsIgnoreCase(staffId);
            if (s instanceof Nurse n) return n.getId().equalsIgnoreCase(staffId);
            return false;
        }).findFirst();

        if (patient.isEmpty() || staff.isEmpty()) {
            throw new InvalidAppointmentException("Patient or Staff ID not found.");
        }

        appointments.add(new Appointment(patient.get(), staff.get(), date, type));
        System.out.println("✅ Appointment Scheduled for " + patientName + " on " + date);
    }


    // Using method references
    public void displayAllPatients() {
        System.out.println("--- Patients ---");
        patients.forEach(System.out::println);
    }

    public void displayAllStaff() {
        System.out.println("--- Staff ---");
        staffList.forEach(System.out::println);
    }

    // Lambda with Predicate
    public void searchPatients(Predicate<Patient> predicate) {
        System.out.println("--- Patients matching criteria ---");
        patients.stream().filter(predicate).forEach(System.out::println);
    }
    
    public void displayAppointments() {
        if (appointments.isEmpty()) {
            System.out.println("No appointments scheduled.");
            return;
        }

        System.out.println("--- Scheduled Appointments ---");
        for (var appt : appointments) {
            System.out.println(appt.patient().getName() + " with " + appt.doctor().getName() +
                    " on " + appt.date() + " [" + appt.type() + "]");
        }
    }
}

