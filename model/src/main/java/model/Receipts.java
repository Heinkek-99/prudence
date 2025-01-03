package model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Receipts {
    private int idReceipt;
    private int idPayment;
    private LocalDate ReceiptDate;
    private BigDecimal Amount;
    private Payment payment;

    public Receipts(){}

    public int getIdReceipt() {
        return idReceipt;
    }

    public void setIdReceipt(int idReceipt) {
        this.idReceipt = idReceipt;
    }

    public int getIdPayment() {
        return idPayment;
    }
    
    public void setIdPayment(int idPayment) {
        this.idPayment = idPayment;
    }

    public BigDecimal getAmount() {
        return Amount;
    }

    public void setAmount(BigDecimal amount) {
        Amount = amount;
    }

    public LocalDate getReceiptDate() {
        return ReceiptDate;
    }

    public void setReceiptDate(LocalDate receiptDate) {
        ReceiptDate = receiptDate;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

}
