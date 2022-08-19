package sr.unasat.library.adapter;

import sr.unasat.library.entity.Medicine;
import sr.unasat.library.entity.Patient;

import java.util.List;

public interface MedicineAndPatientListAdapter {
    List<Medicine> wildcardToMedicineList();

    List<Patient> wildcardToPatientList();
}
