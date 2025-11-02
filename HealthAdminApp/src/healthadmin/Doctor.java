package healthadmin;

import java.util.UUID;

final class Doctor implements Staff {
    private final String id;       // new
    private String name;
    private String specialty;

    public Doctor(String name, String specialty) {
        this.id = "D-" + UUID.randomUUID().toString().substring(0, 5); // unique ID
        this.name = name;
        this.specialty = specialty;
    }

    public String getId() { return id; } // getter for ID

    @Override
    public String getName() { return name; }

    @Override
    public int getAge() { return 35; }

    @Override
    public String getDepartment() { return specialty; }

    @Override
    public String toString() { return id + ": Dr. " + name + " [" + specialty + "]"; }
}
