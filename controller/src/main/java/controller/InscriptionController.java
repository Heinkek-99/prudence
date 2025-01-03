package controller;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Logger;

import dao.InscriptionDAO;
import model.Inscription;

public class InscriptionController {

    private static final Logger LOGGER = Logger.getLogger(InscriptionController.class.getName());
    private final InscriptionDAO inscriptionDAO;

    public InscriptionController(InscriptionDAO inscriptionDAO) {
        this.inscriptionDAO = inscriptionDAO;
    }

    public void createInscription(int idStudent, LocalDate dateDebutInscription, LocalDate dateFinInscription, BigDecimal fraisInscription,
        BigDecimal fraisTransport, BigDecimal montantInscription, Boolean archived ) throws SQLException {
        Inscription inscription = new Inscription();
        inscription.setIdStudent(idStudent);
        inscription.setDateDebutInscription(dateDebutInscription);
        inscription.setDateFinInscription(dateFinInscription);
        inscription.setFraisInscription(fraisInscription);
        inscription.setFraisTransport(fraisTransport);
        inscription.setMontantInscription(montantInscription);
        inscription.setArchived(false);
        inscriptionDAO.create(inscription);
    }

    public List<Inscription> getAllInscriptions() throws SQLException {
        return inscriptionDAO.readAll();
    }

    public Inscription getInscriptionById(int id) throws SQLException {
        return inscriptionDAO.read(id);
    }
}
