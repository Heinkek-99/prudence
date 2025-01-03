package dao;

import java.sql.*;
import java.util.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.Teacher;

public class TeacherDAO {
private static final Logger LOGGER = Logger.getLogger(TeacherDAO.class.getName());
    private final Connection connection;

    public TeacherDAO(Connection connection) {
        this.connection = connection;
    }

    public void create(Teacher teacher) throws SQLException {
        String sql = "INSERT INTO Teachers (first_name, last_name, subject, phone, email, archived) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, teacher.getFirstName());
            stmt.setString(2, teacher.getLastName());
            stmt.setString(3, teacher.getSubject());
            stmt.setString(4, teacher.getPhone());
            stmt.setString(5, teacher.getEmail());
            stmt.setBoolean(6, teacher.isArchived());

            stmt.executeUpdate();

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    teacher.setIdTeacher(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException e) {
                LOGGER.log(Level.SEVERE, "Error creating teacher", e);
            throw e;
        }
    }

    public Teacher read(int id) throws SQLException {
        String sql = "SELECT * FROM Teachers WHERE ID_Teacher = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToTeacher(rs);
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error reading teacher", e);
            throw e;
        }
        return null;
    }

    public List<Teacher> readAll() throws SQLException {
        List<Teacher> teachers = new ArrayList<>();
        String sql = "SELECT * FROM Teachers";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                teachers.add(mapResultSetToTeacher(rs));
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error reading all teachers", e);
            throw e;
        }
        return teachers;
    }

    public void update(Teacher teacher) throws SQLException {
        String sql = "UPDATE Teachers SET first_name = ?, last_name = ?, subject = ?, phone = ?, email = ?, archived = ? WHERE ID_Teacher = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, teacher.getFirstName());
            stmt.setString(2, teacher.getLastName());
            stmt.setString(3, teacher.getSubject());
            stmt.setString(4, teacher.getPhone());
            stmt.setString(5, teacher.getEmail());
            stmt.setBoolean(6, teacher.isArchived());
            stmt.setInt(7, teacher.getIdTeacher());

            stmt.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error updating teacher", e);
            throw e;
        }
    }

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM Teachers WHERE ID_Teacher = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error deleting teacher", e);
            throw e;
        }
    }

    private Teacher mapResultSetToTeacher(ResultSet rs) throws SQLException {
        Teacher teacher = new Teacher();
        teacher.setIdTeacher(rs.getInt("ID_Teacher"));
        teacher.setFirstName(rs.getString("first_name"));
        teacher.setLastName(rs.getString("last_name"));
        teacher.setSubject(rs.getString("subject"));
        teacher.setPhone(rs.getString("phone"));
        teacher.setEmail(rs.getString("email"));
        teacher.setArchived(rs.getBoolean("archived"));

        return teacher;
    }
}
