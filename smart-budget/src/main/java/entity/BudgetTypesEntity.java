package entity;

import java.sql.Timestamp;
import java.util.Collection;

public class BudgetTypesEntity {
    private long id;
    private Timestamp createdAt;
    private Collection<BudgetsEntity> budgetsesById;
    private Collection<RecurringsEntity> recurringsesById;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

        BudgetTypesEntity that = (BudgetTypesEntity) o;

        if (id != that.id) return false;
        if (createdAt != null ? !createdAt.equals(that.createdAt) : that.createdAt != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
        return result;
    }

    public Collection<BudgetsEntity> getBudgetsesById() {
        return budgetsesById;
    }

    public void setBudgetsesById(Collection<BudgetsEntity> budgetsesById) {
        this.budgetsesById = budgetsesById;
    }

    public Collection<RecurringsEntity> getRecurringsesById() {
        return recurringsesById;
    }

    public void setRecurringsesById(Collection<RecurringsEntity> recurringsesById) {
        this.recurringsesById = recurringsesById;
    }
}
