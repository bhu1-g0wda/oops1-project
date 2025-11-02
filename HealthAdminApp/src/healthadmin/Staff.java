package healthadmin;

sealed interface Staff extends Person permits Doctor, Nurse {
    String getDepartment();

    // static interface method
    static void hospitalPolicy() {
        System.out.println("All staff must adhere to patient confidentiality.");
    }

    // default interface method
    default void greet() {
        System.out.println("Hello, I'm " + getName() + " from " + getDepartment());
    }
}

