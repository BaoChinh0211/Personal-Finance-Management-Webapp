package bc.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "Category", schema = "dbo", catalog = "PersonalFinanceManagementWebapp")
public class CategoryEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "CategoryId")
    private int categoryId;
    @Basic
    @Column(name = "CategoryName")
    private String categoryName;
    @Basic
    @Column(name = "CategoryType")
    private boolean categoryType;
    @Basic
    @Column(name = "UserId")
    private int userId;

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public boolean isCategoryType() {
        return categoryType;
    }

    public void setCategoryType(boolean categoryType) {
        this.categoryType = categoryType;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CategoryEntity that = (CategoryEntity) o;
        return categoryId == that.categoryId && categoryType == that.categoryType && userId == that.userId && Objects.equals(categoryName, that.categoryName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(categoryId, categoryName, categoryType, userId);
    }
}
