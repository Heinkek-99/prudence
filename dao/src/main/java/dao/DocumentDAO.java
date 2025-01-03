package dao;

import java.sql.*;
import java.util.*;
import java.util.logging.*;
import model.Document;

public class DocumentDAO {
    
    private static final Logger LOGGER = Logger.getLogger(DocumentDAO.class.getName());
    private final Connection connection;

    public DocumentDAO(Connection connection) {
        this.connection = connection;
    }

    public void create(Document document) throws SQLException {
        String sql = "INSERT INTO Document (_Type, ID_Student, Path, archived) VALUES (?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, document.getType());
            stmt.setInt(2, document.getIdStudent());
            stmt.setString(3, document.getPath());
            stmt.setBoolean(4, document.isArchived());

            stmt.executeUpdate();

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    document.setIdDocument(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error creating document", e);
            throw e;
        }
    }

    public Document read(int id) throws SQLException {
        String sql = "SELECT * FROM Document WHERE ID_Document = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToDocument(rs);
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error reading document", e);
            throw e;
        }
        return null;
    }

    public List<Document> readAll() throws SQLException {
        List<Document> Document = new ArrayList<>();
        String sql = "SELECT * FROM Document";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Document.add(mapResultSetToDocument(rs));
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error reading all Document", e);
            throw e;
        }
        return Document;
    }

    public void update(Document document) throws SQLException {
        String sql = "UPDATE Document SET Type = ?, ID_Student = ?, File_Path = ?, archived = ? WHERE ID_Document = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, document.getType());
            stmt.setInt(2, document.getIdStudent());
            stmt.setString(3, document.getPath());
            stmt.setBoolean(4, document.isArchived());
            stmt.setInt(5, document.getIdDocument());

            stmt.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error updating document", e);
            throw e;
        }
    }

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM Document WHERE ID_Document = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error deleting document", e);
            throw e;
        }
    }

    private Document mapResultSetToDocument(ResultSet rs) throws SQLException {
        Document document = new Document();
        document.setIdDocument(rs.getInt("ID_Document"));
        document.setType(rs.getString("Type"));
        document.setIdStudent(rs.getInt("ID_Student"));
        document.setPath(rs.getString("Path"));
        document.setArchived(rs.getBoolean("archived"));

        return document;
    }
}
