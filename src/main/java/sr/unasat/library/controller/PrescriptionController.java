package sr.unasat.library.controller;

import sr.unasat.library.projections.PrescriptionProjection;
import sr.unasat.library.entity.Prescription;
import sr.unasat.library.service.PrescriptionServiceImpl;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/prescription")
public class PrescriptionController {
    private final PrescriptionServiceImpl prescriptionService = new PrescriptionServiceImpl();

    @Path("/list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Prescription> findAll() {
        return prescriptionService.getAllPrescriptions();
    }

    @Path("/getPrescriptionsByYear")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<PrescriptionProjection> findBasedOnYear(int year) {
        return prescriptionService.returnPrescriptionsGroupedByMedicinePerYear(year);
    }
    @Path("/getPrescriptionsBy1stHalfOfYear")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<PrescriptionProjection> findBasedOn1stHalfOfYear(int year) {
        return prescriptionService.returnPrescriptionsGroupedByMedicineFor1stHalfOfYear(year);
    }

    @Path("/getPrescriptionsBy2ndHalfOfYear")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<PrescriptionProjection> findBasedOn2ndHalfOfYear(int year) {
        return prescriptionService.returnPrescriptionsGroupedByMedicineFor2ndHalfOfYear(year);
    }

    @Path("/getPrescriptionsBy1stQuarterOfYear")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<PrescriptionProjection> findBasedOn1stQuarterOfYear(int year) {
        return prescriptionService.returnPrescriptionsGroupedByMedicineFor1stQuarterOfYear(year);
    }

    @Path("/getPrescriptionsBy2ndQuarterOfYear")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<PrescriptionProjection> findBasedOn2ndQuarterOfYear(int year) {
        return prescriptionService.returnPrescriptionsGroupedByMedicineFor2ndQuarterOfYear(year);
    }

    @Path("/getPrescriptionsBy3rdQuarterOfYear")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<PrescriptionProjection> findBasedOn3rdQuarterOfYear(int year) {
        return prescriptionService.returnPrescriptionsGroupedByMedicineFor3rdQuarterOfYear(year);
    }

    @Path("/getPrescriptionsBy4thQuarterOfYear")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<PrescriptionProjection> findBasedOn4thQuarterOfYear(int year) {
        return prescriptionService.returnPrescriptionsGroupedByMedicineFor4thQuarterOfYear(year);
    }

    @Path("/getPrescription")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Prescription findPrescription(Prescription prescription) {
        return prescriptionService.findPrescription(prescription);
    }

    @Path("/add")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Prescription add(Prescription prescription) {
        return prescriptionService.insertPrescription(prescription);
    }

    @Path("/update")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Prescription update(Prescription prescription) {
        return prescriptionService.update(prescription);
    }

    @Path("/remove")
    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Prescription remove(Prescription prescription) {
        return prescriptionService.deletePrescription(prescription);
    }
}

