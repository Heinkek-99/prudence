/**
 * 
 */
package model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

/**
 * Author : Heidy KENGNE
 */
public class Payment {
	private int idPayment;
	private int idStudent;  // Foreign key to Eleve
    private int idMode;
    private String paymentType;
    private LocalDate paymentDate;
    private BigDecimal amount;
    private boolean archived;
    private Student student;                      // Many-to-One relationship with Eleve
    private PaymentMode paymentMode;
    private List<TypePaiement> typesPaiement;   // One-to-Many relationship with TypePaiement


    // Constructeur
    public Payment(int idPayment, int idStudent, int idMode, BigDecimal amount, LocalDate paymentDate) {
        this.idPayment = idPayment;
        this.idStudent = idStudent;
        this.idMode = idMode;
        this.setAmount(amount);
        this.setPaymentDate(paymentDate);
        this.archived = false;
    }

    public Payment() {
        //TODO Auto-generated constructor stub
    }

    // Getters et Setters
    public int getIdPayment() {
        return idPayment;
    }

    public void setIdPayment(int idPayment) {
        this.idPayment = idPayment;
    }

    public int getIdStudent() {
        return idStudent;
    }

    public void setIdStudent(int idStudent) {
        this.idStudent = idStudent;
    }

    public int getIdMode() {
        return idMode;
    }

    public void setIdMode(int idMode) {
        this.idMode = idMode;
    }

    public boolean isArchived() {
        return archived;
    }

    public void setArchived(boolean archived) {
        this.archived = archived;
    }

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public LocalDate getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(LocalDate paymentDate) {
		this.paymentDate = paymentDate;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}
    
    public PaymentMode getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(PaymentMode paymentMode) {
        this.paymentMode = paymentMode;
    }

	public List<TypePaiement> getTypesPaiement() {
		return typesPaiement;
	}

	public void setTypesPaiement(List<TypePaiement> typesPaiement) {
		this.typesPaiement = typesPaiement;
	}
}
