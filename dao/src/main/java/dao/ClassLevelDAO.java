package dao;

import java.sql.*;
import java.util.*;
import java.util.logging.*;

import model.ClassLevel;

public class ClassLevelDAO {
private static final Logger LOGGER = Logger.getLogger(ClassLevelDAO.class.getName());
    private final Connection connection;

    public ClassLevelDAO(Connection connection) {
        this.connection = connection;
    }

    public void create(ClassLevel classLevel) throws SQLException {
        String sql = "INSERT INTO ClassLevel (Level_Name, Description, ID_Referent, archived) VALUES (?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, classLevel.getLevelName());
            stmt.setString(2, classLevel.getDescription());
            stmt.setInt(3, classLevel.getidReferent());
            stmt.setBoolean(4, classLevel.isArchived());

            stmt.executeUpdate();

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    classLevel.setIdLevel(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error creating class level", e);
            throw e;
        }
    }

    public ClassLevel read(int id) throws SQLException {
        String sql = "SELECT * FROM ClassLevel WHERE ID_ClassLevel = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToClassLevel(rs);
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error reading class level", e);
            throw e;
        }
        return null;
    }

    public List<ClassLevel> readAll() throws SQLException {
        List<ClassLevel> classLevels = new ArrayList<>();
        String sql = "SELECT * FROM ClassLevel";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                classLevels.add(mapResultSetToClassLevel(rs));
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error reading all class levels", e);
            throw e;
        }
        return classLevels;
    }

    public void update(ClassLevel classLevel) throws SQLException {
        String sql = "UPDATE ClassLevel SET Level_Name = ?, Description = ?, ID_Referent = ?, archived = ? WHERE ID_ClassLevel = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, classLevel.getLevelName());
            stmt.setString(2, classLevel.getDescription());
            stmt.setInt(3, classLevel.getidReferent());
            stmt.setBoolean(4, classLevel.isArchived());

            stmt.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error updating class level", e);
            throw e;
        }
    }

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM ClassLevel WHERE ID_ClassLevel = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error deleting class level", e);
            throw e;
        }
    }

    private ClassLevel mapResultSetToClassLevel(ResultSet rs) throws SQLException {
        ClassLevel classLevel = new ClassLevel();
        classLevel.setIdLevel(rs.getInt("ID_ClassLevel"));
        classLevel.setLevelName(rs.getString("Level_Name"));
        classLevel.setDescription(rs.getString("Description"));
        classLevel.setidReferent(rs.getInt("ID_Referent"));
        classLevel.setArchived(rs.getBoolean("archived"));

        return classLevel;
    }
}
