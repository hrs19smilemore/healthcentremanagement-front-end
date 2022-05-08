package sr.unasat.library.controller;
import sr.unasat.library.projections.MedicineProjection;
import sr.unasat.library.entity.Medicine;
import sr.unasat.library.service.MedicineService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("medicine")
public class MedicineController {
    private final MedicineService medicineService = new MedicineService();

    @Path("/list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Medicine> findAll() {
        return medicineService.getMedicineList();
    }

    @Path("/add")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Medicine add(Medicine medicine) {
        return medicineService.insertNewMedicine(medicine);
    }

    @Path("/update")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Medicine update(Medicine medicine) {
        return medicineService.update(medicine);
    }

    @Path("/remove")
    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Medicine remove(Medicine medicine) {
        return medicineService.deleteMedicine(medicine);
    }

    @Path("/getMedicine")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Medicine getMedicine(Medicine medicine) {
        return medicineService.findMedicine(medicine);
    }

    @Path("/getReport")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<MedicineProjection> findReport() {
        return medicineService.getMedicineReport();
    }

}
