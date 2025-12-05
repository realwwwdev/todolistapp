package todo.dao;

import todo.model.Task;
import java.util.List;

public interface TaskDao {
    Task save(Task task);
    boolean delete(long id);
    List<Task> findAll();
    Task findById(long id);
}
