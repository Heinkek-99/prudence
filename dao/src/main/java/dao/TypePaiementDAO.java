package dao;

import java.sql.*;
import java.util.*;
import java.util.logging.*;
import model.TypePaiement;

public class TypePaiementDAO {
    
    private static final Logger LOGGER = Logger.getLogger(TypePaiementDAO.class.getName());
    private final Connection connection;

    public TypePaiementDAO(Connection connection) {
        this.connection = connection;
    }

    public void create(TypePaiement typePaiement) throws SQLException {
        String sql = "INSERT INTO TypePaiement (ID_Paiement, TypePaiement, archived) VALUES (?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, typePaiement.getIdPayment());
            stmt.setString(2, typePaiement.getTypePaiement());
            stmt.setBoolean(3, typePaiement.isArchived());

            stmt.executeUpdate();

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    typePaiement.setIdTypePaiement(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error creating payment type", e);
            throw e;
        }
    }

    public TypePaiement read(int id) throws SQLException {
        String sql = "SELECT * FROM TypePaiement WHERE ID_TypePaiement = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToTypePaiement(rs);
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error reading payment type", e);
            throw e;
        }
        return null;
    }

    public List<TypePaiement> readAll() throws SQLException {
        List<TypePaiement> typePaiements = new ArrayList<>();
        String sql = "SELECT * FROM TypePaiement";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                typePaiements.add(mapResultSetToTypePaiement(rs));
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error reading all payment types", e);
            throw e;
        }
        return typePaiements;
    }

    public void update(TypePaiement typePaiement) throws SQLException {
        String sql = "UPDATE TypePaiement SET ID_Paiement = ?, TypePaiement = ?, archived = ? WHERE ID_TypePaiement = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, typePaiement.getIdPayment());
            stmt.setString(2, typePaiement.getTypePaiement());
            stmt.setBoolean(3, typePaiement.isArchived());
            stmt.setInt(4, typePaiement.getIdTypePaiement());

            stmt.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error updating payment type", e);
            throw e;
        }
    }

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM TypePaiement WHERE ID_TypePaiement = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error deleting payment type", e);
            throw e;
        }
    }

    private TypePaiement mapResultSetToTypePaiement(ResultSet rs) throws SQLException {
        TypePaiement typePaiement = new TypePaiement();
        typePaiement.setIdTypePaiement(rs.getInt("ID_TypePaiement"));
        typePaiement.setIdPayment(rs.getInt("ID_Paiement"));
        typePaiement.setTypePaiement(rs.getString("TypePaiement"));
        typePaiement.setArchived(rs.getBoolean("archived"));

        return typePaiement;
    }
}
