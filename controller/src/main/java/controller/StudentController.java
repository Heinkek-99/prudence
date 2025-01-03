package controller;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Logger;

import dao.StudentDAO;
import model.Student;

public class StudentController {
    private static final Logger LOGGER = Logger.getLogger(StudentController.class.getName());
    
    private final StudentDAO studentDAO;

    public StudentController(StudentDAO studentDAO) {
        this.studentDAO = studentDAO;
    }

    public void createStudent(String matricule, String firstName, String lastName, String gender,
                            LocalDate dateOfBirth, String address, String phone,
                            String nationality, String photoPath, int idFamily, int idInscription, int idClassLevel) throws SQLException {
        Student student = new Student(matricule, firstName, lastName, gender,
                                   dateOfBirth, address, phone, nationality, 
                                   photoPath, idFamily, idInscription, idClassLevel);
        studentDAO.create(student);
        LOGGER.info("Étudiant créé avec succès : " + firstName + " " + lastName);
    }

    public Student getStudent(int id) throws SQLException {
        Student student = studentDAO.read(id);
        if (student != null) {
            LOGGER.info("Étudiant trouvé : ID " + id);
        } else {
            LOGGER.warning("Aucun étudiant trouvé avec l'ID : " + id);
        }
        return student;
    }

    public List<Student> getAllStudents() throws SQLException {
        List<Student> students = studentDAO.readAll();
        LOGGER.info("Nombre total d'étudiants récupérés : " + students.size());
        return students;
    }

    public void updateStudent(Student student) throws SQLException {
        studentDAO.update(student);
        LOGGER.info("Étudiant mis à jour : ID " + student.getIdStudent());
    }

    public void archiveStudent(int id) throws SQLException {
        Student student = studentDAO.read(id);
        if (student != null) {
            student.setArchived(true);
            studentDAO.update(student);
            LOGGER.info("Étudiant archivé : ID " + id);
        } else {
            LOGGER.warning("Impossible d'archiver : aucun étudiant trouvé avec l'ID " + id);
        }
    }
}
