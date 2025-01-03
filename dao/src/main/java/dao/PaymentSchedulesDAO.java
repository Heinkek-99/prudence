package dao;

import java.sql.*;
import java.sql.Date;
import java.util.*;
import java.util.logging.*;
import model.PaymentSchedules;

public class PaymentSchedulesDAO {
    
    private static final Logger LOGGER = Logger.getLogger(PaymentSchedulesDAO.class.getName());
    private final Connection connection;

    public PaymentSchedulesDAO(Connection connection) {
        this.connection = connection;
    }

    public void create(PaymentSchedules schedule) throws SQLException {
        String sql = "INSERT INTO PaymentSchedules (ID_Student, Due_Date, Amount, Status, archived) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, schedule.getIdStudent());
            stmt.setDate(2, Date.valueOf(schedule.getDueDate()));
            stmt.setBigDecimal(3, schedule.getAmount());
            stmt.setString(4, schedule.getStatus());
            stmt.setBoolean(5, schedule.isArchived());

            stmt.executeUpdate();

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    schedule.setIdSchedule(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error creating payment schedule", e);
            throw e;
        }
    }

    public PaymentSchedules read(int id) throws SQLException {
        String sql = "SELECT * FROM PaymentSchedules WHERE ID_Schedule = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToPaymentSchedule(rs);
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error reading payment schedule", e);
            throw e;
        }
        return null;
    }

    public List<PaymentSchedules> readAll() throws SQLException {
        List<PaymentSchedules> schedules = new ArrayList<>();
        String sql = "SELECT * FROM PaymentSchedules";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                schedules.add(mapResultSetToPaymentSchedule(rs));
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error reading all payment schedules", e);
            throw e;
        }
        return schedules;
    }

    public void update(PaymentSchedules schedule) throws SQLException {
        String sql = "UPDATE PaymentSchedules SET ID_Student = ?, Due_Date = ?, Amount = ?, Status = ?, archived = ? WHERE ID_Schedule = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, schedule.getIdStudent());
            stmt.setDate(2, Date.valueOf(schedule.getDueDate()));
            stmt.setBigDecimal(3, schedule.getAmount());
            stmt.setString(4, schedule.getStatus());
            stmt.setBoolean(5, schedule.isArchived());
            stmt.setInt(6, schedule.getIdSchedule());

            stmt.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error updating payment schedule", e);
            throw e;
        }
    }

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM PaymentSchedules WHERE ID_Schedule = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error deleting payment schedule", e);
            throw e;
        }
    }

    private PaymentSchedules mapResultSetToPaymentSchedule(ResultSet rs) throws SQLException {
        PaymentSchedules schedule = new PaymentSchedules();
        schedule.setIdSchedule(rs.getInt("ID_Schedule"));
        schedule.setIdStudent(rs.getInt("ID_Student"));
        schedule.setDueDate(rs.getDate("Due_Date").toLocalDate());
        schedule.setAmount(rs.getBigDecimal("Amount"));
        schedule.setStatus(rs.getString("Status"));
        schedule.setArchived(rs.getBoolean("archived"));

        return schedule;
    }
}
