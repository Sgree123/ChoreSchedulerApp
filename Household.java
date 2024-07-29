import java.util.ArrayList;
import java.util.List;

public class Household {
    private String householdName;
    private List<Member> members;

    public Household(String householdName) {
        this.householdName = householdName;
        this.members = new ArrayList<>();
    }

    public String getHouseholdName() {
        return householdName;
    }

    public void addMember(Member member) {
        members.add(member);
    }

    public List<Member> getMembers() {
        return members;
    }
}
