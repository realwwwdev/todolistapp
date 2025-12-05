package todo.app;

import todo.model.Task;
import todo.dao.InMemoryTaskDao;
import todo.service.TaskService;
import todo.model.Status;

import java.time.LocalDate;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TaskService service = new TaskService(new InMemoryTaskDao());

        System.out.println("Todo App. Commands: add, list, delete, done, edit, exit");

        while (true) {
            System.out.print("> ");
            String line = sc.nextLine().trim();

            if (line.equals("exit")) break;

            if (line.startsWith("add")) {
                try {
                    int firstQuote = line.indexOf('"');
                    int lastQuote = line.lastIndexOf('"');
                    String text = line.substring(firstQuote + 1, lastQuote);

                    String[] parts = line.substring(lastQuote + 1).trim().split(" ");
                    LocalDate deadline = null;
                    if (parts.length > 0 && !parts[0].isEmpty()) {
                        deadline = LocalDate.parse(parts[0]);
                    }

                    service.addTask(text, deadline);
                    System.out.println("Added");
                } catch (Exception e) {
                    System.out.println("Invalid add command. Use: add \"text\" [YYYY-MM-DD]");
                }
            } else if (line.equals("list")) {
                for (Task t : service.list()) System.out.println(t);
            } else if (line.startsWith("delete")) {
                long id = Long.parseLong(line.split(" ")[1]);
                service.delete(id);
                System.out.println("Deleted");
            } else if (line.startsWith("done")) {
                long id = Long.parseLong(line.split(" ")[1]);
                service.markDone(id);
                System.out.println("Marked done");
            } else if (line.startsWith("edit")) {
                try {
                    String[] parts = line.split(" ", 3);
                    long id = Long.parseLong(parts[1]);
                    String newText = parts[2].replace("\"", "");
                    service.updateText(id, newText);
                    System.out.println("Updated");
                } catch (Exception e) {
                    System.out.println("Invalid edit command. Use: edit <id> \"new text\"");
                }
            } else {
                System.out.println("Unknown command");
            }
        }
    }
}
