package controller;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

import dao.TeacherDAO;
import model.Teacher;

public class TeacherController {

    private static final Logger LOGGER = Logger.getLogger(TeacherController.class.getName());
    private final TeacherDAO teacherDAO;

    public TeacherController(TeacherDAO teacherDAO) {
        this.teacherDAO = teacherDAO;
    }

    public void createTeacher(String firstName, String lastName, String email, String subject, String phone, Boolean archived) throws SQLException {
        Teacher teacher = new Teacher();
        teacher.setFirstName(firstName);
        teacher.setLastName(lastName);
        teacher.setSubject(subject);
        teacher.setPhone(phone);
        teacher.setEmail(email);
        teacher.setArchived(archived);
        teacherDAO.create(teacher);
    }

    public List<Teacher> getAllTeacher() throws SQLException {
        return teacherDAO.readAll();
    }

    public Teacher getTeacherById(int id) throws SQLException {
        return teacherDAO.read(id);
    }
}
