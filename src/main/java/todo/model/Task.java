package todo.model;

import java.time.LocalDate;

public class Task {
    private long id;
    private String text;
    private Status status;
    private LocalDate deadline;

    public Task(long id, String text, LocalDate deadline) {
        this.id = id;
        this.text = text;
        this.deadline = deadline;
        this.status = Status.NEW;
    }

    public long getId() { return id; }
    public String getText() { return text; }
    public void setText(String text) { this.text = text; }
    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }
    public LocalDate getDeadline() { return deadline; }
    public void setDeadline(LocalDate deadline) { this.deadline = deadline; }

    @Override
    public String toString() {
        return id + ". " + text + " | status=" + status +
               (deadline != null ? " | deadline=" + deadline : "");
    }
}
