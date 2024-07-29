import java.time.DayOfWeek;

public class Chore {
    private String name;
    private DayOfWeek deadline;

    public Chore(String name, DayOfWeek deadline) {
        this.name = name;
        this.deadline = deadline;
    }

    public String getName() {
        return name;
    }

    public DayOfWeek getDeadline() {
        return deadline;
    }

    // You can add more methods as needed
}
