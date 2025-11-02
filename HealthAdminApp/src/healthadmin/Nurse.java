package healthadmin;

import java.util.UUID;

final class Nurse implements Staff {
    private final String id;       // new
    private String name;
    private String ward;

    public Nurse(String name, String ward) {
        this.id = "N-" + UUID.randomUUID().toString().substring(0, 5); // unique ID
        this.name = name;
        this.ward = ward;
    }

    public String getId() { return id; }

    @Override
    public String getName() { return name; }

    @Override
    public int getAge() { return 28; }

    @Override
    public String getDepartment() { return ward; }

    @Override
    public String toString() { return id + ": Nurse " + name + " (" + ward + ")"; }
}
