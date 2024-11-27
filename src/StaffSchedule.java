import com.restaurantapp.model.Staff;

import java.util.HashMap;

public class StaffSchedule {
    private HashMap<String, String> schedule;

    public StaffSchedule() {
        schedule = new HashMap<>();
    }

    public void assignShift(Staff staff, String shiftTime) {
        schedule.put(staff.getName(), shiftTime);
        System.out.println(staff.getName() + " assigned to shift: " + shiftTime);
    }

    public void viewSchedule() {
        for (String staffName : schedule.keySet()) {
            System.out.println(staffName + ": " + schedule.get(staffName));
        }
    }
}
