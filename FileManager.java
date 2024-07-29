import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.DayOfWeek;
import java.util.List;
import java.util.Map;

public class FileManager {
    public static void writeScheduleToFile(String fileName, Schedule schedule) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Map.Entry<DayOfWeek, Map<Member, List<Chore>>> entry : schedule.getScheduleMap().entrySet()) {
                DayOfWeek dayOfWeek = entry.getKey();
                Map<Member, List<Chore>> dailyChores = entry.getValue();
                writer.write(dayOfWeek.toString());
                writer.write(" - ");
                for (Map.Entry<Member, List<Chore>> choreEntry : dailyChores.entrySet()) {
                    Member member = choreEntry.getKey();
                    List<Chore> chores = choreEntry.getValue();
                    for (Chore chore : chores) {
                        writer.write(chore.getName() + " (" + member.getName() + ") | ");
                    }
                }
                writer.newLine();
            }
            System.out.println("Schedule written to file: " + fileName);
        } catch (IOException e) {
            System.out.println("Error writing schedule to file: " + e.getMessage());
        }
    }
}
