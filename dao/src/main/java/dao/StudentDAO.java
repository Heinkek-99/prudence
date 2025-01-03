package dao;

import java.sql.*;
import java.sql.Date;
import java.util.*;
import java.util.logging.*;

import model.Student;


public class StudentDAO {
	
	private static final Logger LOGGER = Logger.getLogger(UserDAO.class.getName());

	private final Connection connection;

    public StudentDAO(Connection connection) {
        this.connection = connection;
    }
    public void create(Student student) throws SQLException {
        String sql = "INSERT INTO student (matricule, first_name, last_name, gender, date_of_birth, " +
                    "address, phone, nationality, photo_path, id_family,, id_inscription, id_classlevel, archived) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, student.getMatricule());
            stmt.setString(2, student.getFirstName());
            stmt.setString(3, student.getLastName());
            stmt.setString(4, student.getGender());
            stmt.setDate(5, Date.valueOf(student.getDate_of_birth()));
            stmt.setString(6, student.getAddress());
            stmt.setString(7, student.getPhone());
            stmt.setString(8, student.getNationality());
            stmt.setString(9, student.getPhotoPath());
            stmt.setInt(10, student.getIdFamily());
            stmt.setInt(11, student.getIdInscription());
            stmt.setInt(12, student.getIdClassLevel());

			stmt.setBoolean(13, student.isArchived());
            
            stmt.executeUpdate();
            
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    student.setIdStudent(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error creating student", e);
            throw e;
        }
    }
    
    public Student read(int id) throws SQLException {
        String sql = "SELECT * FROM student WHERE id_student = ?";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToStudent(rs);
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error reading student", e);
            throw e;
        }
        return null;
    }
    
    public List<Student> readAll() throws SQLException {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM student";
        
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                students.add(mapResultSetToStudent(rs));
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error reading all students", e);
            throw e;
        }
        return students;
    }
    
    public void update(Student student) throws SQLException {
        String sql = "UPDATE student SET matricule = ?, first_name = ?, last_name = ?, " +
                    "gender = ?, date_of_birth = ?, address = ?, phone = ?, nationality = ?, " +
                    "photo_path = ?, id_family = ?, id_inscription = ?, id_classlevel = ?,archived = ? WHERE id_student = ?";
                    
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, student.getMatricule());
            stmt.setString(2, student.getFirstName());
            stmt.setString(3, student.getLastName());
            stmt.setString(4, student.getGender());
            stmt.setDate(5, Date.valueOf(student.getDate_of_birth()));
            stmt.setString(6, student.getAddress());
            stmt.setString(7, student.getPhone());
            stmt.setString(8, student.getNationality());
            stmt.setString(9, student.getPhotoPath());
            stmt.setInt(10, student.getIdFamily());
            stmt.setBoolean(11, student.isArchived());
            stmt.setInt(12, student.getIdStudent());
			stmt.setInt(13, student.getIdInscription());
            stmt.setInt(14, student.getIdClassLevel());

            
            stmt.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error updating student", e);
            throw e;
        }
    }
    
    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM student WHERE id_student = ?";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error deleting student", e);
            throw e;
        }
    }
    
    private Student mapResultSetToStudent(ResultSet rs) throws SQLException {
        Student student = new Student();
        student.setIdStudent(rs.getInt("id_student"));
        student.setMatricule(rs.getString("matricule"));
        student.setFirstName(rs.getString("first_name"));
        student.setLastName(rs.getString("last_name"));
        student.setGender(rs.getString("gender"));
        student.setDate_of_birth(rs.getDate("date_of_birth").toLocalDate());
        student.setAddress(rs.getString("address"));
        student.setPhone(rs.getString("phone"));
        student.setNationality(rs.getString("nationality"));
        student.setPhotoPath(rs.getString("photo_path"));
        student.setIdFamily(rs.getInt("id_family"));
		student.setIdFamily(rs.getInt("id_inscription"));
        student.setIdFamily(rs.getInt("id_classlevel"));

        student.setArchived(rs.getBoolean("archived"));

        return student;
    }
	
}

