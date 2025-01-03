package dao;

import java.sql.*;
import java.util.*;
import java.util.logging.*;
import model.Logs;

public class LogDAO {
    
    private static final Logger LOGGER = Logger.getLogger(LogDAO.class.getName());
    private final Connection connection;

    public LogDAO(Connection connection) {
        this.connection = connection;
    }

    public void create(Logs log) throws SQLException {
        String sql = "INSERT INTO Logs (ID_User, Action, Timestamp) VALUES (?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, log.getId_user());
            stmt.setString(2, log.getAction());
            stmt.setTimestamp(3, Timestamp.valueOf(log.getTimestamp()));

            stmt.executeUpdate();

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    log.setId_log(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error creating log", e);
            throw e;
        }
    }

    public Logs read(int id) throws SQLException {
        String sql = "SELECT * FROM Logs WHERE ID_Log = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToLog(rs);
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error reading log", e);
            throw e;
        }
        return null;
    }

    public List<Logs> readAll() throws SQLException {
        List<Logs> logs = new ArrayList<>();
        String sql = "SELECT * FROM Logs";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                logs.add(mapResultSetToLog(rs));
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error reading all logs", e);
            throw e;
        }
        return logs;
    }

    public void update(Logs log) throws SQLException {
        String sql = "UPDATE Logs SET ID_User = ?, Action = ?, Timestamp = ? WHERE ID_Log = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, log.getId_user());
            stmt.setString(2, log.getAction());
            stmt.setTimestamp(3, Timestamp.valueOf(log.getTimestamp()));
            stmt.setInt(4, log.getId_log());

            stmt.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error updating log", e);
            throw e;
        }
    }

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM Logs WHERE ID_Log = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error deleting log", e);
            throw e;
        }
    }

    private Logs mapResultSetToLog(ResultSet rs) throws SQLException {
        Logs log = new Logs();
        log.setId_log(rs.getInt("ID_Log"));
        log.setId_user(rs.getInt("ID_User"));
        log.setAction(rs.getString("Action"));
        log.setTimestamp(rs.getTimestamp("Timestamp").toLocalDateTime());

        return log;
    }
}
