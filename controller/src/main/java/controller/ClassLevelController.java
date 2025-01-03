package controller;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

import dao.ClassLevelDAO;
import model.ClassLevel;

public class ClassLevelController {

    private static final Logger LOGGER = Logger.getLogger(ClassLevelController.class.getName());
    private final ClassLevelDAO classLevelDAO;

    public ClassLevelController(ClassLevelDAO classLevelDAO) {
        this.classLevelDAO = classLevelDAO;
    }

    public void createClassLevel( String levelName, String description, int idReferent) throws SQLException {
        ClassLevel classLevel = new ClassLevel();
        classLevel.setLevelName(levelName);
        classLevel.setDescription(description);
        classLevel.setidReferent(idReferent);
        classLevelDAO.create(classLevel);
    }

    public List<ClassLevel> getAllClassLevels() throws SQLException {
        return classLevelDAO.readAll();
    }

    public ClassLevel getClassLevelById(int id) throws SQLException {
        return classLevelDAO.read(id);
    }
}
