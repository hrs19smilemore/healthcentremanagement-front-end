package sr.unasat.library.adapter;

import sr.unasat.library.entity.Medicine;
import sr.unasat.library.entity.Patient;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MedicineAndPatientListAdapterImplementation implements MedicineAndPatientListAdapter {
    Map<String, List<?>> map;

    public MedicineAndPatientListAdapterImplementation(Map<String, List<?>> map) {
        this.map = map;
    }

    @Override
    public List<Medicine> wildcardToMedicineList() {
        List<Medicine> medicines = new ArrayList<>();
        map.get("medicines").forEach(o -> {
            Medicine medicine = (Medicine) o;
            medicines.add(medicine);
        });
        return medicines;
    }

    @Override
    public List<Patient> wildcardToPatientList() {
        List<Patient> patients = new ArrayList<>();
        map.get("patients").forEach(o -> {
            Patient patient = (Patient) o;
            patients.add(patient);
        });
        return patients;
    }
}
