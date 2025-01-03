package controller;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Logger;

import dao.PaymentDAO;
import model.Payment;

public class PaymentController {

    private static final Logger LOGGER = Logger.getLogger(PaymentController.class.getName());
    private final PaymentDAO paymentDAO;

    public PaymentController(PaymentDAO paymentDAO) {
        this.paymentDAO = paymentDAO;
    }

    public void createPayment(int idStudent, int idMode, BigDecimal amount, LocalDate paymentDate) throws SQLException {
        Payment payment = new Payment();
        payment.setIdStudent(idStudent);
        payment.setIdMode(idMode);
        payment.setAmount(amount);
        payment.setPaymentDate(paymentDate);
        paymentDAO.create(payment);
    }

    public List<Payment> getAllPayments() throws SQLException {
        return paymentDAO.readAll();
    }

    public Payment getPaymentById(int id) throws SQLException {
        return paymentDAO.read(id);
    }
}
