import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Schedule {
    private Map<DayOfWeek, Map<Member, List<Chore>>> scheduleMap;

    public Schedule() {
        this.scheduleMap = new HashMap<>();
    }

    public void assignChore(DayOfWeek dayOfWeek, Member member, Chore chore) {
        if (!scheduleMap.containsKey(dayOfWeek)) {
            scheduleMap.put(dayOfWeek, new HashMap<>());
        }
        
        Map<Member, List<Chore>> dailyChores = scheduleMap.get(dayOfWeek);
        if (!dailyChores.containsKey(member)) {
            dailyChores.put(member, new ArrayList<>());
        }
    
        List<Chore> memberChores = dailyChores.get(member);
        memberChores.add(chore);
    }

    public Map<DayOfWeek, Map<Member, List<Chore>>> getScheduleMap() {
        return scheduleMap;
    }

    public void displaySchedule() {
    for (Map.Entry<DayOfWeek, Map<Member, List<Chore>>> entry : scheduleMap.entrySet()) {
        DayOfWeek dayOfWeek = entry.getKey();
        Map<Member, List<Chore>> dailyChores = entry.getValue();
        System.out.print(dayOfWeek + ": ");
        for (Map.Entry<Member, List<Chore>> choreEntry : dailyChores.entrySet()) {
            Member member = choreEntry.getKey();
            List<Chore> memberChores = choreEntry.getValue();
            for (Chore chore : memberChores) {
                System.out.print(chore.getName() + " (" + member.getName() + ") | ");
            }
        }
        System.out.println();
    }
}
}
