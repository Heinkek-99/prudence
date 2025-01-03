package controller;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

import dao.FamilyDAO;
import model.Family;

public class FamilyController {

    private static final Logger LOGGER = Logger.getLogger(FamilyController.class.getName());
    private final FamilyDAO familyDAO;

    public FamilyController(FamilyDAO familyDAO) {
        this.familyDAO = familyDAO;
    }

    public void createFamily(String nomParent, String prenomParent, String titre, String adresse1, 
    String codeVille, String telephone, String cnie) throws SQLException {
        Family family = new Family();
        family.setNomParent(nomParent);
        family.setPrenomParent(prenomParent);
        family.setTitre(titre);
        family.setAdresse1(adresse1);
        family.setCodeVille(codeVille);
        family.setTelephone(telephone);
        family.setCnie(cnie);
        familyDAO.create(family);
    }

    public List<Family> getAllFamilies() throws SQLException {
        return familyDAO.readAll();
    }

    public Family getFamilyById(int id) throws SQLException {
        return familyDAO.read(id);
    }
}
