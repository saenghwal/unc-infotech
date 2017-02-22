package entity;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Collection;

public class BudgetsEntity {
    private long id;
    private String name;
    private BigDecimal projected;
    private BigDecimal actual;
    private Date periodOn;
    private Timestamp createdAt;
    private UsersEntity usersByUserId;
    private CategoriesEntity categoriesByCategoryId;
    private BudgetTypesEntity budgetTypesByTypeId;
    private Collection<TransactionsEntity> transactionsesById;

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

    public BigDecimal getProjected() {
        return projected;
    }

    public void setProjected(BigDecimal projected) {
        this.projected = projected;
    }

    public BigDecimal getActual() {
        return actual;
    }

    public void setActual(BigDecimal actual) {
        this.actual = actual;
    }

    public Date getPeriodOn() {
        return periodOn;
    }

    public void setPeriodOn(Date periodOn) {
        this.periodOn = periodOn;
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

        BudgetsEntity that = (BudgetsEntity) o;

        if (id != that.id) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (projected != null ? !projected.equals(that.projected) : that.projected != null)
            return false;
        if (actual != null ? !actual.equals(that.actual) : that.actual != null) return false;
        if (periodOn != null ? !periodOn.equals(that.periodOn) : that.periodOn != null)
            return false;
        if (createdAt != null ? !createdAt.equals(that.createdAt) : that.createdAt != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (projected != null ? projected.hashCode() : 0);
        result = 31 * result + (actual != null ? actual.hashCode() : 0);
        result = 31 * result + (periodOn != null ? periodOn.hashCode() : 0);
        result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
        return result;
    }

    public UsersEntity getUsersByUserId() {
        return usersByUserId;
    }

    public void setUsersByUserId(UsersEntity usersByUserId) {
        this.usersByUserId = usersByUserId;
    }

    public CategoriesEntity getCategoriesByCategoryId() {
        return categoriesByCategoryId;
    }

    public void setCategoriesByCategoryId(CategoriesEntity categoriesByCategoryId) {
        this.categoriesByCategoryId = categoriesByCategoryId;
    }

    public BudgetTypesEntity getBudgetTypesByTypeId() {
        return budgetTypesByTypeId;
    }

    public void setBudgetTypesByTypeId(BudgetTypesEntity budgetTypesByTypeId) {
        this.budgetTypesByTypeId = budgetTypesByTypeId;
    }

    public Collection<TransactionsEntity> getTransactionsesById() {
        return transactionsesById;
    }

    public void setTransactionsesById(Collection<TransactionsEntity> transactionsesById) {
        this.transactionsesById = transactionsesById;
    }
}
