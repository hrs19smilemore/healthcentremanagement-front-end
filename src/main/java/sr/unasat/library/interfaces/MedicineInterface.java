package sr.unasat.library.interfaces;

import sr.unasat.library.entity.Medicine;
import sr.unasat.library.projections.MedicineProjection;

import java.util.List;

public interface MedicineInterface {

    List<Medicine> getMedicineList();
    List<MedicineProjection> getMedicineReport();
    Medicine insertNewMedicine (Medicine medicine);
    Medicine deleteMedicine (int medicineId);
    Medicine findMedicine(int medicineId);
    Medicine update(Medicine medicine);
}
