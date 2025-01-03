package dao;

import java.sql.*;
import java.sql.Date;
import java.util.*;
import java.util.logging.*;
import model.Payment;

public class PaymentDAO {
    
    private static final Logger LOGGER = Logger.getLogger(PaymentDAO.class.getName());
    private final Connection connection;

    public PaymentDAO(Connection connection) {
        this.connection = connection;
    }

    public void create(Payment payment) throws SQLException {
        String sql = "INSERT INTO Payment (ID_Student, ID_Mode, Payment_Type, Payment_Date, Amount, archived) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, payment.getIdStudent());
            stmt.setInt(2, payment.getIdMode());
            stmt.setString(3, payment.getPaymentType());
            stmt.setDate(4, Date.valueOf(payment.getPaymentDate()));
            stmt.setBigDecimal(5, payment.getAmount());
            stmt.setBoolean(6, payment.isArchived());

            stmt.executeUpdate();

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    payment.setIdPayment(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error creating payment", e);
            throw e;
        }
    }

    public Payment read(int id) throws SQLException {
        String sql = "SELECT * FROM Payment WHERE ID_Payment = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToPayment(rs);
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error reading payment", e);
            throw e;
        }
        return null;
    }

    public List<Payment> readAll() throws SQLException {
        List<Payment> Payment = new ArrayList<>();
        String sql = "SELECT * FROM Payment";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Payment.add(mapResultSetToPayment(rs));
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error reading all Payment", e);
            throw e;
        }
        return Payment;
    }

    public void update(Payment payment) throws SQLException {
        String sql = "UPDATE Payment SET ID_Student = ?, ID_Mode = ?, Payment_Type = ?, Payment_Date = ?, Amount = ?, archived = ? WHERE ID_Payment = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, payment.getIdStudent());
            stmt.setInt(2, payment.getIdMode());
            stmt.setString(3, payment.getPaymentType());
            stmt.setDate(4, Date.valueOf(payment.getPaymentDate()));
            stmt.setBigDecimal(5, payment.getAmount());
            stmt.setBoolean(6, payment.isArchived());
            stmt.setInt(7, payment.getIdPayment());

            stmt.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error updating payment", e);
            throw e;
        }
    }

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM Payment WHERE ID_Payment = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error deleting payment", e);
            throw e;
        }
    }

    private Payment mapResultSetToPayment(ResultSet rs) throws SQLException {
        Payment payment = new Payment();
        payment.setIdPayment(rs.getInt("ID_Payment"));
        payment.setIdStudent(rs.getInt("ID_Student"));
        payment.setIdMode(rs.getInt("ID_Mode"));
        payment.setPaymentType(rs.getString("Payment_Type"));
        payment.setPaymentDate(rs.getDate("Payment_Date").toLocalDate());
        payment.setAmount(rs.getBigDecimal("Amount"));
        payment.setArchived(rs.getBoolean("archived"));

        return payment;
    }
}
