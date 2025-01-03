package controller;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Logger;

import dao.PaiementStudentDAO;
import model.PaiementStudent;

public class PaiementStudentController {

    private static final Logger LOGGER = Logger.getLogger(PaiementStudentController.class.getName());
    private final PaiementStudentDAO paiementStudentDAO;

    public PaiementStudentController(PaiementStudentDAO paiementStudentDAO) {
        this.paiementStudentDAO = paiementStudentDAO;
    }

    public void createPaiementStudent(int idInscription, String typePaiementStudent, LocalDate datePaiementStudent, BigDecimal montant) throws SQLException {
        PaiementStudent paiementStudent = new PaiementStudent();
        paiementStudent.setIdInscription(idInscription);
        paiementStudent.setTypePaiementStudent(typePaiementStudent);
        paiementStudent.setDatePaiementStudent(datePaiementStudent);
        paiementStudent.setMontant(montant);
        paiementStudent.setArchived(false);

        paiementStudentDAO.create(paiementStudent);
    }

    public List<PaiementStudent> getAllPaiementStudents() throws SQLException {
        return paiementStudentDAO.readAll();
    }

    public PaiementStudent getPaiementStudentById(int id) throws SQLException {
        return paiementStudentDAO.read(id);
    }
}
