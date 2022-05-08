package sr.unasat.library.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "medicine", schema = "healthcentremanagement-frontend")
public class Medicine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "medicineId")
    private int id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "brand", nullable = false)
    private String brand;
    @Column(name = "description")
    private String description;
    @Column(name = "stock")
    private int stock;

    @OneToMany(mappedBy = "medicine")
    @JsonIgnore
    private Set<Prescription> prescriptions;

    public Medicine() {
    }


    public Medicine(int id, String name, String brand, String description, int stock, Set<Prescription> prescriptions) {
        this.id = id;
        this.name = name;
        this.brand = brand;
        this.description = description;
        this.stock = stock;
        this.prescriptions = prescriptions;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Set<Prescription> getPrescriptions() {
        return prescriptions;
    }

    public void setPrescriptions(Set<Prescription> prescriptions) {
        this.prescriptions = prescriptions;
    }

    @Override
    public String toString() {
        return "Medicine{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", brand='" + brand + '\'' +
                ", description='" + description + '\'' +
                ", stock=" + stock +
                '}';
    }
}
