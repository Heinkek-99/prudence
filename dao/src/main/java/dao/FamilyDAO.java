package dao;

import java.sql.*;
import java.util.*;
import java.util.logging.*;
import model.Family;

public class FamilyDAO {
    
    private static final Logger LOGGER = Logger.getLogger(FamilyDAO.class.getName());
    private final Connection connection;

    public FamilyDAO(Connection connection) {
        this.connection = connection;
    }

    public void create(Family family) throws SQLException {
        String sql = "INSERT INTO Family (Nom_Parent, Prenom_Parent, Titre, Adresse_1, Code_Ville, Telephone, Cnie, archived) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, family.getNomParent());
            stmt.setString(2, family.getPrenomParent());
            stmt.setString(3, family.getTitre());
            stmt.setString(4, family.getAdresse1());
            stmt.setString(6, family.getCodeVille());
            stmt.setString(7, family.getTelephone());
            stmt.setString(8, family.getCnie());
            stmt.setBoolean(9, family.isArchived());

            stmt.executeUpdate();

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    family.setIdFamily(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error creating family", e);
            throw e;
        }
    }

    public Family read(int id) throws SQLException {
        String sql = "SELECT * FROM Family WHERE id_family = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToFamily(rs);
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error reading family", e);
            throw e;
        }
        return null;
    }

    public List<Family> readAll() throws SQLException {
        List<Family> families = new ArrayList<>();
        String sql = "SELECT * FROM Family";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                families.add(mapResultSetToFamily(rs));
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error reading all families", e);
            throw e;
        }
        return families;
    }

    public void update(Family family) throws SQLException {
        String sql = "UPDATE Family SET Nom_Parent = ?, Prenom_Parent = ?, Titre = ?, Adresse_1 = ?, Adresse_2 = ?, Code_Ville = ?, Telephone = ?, Cnie = ?, archived = ? WHERE id_family = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, family.getNomParent());
            stmt.setString(2, family.getPrenomParent());
            stmt.setString(3, family.getTitre());
            stmt.setString(4, family.getAdresse1());
            stmt.setString(6, family.getCodeVille());
            stmt.setString(7, family.getTelephone());
            stmt.setString(8, family.getCnie());
            stmt.setBoolean(9, family.isArchived());
            stmt.setInt(10, family.getIdFamily());

            stmt.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error updating family", e);
            throw e;
        }
    }

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM Family WHERE id_family = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error deleting family", e);
            throw e;
        }
    }

    private Family mapResultSetToFamily(ResultSet rs) throws SQLException {
        Family family = new Family();
        family.setIdFamily(rs.getInt("id_family"));
        family.setNomParent(rs.getString("Nom_Parent"));
        family.setPrenomParent(rs.getString("Prenom_Parent"));
        family.setTitre(rs.getString("Titre"));
        family.setAdresse1(rs.getString("Adresse_1"));
        family.setCodeVille(rs.getString("Code_Ville"));
        family.setTelephone(rs.getString("Telephone"));
        family.setCnie(rs.getString("Cnie"));
        family.setArchived(rs.getBoolean("archived"));

        return family;
    }
}
