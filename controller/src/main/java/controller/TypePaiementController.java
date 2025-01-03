package controller;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

import dao.TypePaiementDAO;
import model.TypePaiement;

public class TypePaiementController {

    private static final Logger LOGGER = Logger.getLogger(TypePaiementController.class.getName());
    private final TypePaiementDAO typePaiementDAO;

    public TypePaiementController(TypePaiementDAO typePaiementDAO) {
        this.typePaiementDAO = typePaiementDAO;
    }

    public void createTypePaiement(String type, Boolean archived) throws SQLException {
        TypePaiement typePaiement = new TypePaiement();
        typePaiement.setTypePaiement(type);
        typePaiement.setArchived(false);
        typePaiementDAO.create(typePaiement);
    }

    public List<TypePaiement> getAllTypePaiements() throws SQLException {
        return typePaiementDAO.readAll();
    }

    public TypePaiement getTypePaiementById(int id) throws SQLException {
        return typePaiementDAO.read(id);
    }
}
