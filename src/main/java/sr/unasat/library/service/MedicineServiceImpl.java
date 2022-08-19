package sr.unasat.library.service;

import sr.unasat.library.JPAConfiguration;
import sr.unasat.library.builders.MedicineBuilder;
import sr.unasat.library.interfaces.MedicineInterface;
import sr.unasat.library.projections.MedicineProjection;
import sr.unasat.library.dao.MedicineDao;
import sr.unasat.library.entity.Medicine;

import java.util.List;

public class MedicineServiceImpl implements MedicineInterface {
    MedicineDao medicineDao = new MedicineDao(JPAConfiguration.getEntityManager());

    @Override
    public List<Medicine> getMedicineList() {
        return medicineDao.retrieveList();
    }

    @Override
    public List<MedicineProjection> getMedicineReport() {
        return medicineDao.getMedicineReport();
    }

    @Override
    public Medicine insertNewMedicine(Medicine medicine) {
        return medicineDao.insert(medicine);
    }

    @Override
    public Medicine deleteMedicine(int medicineId) {
        Medicine medicine = new MedicineBuilder.Builder().id(medicineId).build().getMedicine();
        int index = -1;

        for (int i = 0; i < getMedicineList().size(); i++) {
            if (getMedicineList().get(i).getId() == medicine.getId()) {
                index = i;
                break;
            }
        }
        Medicine medicineTemp = new Medicine();
        medicineTemp = getMedicineList().get(index);


        medicineDao.delete(medicine);
        return medicineTemp;

    }

    @Override
    public Medicine findMedicine(int medicineId) {
        Medicine medicine = new MedicineBuilder.Builder().id(medicineId).build().getMedicine();
        Medicine foundMedicine = new Medicine();
        for (int i = 0; i < getMedicineList().size(); i++) {
            if (getMedicineList().get(i).getId() == medicine.getId()) {
                foundMedicine = getMedicineList().get(i);
            }
        }
        return foundMedicine;
    }

    @Override
    public Medicine update(Medicine medicine) {
        for (int i = 0; i < getMedicineList().size(); i++) {
            if (getMedicineList().get(i).getId() == medicine.getId()) {
                medicineDao.update(medicine);
                break;
            }
        }
        return medicine;


    }
}


