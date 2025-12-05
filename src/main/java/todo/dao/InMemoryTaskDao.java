package todo.dao;

import todo.model.Task;
import java.util.ArrayList;
import java.util.List;

public class InMemoryTaskDao implements TaskDao {

    private final List<Task> tasks = new ArrayList<>();
    private long idCounter = 1;

    @Override
    public Task save(Task task) {
        if (task.getId() == 0) {
            task = new Task(idCounter++, task.getText(), task.getDeadline());
        }
        tasks.add(task);
        return task;
    }

    @Override
    public boolean delete(long id) {
        return tasks.removeIf(t -> t.getId() == id);
    }

    @Override
    public List<Task> findAll() {
        return new ArrayList<>(tasks);
    }

    @Override
    public Task findById(long id) {
        for (Task t : tasks) {
            if (t.getId() == id) return t;
        }
        return null;
    }
}
