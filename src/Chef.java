import com.restaurantapp.model.Staff;

public class Chef extends Staff {
    private String specialty;

    public Chef(String name, int age, String contactInfo, double salary, String specialty) {
        super(name, age, contactInfo, "12","Chef", salary);
        this.specialty = specialty;
    }

    public String getSpecialty() { return specialty; }
}