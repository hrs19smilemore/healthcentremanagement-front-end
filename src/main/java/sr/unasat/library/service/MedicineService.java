package sr.unasat.library.service;

import sr.unasat.library.JPAConfiguration;
import sr.unasat.library.projections.MedicineProjection;
import sr.unasat.library.dao.MedicineDao;
import sr.unasat.library.entity.Medicine;
import java.util.List;

public class MedicineService {
    MedicineDao medicineDao = new MedicineDao(JPAConfiguration.getEntityManager());

    public List<Medicine> getMedicineList (){
        return medicineDao.retrieveMedicineList();
    }

    public List<MedicineProjection> getMedicineReport(){
        return medicineDao.getMedicineReport();
    }

    public Medicine insertNewMedicine (Medicine medicine){
            System.out.println("Medicine inserted: " + medicineDao.insertOneRecord(medicine));
            return  medicineDao.insertOneRecord(medicine);
        }

        public Medicine deleteMedicine (Medicine medicine) {
            int index = -1;

            for (int i = 0; i < getMedicineList().size(); i++) {
                if (getMedicineList().get(i).getId() == medicine.getId()) {
                    index = i;
                    break;
                }
            }
            Medicine medicineTemp = new Medicine();
            medicineTemp = getMedicineList().get(index);


            medicineDao.deleteOneRecord(medicine);
            return medicineTemp;

        }

        public Medicine findMedicine(Medicine medicine) {
            Medicine foundMedicine = new Medicine();
            for (int i = 0; i < getMedicineList().size(); i++){
                if(getMedicineList().get(i).getId() == medicine.getId())
                {
                    foundMedicine = getMedicineList().get(i);
                }
            }
            return foundMedicine;
        }

        public Medicine update(Medicine medicine) {
            for (int i = 0; i < getMedicineList().size(); i++) {
                if (getMedicineList().get(i).getId() == medicine.getId()) {
                    medicineDao.updateMedicine(medicine);
                    break;
                }
            }
            return medicine;


        }
}


