package model;

import java.time.LocalDateTime;
import java.util.Objects;

public class Logs {
    private int id_log;
    private int id_user;
    private String action;
    private LocalDateTime timestamp;

    public Logs(int id_log, int id_user, String action, LocalDateTime timestamp) {
        this.id_log = id_log;
        this.id_user = id_user;
        this.action = action;
        this.timestamp = timestamp;
    }

    public Logs() {
        //TODO Auto-generated constructor stub
    }

    public int getId_log() {
        return id_log;
    }
    
    public void setId_log(int id_log) {
        this.id_log = id_log;
    }

    public int getId_user() {
        return id_user;
    }   

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }  

    @Override
    public String toString() {
        return "Logs [id_log=" + id_log + ", id_user=" + id_user + ", action=" + action + ", timestamp=" + timestamp + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Logs other = (Logs) obj;
        return id_log == other.id_log && id_user == other.id_user && action.equals(other.action) && timestamp.equals(other.timestamp);
    }
    @Override
    public int hashCode() {
        return Objects.hash(id_log, id_user, action, timestamp);
    }

}
