package dao;

import java.sql.*;
import java.sql.Date;
import java.util.*;
import java.util.logging.*;
import model.Inscription;

public class InscriptionDAO {
    
    private static final Logger LOGGER = Logger.getLogger(InscriptionDAO.class.getName());
    private final Connection connection;

    public InscriptionDAO(Connection connection) {
        this.connection = connection;
    }

    public void create(Inscription inscription) throws SQLException {
        String sql = "INSERT INTO Inscription (ID_Student, DateDebutInscription, DateFinInscription, FraisInscription, MontantInscription, archived) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, inscription.getIdStudent());
            stmt.setDate(2, Date.valueOf(inscription.getDateDebutInscription()));
            stmt.setDate(3, Date.valueOf(inscription.getDateFinInscription()));
            stmt.setBigDecimal(4, inscription.getFraisInscription());
            stmt.setBigDecimal(5, inscription.getMontantInscription());
            stmt.setBoolean(6, inscription.isArchived());

            stmt.executeUpdate();

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    inscription.setIdInscription(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error creating inscription", e);
            throw e;
        }
    }

    public Inscription read(int id) throws SQLException {
        String sql = "SELECT * FROM Inscription WHERE ID_Inscription = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToInscription(rs);
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error reading inscription", e);
            throw e;
        }
        return null;
    }

    public List<Inscription> readAll() throws SQLException {
        List<Inscription> inscriptions = new ArrayList<>();
        String sql = "SELECT * FROM Inscription";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                inscriptions.add(mapResultSetToInscription(rs));
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error reading all inscriptions", e);
            throw e;
        }
        return inscriptions;
    }

    public void update(Inscription inscription) throws SQLException {
        String sql = "UPDATE Inscription SET ID_Student = ?, DateDebutInscription = ?, DateFinInscription = ?, FraisInscription = ?, MontantInscription = ?, archived = ? WHERE ID_Inscription = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, inscription.getIdStudent());
            stmt.setDate(2, Date.valueOf(inscription.getDateDebutInscription()));
            stmt.setDate(3, Date.valueOf(inscription.getDateFinInscription()));
            stmt.setBigDecimal(4, inscription.getFraisInscription());
            stmt.setBigDecimal(5, inscription.getMontantInscription());
            stmt.setBoolean(6, inscription.isArchived());
            stmt.setInt(7, inscription.getIdInscription());

            stmt.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error updating inscription", e);
            throw e;
        }
    }

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM Inscription WHERE ID_Inscription = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error deleting inscription", e);
            throw e;
        }
    }

    private Inscription mapResultSetToInscription(ResultSet rs) throws SQLException {
        Inscription inscription = new Inscription();
        inscription.setIdInscription(rs.getInt("ID_Inscription"));
        inscription.setIdStudent(rs.getInt("ID_Student"));
        inscription.setDateDebutInscription(rs.getDate("DateDebutInscription").toLocalDate());
        inscription.setDateFinInscription(rs.getDate("DateFinInscription").toLocalDate());
        inscription.setFraisInscription(rs.getBigDecimal("FraisInscription"));
        inscription.setMontantInscription(rs.getBigDecimal("MontantInscription"));
        inscription.setArchived(rs.getBoolean("archived"));

        return inscription;
    }
}
