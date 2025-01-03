package dao;

import java.sql.*;
import java.util.*;
import java.util.logging.*;
import model.PaymentMode;

public class PaymentModeDAO {
    
    private static final Logger LOGGER = Logger.getLogger(PaymentModeDAO.class.getName());
    private final Connection connection;

    public PaymentModeDAO(Connection connection) {
        this.connection = connection;
    }

    public void create(PaymentMode paymentMode) throws SQLException {
        String sql = "INSERT INTO PaymentMode (Mode_Name, archived) VALUES (?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, paymentMode.getModeName());
            stmt.setBoolean(2, paymentMode.isArchived());

            stmt.executeUpdate();

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    paymentMode.setIdMode(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error creating payment mode", e);
            throw e;
        }
    }

    public PaymentMode read(int id) throws SQLException {
        String sql = "SELECT * FROM PaymentMode WHERE ID_Mode = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToPaymentMode(rs);
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error reading payment mode", e);
            throw e;
        }
        return null;
    }

    public List<PaymentMode> readAll() throws SQLException {
        List<PaymentMode> paymentModes = new ArrayList<>();
        String sql = "SELECT * FROM PaymentMode";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                paymentModes.add(mapResultSetToPaymentMode(rs));
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error reading all payment modes", e);
            throw e;
        }
        return paymentModes;
    }

    public void update(PaymentMode paymentMode) throws SQLException {
        String sql = "UPDATE PaymentMode SET Mode_Name = ?, archived = ? WHERE ID_Mode = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, paymentMode.getModeName());
            stmt.setBoolean(2, paymentMode.isArchived());
            stmt.setInt(3, paymentMode.getIdMode());

            stmt.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error updating payment mode", e);
            throw e;
        }
    }

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM PaymentMode WHERE ID_Mode = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error deleting payment mode", e);
            throw e;
        }
    }

    private PaymentMode mapResultSetToPaymentMode(ResultSet rs) throws SQLException {
        PaymentMode paymentMode = new PaymentMode();
        paymentMode.setIdMode(rs.getInt("ID_Mode"));
        paymentMode.setModeName(rs.getString("Mode_Name"));
        paymentMode.setArchived(rs.getBoolean("archived"));

        return paymentMode;
    }
}
