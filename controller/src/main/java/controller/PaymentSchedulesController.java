package controller;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Logger;

import dao.PaymentSchedulesDAO;
import model.PaymentSchedules;

public class PaymentSchedulesController {

    private static final Logger LOGGER = Logger.getLogger(PaymentSchedulesController.class.getName());
    private final PaymentSchedulesDAO paymentSchedulesDAO;

    public PaymentSchedulesController(PaymentSchedulesDAO paymentSchedulesDAO) {
        this.paymentSchedulesDAO = paymentSchedulesDAO;
    }

    public void createPaymentSchedule(int idStudent, LocalDate dueDate, BigDecimal amount, String status) throws SQLException {
        PaymentSchedules schedule = new PaymentSchedules();
        schedule.setIdStudent(idStudent);
        schedule.setDueDate(null);
        schedule.setAmount(amount);
        schedule.setStatus(status);
        paymentSchedulesDAO.create(schedule);
    }

    public List<PaymentSchedules> getAllPaymentSchedules() throws SQLException {
        return paymentSchedulesDAO.readAll();
    }

    public PaymentSchedules getPaymentScheduleById(int id) throws SQLException {
        return paymentSchedulesDAO.read(id);
    }
}
