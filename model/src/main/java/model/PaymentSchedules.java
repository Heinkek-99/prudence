package model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class PaymentSchedules {

    private int idSchedule;
    private int idStudent;
    private LocalDate dueDate;
    private BigDecimal amount;
    private String status;
    private boolean archived;
    private Student student;

    public PaymentSchedules(){}

    public int getIdSchedule() {
        return idSchedule;
    }

    public void setIdSchedule(int idSchedule) {
        this.idSchedule = idSchedule;
    }

    public int getIdStudent() {
        return idStudent;
    }

    public void setIdStudent(int idStudent) {
        this.idStudent = idStudent;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public boolean isArchived() {
        return archived;
    }
    
    public void setArchived(boolean archived) {
        this.archived = archived;
    }
    
    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

}
