package entity;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Collection;

public class RecurringsEntity {
    private long id;
    private BigDecimal amount;
    private String type;
    private Timestamp lastRunAt;
    private Timestamp createdAt;
    private String remark;
    private BudgetTypesEntity budgetTypesByBudgetTypeId;
    private Collection<TransactionsEntity> transactionsesById;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Timestamp getLastRunAt() {
        return lastRunAt;
    }

    public void setLastRunAt(Timestamp lastRunAt) {
        this.lastRunAt = lastRunAt;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RecurringsEntity that = (RecurringsEntity) o;

        if (id != that.id) return false;
        if (amount != null ? !amount.equals(that.amount) : that.amount != null) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;
        if (lastRunAt != null ? !lastRunAt.equals(that.lastRunAt) : that.lastRunAt != null)
            return false;
        if (createdAt != null ? !createdAt.equals(that.createdAt) : that.createdAt != null)
            return false;
        if (remark != null ? !remark.equals(that.remark) : that.remark != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (amount != null ? amount.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (lastRunAt != null ? lastRunAt.hashCode() : 0);
        result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
        result = 31 * result + (remark != null ? remark.hashCode() : 0);
        return result;
    }

    public BudgetTypesEntity getBudgetTypesByBudgetTypeId() {
        return budgetTypesByBudgetTypeId;
    }

    public void setBudgetTypesByBudgetTypeId(BudgetTypesEntity budgetTypesByBudgetTypeId) {
        this.budgetTypesByBudgetTypeId = budgetTypesByBudgetTypeId;
    }

    public Collection<TransactionsEntity> getTransactionsesById() {
        return transactionsesById;
    }

    public void setTransactionsesById(Collection<TransactionsEntity> transactionsesById) {
        this.transactionsesById = transactionsesById;
    }
}
