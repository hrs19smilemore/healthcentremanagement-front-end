package sr.unasat.library.builders;

import sr.unasat.library.entity.Medicine;
import sr.unasat.library.entity.Prescription;

import java.util.Set;

public class MedicineBuilder {
    private final int id;
    private final String name;
    private final String brand;
    private final String description;
    private final int stock;
    private final Set<Prescription> prescriptions;

    private MedicineBuilder(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.brand = builder.brand;
        this.description = builder.description;
        this.stock = builder.stock;
        this.prescriptions = builder.prescriptions;
        //Medicine medicine = new Medicine(builder.id, builder.name, builder.brand, builder.description, builder.stock, builder.prescriptions);
    }

    public static class Builder {
        private int id;
        private String name;
        private String brand;
        private String description;
        private int stock;
        private Set<Prescription> prescriptions;

        public Builder id(final int id) {
            this.id = id;
            return this;
        }

        public Builder name(final String name) {
            this.name = name;
            return this;
        }

        public Builder brand(final String brand) {
            this.brand = brand;
            return this;
        }

        public Builder description(final String description) {
            this.description = description;
            return this;
        }

        public Builder stock(final int stock) {
            this.stock = stock;
            return this;
        }

        public Builder prescriptions(final Set<Prescription> prescriptions) {
            this.prescriptions = prescriptions;
            return this;
        }

        public MedicineBuilder build() {
            return new MedicineBuilder(this);
        }
    }

    public Medicine getMedicine(){
        return new Medicine(this.id, this.name, this.brand, this.description, this.stock, this.prescriptions);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getBrand() {
        return brand;
    }

    public String getDescription() {
        return description;
    }

    public int getStock() {
        return stock;
    }

    public Set<Prescription> getPrescriptions() {
        return prescriptions;
    }
}
