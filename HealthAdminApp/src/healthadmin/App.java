package healthadmin;

import java.time.LocalDate;
import java.util.*;

public class App {
    private static final Scanner sc = new Scanner(System.in);
    private static final Hospital hospital = new Hospital("Trinity Health Center");

    public static void main(String[] args) {
        System.out.println("=== Welcome to Health Administration System ===");

        boolean running = true;
        while (running) {
            System.out.println("""
                \nMenu:
                1. Register Patient
                2. Register Doctor
                3. Register Nurse
                4. Schedule Appointment
                5. View appointments
                6. View Patients
                7. View Staff
                8. Search Patient
                9. Exit
                """);
            System.out.print("Enter choice: ");
            String choice = sc.nextLine();

            switch (choice) {
                case "1" -> registerPatient();
                case "2" -> registerDoctor();
                case "3" -> registerNurse();
                case "4" -> scheduleAppointment();
                case "5" -> hospital.displayAppointments();
                case "6" -> hospital.displayAllPatients();
                case "7" -> hospital.displayAllStaff();
                case "8" -> {
                    System.out.print("Enter the name or starting letters to search: ");
                    String searchTerm = sc.nextLine().trim();
                    hospital.searchPatients(p -> p.getName().toLowerCase().startsWith(searchTerm.toLowerCase()));
                }
                
                case "9" -> running = false;
                default -> System.out.println("Invalid choice!");
            }
        }
        System.out.println("Goodbye!");
    }

    // Demonstrates constructor chaining (this()) and this.
    private static void registerPatient() {
        System.out.print("Enter name: ");
        String name = sc.nextLine();
        System.out.print("Enter age: ");
        int age = Integer.parseInt(sc.nextLine().trim());
        System.out.println("Enter email: ");
        String email = sc.nextLine();
        System.out.println("Enter Phone: ");
        String phone = sc.nextLine();
        System.out.print("Enter street: ");
        String street = sc.nextLine();
        System.out.print("Enter city: ");
        String city = sc.nextLine();

        ImmutableAddress address = new ImmutableAddress(street, city);
        Patient p = new Patient(name, age, new Contact(email, phone),address);
        hospital.addPatients(p);
    }

    private static void registerDoctor() {
        System.out.print("Enter name: ");
        String name = sc.nextLine();
        System.out.print("Enter specialty: ");
        String spec = sc.nextLine();
        Doctor d = new Doctor(name, spec);
        hospital.addStaff(d);
    }

    private static void registerNurse() {
        System.out.print("Enter name: ");
        String name = sc.nextLine();
        System.out.print("Enter ward: ");
        String ward = sc.nextLine();
        Nurse n = new Nurse(name, ward);
        hospital.addStaff(n);
    }

    private static void scheduleAppointment() {
        try {
        	System.out.print("Enter patient name: ");
        	String pname = sc.nextLine();
        	System.out.print("Enter staff ID: ");
        	String staffId = sc.nextLine();
        	System.out.print("Enter date (YYYY-MM-DD): ");
        	LocalDate date = LocalDate.parse(sc.nextLine());
        	System.out.println("Select type:");
        	for (AppointmentType t : AppointmentType.values()) {
        	    System.out.println("- " + t);
        	}
        	AppointmentType type = AppointmentType.valueOf(sc.nextLine().toUpperCase());

        	hospital.scheduleAppointment(pname, staffId, date, type);

        } catch (InvalidAppointmentException e) {
            System.out.println("⚠ " + e.getMessage());
        } catch (Exception e) {
            System.out.println("❌ Error: " + e);
        }
    }
}
