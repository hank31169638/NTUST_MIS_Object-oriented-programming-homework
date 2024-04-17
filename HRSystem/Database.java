package HRSystem;

import java.util.HashMap;
import java.util.Map;

public class Database {
    private Map<String, GeneralStaff> Members = new HashMap<>();

    public Map<String, GeneralStaff> getMembers() {
        return Members;
    }

    public void putMembers(String work_number,GeneralStaff members) {
        Members.put(work_number,members);
    }
}
