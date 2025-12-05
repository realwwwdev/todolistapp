package todo.service;

import todo.dao.TaskDao;
import todo.model.Task;
import todo.model.Status;
import java.time.LocalDate;
import java.util.List;

public class TaskService {

    private final TaskDao dao;

    public TaskService(TaskDao dao) {
        this.dao = dao;
    }

    public Task addTask(String text, LocalDate deadline) {
        Task t = new Task(0, text, deadline);
        return dao.save(t);
    }

    public boolean delete(long id) { return dao.delete(id); }

    public List<Task> list() { return dao.findAll(); }

    public boolean markDone(long id) {
        Task t = dao.findById(id);
        if (t == null) return false;
        t.setStatus(Status.DONE);
        return true;
    }

    public boolean updateText(long id, String newText) {
        Task t = dao.findById(id);
        if (t == null) return false;
        t.setText(newText);
        return true;
    }
}
