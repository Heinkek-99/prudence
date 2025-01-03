package dao;

import java.sql.*;
import java.sql.Date;
import java.util.*;
import java.util.logging.*;
import model.PaiementStudent;

public class PaiementStudentDAO {
    
    private static final Logger LOGGER = Logger.getLogger(PaiementStudentDAO.class.getName());
    private final Connection connection;

    public PaiementStudentDAO(Connection connection) {
        this.connection = connection;
    }

    public void create(PaiementStudent paiementStudent) throws SQLException {
        String sql = "INSERT INTO PaiementStudent (ID_Inscription, Type_PaiementStudent, Date_PaiementStudent, Montant, archived) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, paiementStudent.getIdInscription());
            stmt.setString(2, paiementStudent.getTypePaiementStudent());
            stmt.setDate(3, Date.valueOf(paiementStudent.getDatePaiementStudent()));
            stmt.setBigDecimal(4, paiementStudent.getMontant());
            stmt.setBoolean(5, paiementStudent.isArchived());

            stmt.executeUpdate();

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    paiementStudent.setIdPaiementStudent(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error creating student payment", e);
            throw e;
        }
    }

    public PaiementStudent read(int id) throws SQLException {
        String sql = "SELECT * FROM PaiementStudent WHERE ID_PaiementStudent = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToPaiementStudent(rs);
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error reading student payment", e);
            throw e;
        }
        return null;
    }

    public List<PaiementStudent> readAll() throws SQLException {
        List<PaiementStudent> paiements = new ArrayList<>();
        String sql = "SELECT * FROM PaiementStudent";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                paiements.add(mapResultSetToPaiementStudent(rs));
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error reading all student payments", e);
            throw e;
        }
        return paiements;
    }

    public void update(PaiementStudent paiementStudent) throws SQLException {
        String sql = "UPDATE PaiementStudent SET ID_Inscription = ?, Type_PaiementStudent = ?, Date_PaiementStudent = ?, Montant = ?, archived = ? WHERE ID_PaiementStudent = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, paiementStudent.getIdInscription());
            stmt.setString(2, paiementStudent.getTypePaiementStudent());
            stmt.setDate(3, Date.valueOf(paiementStudent.getDatePaiementStudent()));
            stmt.setBigDecimal(4, paiementStudent.getMontant());
            stmt.setBoolean(5, paiementStudent.isArchived());
            stmt.setInt(6, paiementStudent.getIdPaiementStudent());

            stmt.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error updating student payment", e);
            throw e;
        }
    }

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM PaiementStudent WHERE ID_PaiementStudent = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error deleting student payment", e);
            throw e;
        }
    }

    private PaiementStudent mapResultSetToPaiementStudent(ResultSet rs) throws SQLException {
        PaiementStudent paiementStudent = new PaiementStudent();
        paiementStudent.setIdPaiementStudent(rs.getInt("ID_PaiementStudent"));
        paiementStudent.setIdInscription(rs.getInt("ID_Inscription"));
        paiementStudent.setTypePaiementStudent(rs.getString("Type_PaiementStudent"));
        paiementStudent.setDatePaiementStudent(rs.getDate("Date_PaiementStudent").toLocalDate());
        paiementStudent.setMontant(rs.getBigDecimal("Montant"));
        paiementStudent.setArchived(rs.getBoolean("archived"));

        return paiementStudent;
    }
}
