package sr.unasat.library.controller;

import sr.unasat.library.entity.Medicine;
import sr.unasat.library.entity.Patient;
import sr.unasat.library.service.PatientService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("patient")
public class PatientController {
    private PatientService patientService = new PatientService();

    @Path("/list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Patient> findAll() {
        return patientService.getAllPatients();
    }

    @Path("/add")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Patient add(Patient patient) {
        return patientService.insertPatient(patient);
    }

    @Path("/update")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Patient update(Patient patient) {
        return patientService.updatePatient(patient);
    }

    @Path("/remove")
    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Patient remove(Patient patient) {
        return patientService.deletePatient(patient);

    }

    @Path("/getPatient")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Patient getPatient(Patient patient) {
        return patientService.findPatient(patient);
    }

    @Path("/getPatientInfo")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<Patient> getPatientInfo(String patientId) {
        return patientService.getAllPatientInfo(patientId);
    }
}
