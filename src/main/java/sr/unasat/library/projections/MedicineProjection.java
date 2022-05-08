package sr.unasat.library.projections;

public class MedicineProjection {
    private String medicineName;
    private String medicineBrand;
    private int stock;
    private long currentStock;

    public MedicineProjection(String medicineName, String medicineBrand, int stock, long currentStock) {
        this.medicineName = medicineName;
        this.medicineBrand = medicineBrand;
        this.stock = stock;
        this.currentStock = currentStock;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    public String getMedicineBrand() {
        return medicineBrand;
    }

    public void setMedicineBrand(String medicineBrand) {
        this.medicineBrand = medicineBrand;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public long getCurrentStock() {
        return currentStock;
    }

    public void setCurrentStock(long currentStock) {
        this.currentStock = currentStock;
    }
}
