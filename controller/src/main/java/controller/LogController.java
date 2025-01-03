package controller;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.*;

import dao.LogDAO;
import model.Logs;

public class LogController {

    private static final Logger LOGGER = Logger.getLogger(LogController.class.getName());
    private final LogDAO logDAO;

    public LogController(LogDAO logDAO) {
        this.logDAO = logDAO;
    }

    public void createLog(int id_user, String action, LocalDateTime timestamp ) throws SQLException {
        Logs log = new Logs();
        log.setId_user(id_user);
        log.setAction(action);
        log.setTimestamp(timestamp);
        logDAO.create(log);
    }

    public List<Logs> getAllLog() throws SQLException {
        return logDAO.readAll();
    }

    public Logs getLogById(int id) throws SQLException {
        return logDAO.read(id);
    }

}
