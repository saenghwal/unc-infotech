package entity;

import java.sql.Timestamp;
import java.util.Collection;

public class UsersEntity {
    private long id;
    private String username;
    private String password;
    private String name;
    private Timestamp createdAt;
    private String currency;
    private Collection<AuthTokensEntity> authTokensesById;
    private Collection<BudgetsEntity> budgetsesById;
    private Collection<CategoriesEntity> categoriesById;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UsersEntity that = (UsersEntity) o;

        if (id != that.id) return false;
        if (username != null ? !username.equals(that.username) : that.username != null)
            return false;
        if (password != null ? !password.equals(that.password) : that.password != null)
            return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (createdAt != null ? !createdAt.equals(that.createdAt) : that.createdAt != null)
            return false;
        if (currency != null ? !currency.equals(that.currency) : that.currency != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
        result = 31 * result + (currency != null ? currency.hashCode() : 0);
        return result;
    }

    public Collection<AuthTokensEntity> getAuthTokensesById() {
        return authTokensesById;
    }

    public void setAuthTokensesById(Collection<AuthTokensEntity> authTokensesById) {
        this.authTokensesById = authTokensesById;
    }

    public Collection<BudgetsEntity> getBudgetsesById() {
        return budgetsesById;
    }

    public void setBudgetsesById(Collection<BudgetsEntity> budgetsesById) {
        this.budgetsesById = budgetsesById;
    }

    public Collection<CategoriesEntity> getCategoriesById() {
        return categoriesById;
    }

    public void setCategoriesById(Collection<CategoriesEntity> categoriesById) {
        this.categoriesById = categoriesById;
    }
}
