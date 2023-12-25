package bc.entity;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "Financial", schema = "dbo", catalog = "PersonalFinanceManagementWebapp")
public class FinancialEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "FinancialId")
    private int financialId;
    @Basic
    @Column(name = "Amount")
    private int amount;
    @Basic
    @Column(name = "DateTime")
    private Date dateTime;
    @Basic
    @Column(name = "Note")
    private String note;
    @Basic
    @Column(name = "CategoryId")
    private int categoryId;

    public int getFinancialId() {
        return financialId;
    }

    public void setFinancialId(int financialId) {
        this.financialId = financialId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FinancialEntity that = (FinancialEntity) o;
        return financialId == that.financialId && amount == that.amount && categoryId == that.categoryId && Objects.equals(dateTime, that.dateTime) && Objects.equals(note, that.note);
    }

    @Override
    public int hashCode() {
        return Objects.hash(financialId, amount, dateTime, note, categoryId);
    }
}
