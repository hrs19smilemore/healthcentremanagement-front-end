package sr.unasat.library.template;

import sr.unasat.library.entity.Medicine;
import sr.unasat.library.entity.Patient;
import sr.unasat.library.entity.Prescription;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class PrescriptionTemplate {

    public final Map<String, List<?>> check(Prescription prescription) {
        Map<String, List<?>> map = new HashMap<>();
        List<Medicine> medicines = checkIfMedicineExist(prescription);
        List<Patient> patients = checkIfPatientExist(prescription);
        map.put("medicines", medicines);
        map.put("patients", patients);
        return map;
    }

    public abstract List<Medicine> checkIfMedicineExist(Prescription prescription);

    public abstract List<Patient> checkIfPatientExist(Prescription prescription);


}
