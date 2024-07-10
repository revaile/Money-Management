package main.uangku.models.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Income")
public class Income {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "value", nullable = false)
    private int value;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "transactionDate", nullable = false)
    private Date date;

    @Column(name = "category", nullable = false)
    private String category;

    

    public Income(Long id, int value, String description, Date date, String category) {
        this.id = id;
        this.value = value;
        this.description = description;
        this.date = date;
        this.category = category;
    }

    public Income() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Income [id=" + id + ", value=" + value + ", description=" + description + ", date=" + date
                + ", category=" + category + "]";
    }

}
