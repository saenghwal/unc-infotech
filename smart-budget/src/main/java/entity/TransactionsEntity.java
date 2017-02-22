package entity;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

public class TransactionsEntity {
    private long id;
    private String name;
    private BigDecimal amount;
    private String remark;
    private boolean auto;
    private Date transactionOn;
    private Timestamp createdAt;
    private BudgetsEntity budgetsByBudgetId;
    private RecurringsEntity recurringsByRecurringId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public boolean isAuto() {
        return auto;
    }

    public void setAuto(boolean auto) {
        this.auto = auto;
    }

    public Date getTransactionOn() {
        return transactionOn;
    }

    public void setTransactionOn(Date transactionOn) {
        this.transactionOn = transactionOn;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TransactionsEntity that = (TransactionsEntity) o;

        if (id != that.id) return false;
        if (auto != that.auto) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (amount != null ? !amount.equals(that.amount) : that.amount != null) return false;
        if (remark != null ? !remark.equals(that.remark) : that.remark != null) return false;
        if (transactionOn != null ? !transactionOn.equals(that.transactionOn) : that.transactionOn != null)
            return false;
        if (createdAt != null ? !createdAt.equals(that.createdAt) : that.createdAt != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (amount != null ? amount.hashCode() : 0);
        result = 31 * result + (remark != null ? remark.hashCode() : 0);
        result = 31 * result + (auto ? 1 : 0);
        result = 31 * result + (transactionOn != null ? transactionOn.hashCode() : 0);
        result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
        return result;
    }

    public BudgetsEntity getBudgetsByBudgetId() {
        return budgetsByBudgetId;
    }

    public void setBudgetsByBudgetId(BudgetsEntity budgetsByBudgetId) {
        this.budgetsByBudgetId = budgetsByBudgetId;
    }

    public RecurringsEntity getRecurringsByRecurringId() {
        return recurringsByRecurringId;
    }

    public void setRecurringsByRecurringId(RecurringsEntity recurringsByRecurringId) {
        this.recurringsByRecurringId = recurringsByRecurringId;
    }
}
