package dao;

import java.sql.*;
import java.sql.Date;
import java.util.*;
import java.util.logging.*;
import model.Receipts;

public class ReceiptsDAO {
    
    private static final Logger LOGGER = Logger.getLogger(ReceiptsDAO.class.getName());
    private final Connection connection;

    public ReceiptsDAO(Connection connection) {
        this.connection = connection;
    }

    public void create(Receipts receipt) throws SQLException {
        String sql = "INSERT INTO Receipts (ID_Payment, Receipt_Date, Amount) VALUES (?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, receipt.getIdPayment());
            stmt.setDate(2, Date.valueOf(receipt.getReceiptDate()));
            stmt.setBigDecimal(3, receipt.getAmount());

            stmt.executeUpdate();

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    receipt.setIdReceipt(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error creating receipt", e);
            throw e;
        }
    }

    public Receipts read(int id) throws SQLException {
        String sql = "SELECT * FROM Receipts WHERE ID_Receipt = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToReceipt(rs);
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error reading receipt", e);
            throw e;
        }
        return null;
    }

    public List<Receipts> readAll() throws SQLException {
        List<Receipts> receipts = new ArrayList<>();
        String sql = "SELECT * FROM Receipts";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                receipts.add(mapResultSetToReceipt(rs));
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error reading all receipts", e);
            throw e;
        }
        return receipts;
    }

    public void update(Receipts receipt) throws SQLException {
        String sql = "UPDATE Receipts SET ID_Payment = ?, Receipt_Date = ?, Amount = ? WHERE ID_Receipt = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, receipt.getIdPayment());
            stmt.setDate(2, Date.valueOf(receipt.getReceiptDate()));
            stmt.setBigDecimal(3, receipt.getAmount());
            stmt.setInt(4, receipt.getIdReceipt());

            stmt.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error updating receipt", e);
            throw e;
        }
    }

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM Receipts WHERE ID_Receipt = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error deleting receipt", e);
            throw e;
        }
    }

    private Receipts mapResultSetToReceipt(ResultSet rs) throws SQLException {
        Receipts receipt = new Receipts();
        receipt.setIdReceipt(rs.getInt("ID_Receipt"));
        receipt.setIdPayment(rs.getInt("ID_Payment"));
        receipt.setReceiptDate(rs.getDate("Receipt_Date").toLocalDate());
        receipt.setAmount(rs.getBigDecimal("Amount"));

        return receipt;
    }
}
