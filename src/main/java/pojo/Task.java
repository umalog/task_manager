package pojo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;

@XmlType
@XmlAccessorType(XmlAccessType.FIELD)
public class Task {
    @XmlElement(required = true)
    private String company;
    @XmlElement(required = true)
    private int taskID;
    @XmlElement(required = true)
    private String taskName;
    @XmlElement(required = true)
    private String description;
    @XmlElement(required = true)
    private TaskStatus status;
    @XmlElement(required = true)
    private int executor;
    @XmlElement(required = true)
    private int author;
    @XmlJavaTypeAdapter(value = LocalDateAdapter.class)
    private LocalDate workStartDate;
    @XmlJavaTypeAdapter(value = LocalDateAdapter.class)
    private LocalDate closingDate;
    @XmlJavaTypeAdapter(value = LocalDateAdapter.class)
    private LocalDate deadline;

    /* конструктор для БД1 */
    public Task(int id, String taskName, String description, int executor, int author,
                LocalDate startDate, LocalDate closingDate, LocalDate deadline, TaskStatus status, String company) {
        this.taskID = id;
        this.taskName = taskName;
        this.description = description;
        this.executor = executor;
        this.author = author;
        if (startDate != null) this.workStartDate = startDate;
        if (closingDate != null) this.closingDate = closingDate;
        this.deadline = deadline;
        this.status = status;
        this.company = company;
    }

    /* конструктор для БД2 */
    public Task(int id, String taskName, String description, int executor, int author,
                LocalDate startDate,  LocalDate deadline, TaskStatus status, String company) {
        this.taskID = id;
        this.taskName = taskName;
        this.description = description;
        this.executor = executor;
        this.author = author;
        if (startDate != null) this.workStartDate = startDate;
        this.deadline = deadline;
        this.status = status;
        this.company = company;
    }
    /* конструктор для БД3 */
    public Task(int id, String taskName, String description, int executor, int author,
                LocalDate deadline, TaskStatus status, String company) {
        this.taskID = id;
        this.taskName = taskName;
        this.description = description;
        this.executor = executor;
        this.author = author;
        this.deadline = deadline;
        this.status = status;
        this.company = company;
    }


    /**
     * Задача на конкретного исполнителя
     */
    public Task(String taskName, String description, Employee executor, Employee author, LocalDate deadline, Company company) {
        this.company = company.companyName;
        this.taskID = company.getTaskIDCounter();
        this.status = TaskStatus.InWork;
        this.workStartDate = LocalDate.now();
        this.taskName = taskName;
        this.description = description;
        this.executor = executor.employeeID;
        this.author = author.employeeID;
        this.deadline = deadline;
        company.addTask(this);
        executor.currentTask = this.taskID;
    }

    /**
     * Задача без исполнителя
     * недостающие поля заполняются при  {@link Task#setExecutor}
     */
    public Task(String taskName, String description, Employee author, LocalDate deadline, Company company) {
        this.company = company.companyName;
        taskID = company.getTaskIDCounter();
        this.status = TaskStatus.Paused;
        this.taskName = taskName;
        this.description = description;
        this.author = author.employeeID;
        this.deadline = deadline;
        company.addTask(this);
    }

    public Task() {
    }

    public void setExecutor(Employee executor) {
        this.executor = executor.employeeID;
        this.status = TaskStatus.InWork;
        this.workStartDate = LocalDate.now();
        executor.currentTask = this.taskID;
    }

    public String getCompany() {
        return company;
    }

    public int getTaskID() {
        return taskID;
    }

    public String getTaskName() {
        return taskName;
    }

    public String getDescription() {
        return description;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public int getExecutor() {
        return executor;
    }

    public int getAuthor() {
        return author;
    }

    public LocalDate getWorkStartDate() {
        return workStartDate;
    }

    public LocalDate getClosingDate() {
        return closingDate;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public void setTaskID(int taskID) {
        this.taskID = taskID;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public void setExecutor(int executor) {
        this.executor = executor;
    }

    public void setAuthor(int author) {
        this.author = author;
    }

    public void setWorkStartDate(LocalDate workStartDate) {
        this.workStartDate = workStartDate;
    }

    public void setClosingDate(LocalDate closingDate) {
        this.closingDate = closingDate;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Task task = (Task) o;

        return taskID == task.taskID;
    }

    @Override
    public int hashCode() {
        return (taskID * 31) + company.hashCode();
    }


    @Override
    public String toString() {
        return "Task{" +
                "company='" + company + '\'' +
                ", taskID=" + taskID +
                ", taskName='" + taskName + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                ", executor=" + executor +
                ", author=" + author +
                ", workStartDate=" + workStartDate +
                ", closingDate=" + closingDate +
                ", deadline=" + deadline +
                '}';
    }
}
