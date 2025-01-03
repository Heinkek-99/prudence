package controller;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Logger;

import dao.ReceiptsDAO;
import model.Receipts;

public class ReceiptsController {

    private static final Logger LOGGER = Logger.getLogger(ReceiptsController.class.getName());
    private final ReceiptsDAO receiptsDAO;

    public ReceiptsController(ReceiptsDAO receiptsDAO) {
        this.receiptsDAO = receiptsDAO;
    }

    public void createReceipt(int idPayment, LocalDate receiptDate, BigDecimal Amount) throws SQLException {
        Receipts receipt = new Receipts();
        receipt.setIdPayment(idPayment);
        receipt.setReceiptDate(receiptDate);
        receipt.setAmount(Amount);
        receiptsDAO.create(receipt);
    }

    public List<Receipts> getAllReceipts() throws SQLException {
        return receiptsDAO.readAll();
    }

    public Receipts getReceiptById(int id) throws SQLException {
        return receiptsDAO.read(id);
    }
}
