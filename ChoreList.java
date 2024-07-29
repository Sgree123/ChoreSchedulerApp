import java.util.ArrayList;
import java.util.List;

public class ChoreList {
    private List<Chore> chores;

    public ChoreList() {
        this.chores = new ArrayList<>();
    }

    public void addChore(Chore chore) {
        chores.add(chore);
    }

    public List<Chore> getChores() {
        return chores;
    }

    public void displayChores() {
        System.out.println("Chores available:");
        for (int i = 0; i < chores.size(); i++) {
            System.out.println((i + 1) + ". " + chores.get(i).getName());
        }
    }

    public void removeChore(Chore chore) {
        chores.remove(chore);
    }

    public boolean isEmpty() {
        return chores.isEmpty();
    }
}
