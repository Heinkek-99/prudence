package controller;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

import dao.PaymentModeDAO;
import model.PaymentMode;

public class PaymentModeController {

    private static final Logger LOGGER = Logger.getLogger(PaymentModeController.class.getName());
    private final PaymentModeDAO paymentModeDAO;

    public PaymentModeController(PaymentModeDAO paymentModeDAO) {
        this.paymentModeDAO = paymentModeDAO;
    }

    public void createPaymentMode(String modeName, Boolean archived) throws SQLException {
        PaymentMode paymentMode = new PaymentMode();
        paymentMode.setModeName(modeName);
        paymentMode.setArchived(false);
        paymentModeDAO.create(paymentMode);
    }

    public List<PaymentMode> getAllPaymentModes() throws SQLException {
        return paymentModeDAO.readAll();
    }

    public PaymentMode getPaymentModeById(int id) throws SQLException {
        return paymentModeDAO.read(id);
    }
}
