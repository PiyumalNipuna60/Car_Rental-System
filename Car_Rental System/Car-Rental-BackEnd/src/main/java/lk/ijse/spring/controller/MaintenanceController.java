package lk.ijse.spring.controller;

import lk.ijse.spring.dto.MaintenanceDTO;
import lk.ijse.spring.service.MaintenanceService;
import lk.ijse.spring.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * @author : M-Prageeth
 * @created : 07/07/2022 - 7:18 PM
 **/
@RestController
@RequestMapping("api/v1/maintenance")
@CrossOrigin
public class MaintenanceController {

    @Autowired
    MaintenanceService service;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil getAllMaintenance() {
        return new ResponseUtil(200, "Ok", service.getAllMaintenances());
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil addMaintenance(@RequestBody MaintenanceDTO dto) {
        System.out.println(dto.toString());
        service.addMaintenance(dto);
        return new ResponseUtil(200, "Saved", null);
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil updateMaintenance(@RequestBody MaintenanceDTO dto) {
        service.updateMaintenance(dto);
        return new ResponseUtil(200, "Updated", null);
    }

    @DeleteMapping(params = {"maintenanceId"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil deleteMaintenance(@RequestParam String maintenanceId) {
        service.deleteMaintenance(maintenanceId);
        return new ResponseUtil(200, "Deleted", null);
    }

    @GetMapping(path = "/{maintenanceId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil searchMaintenance(@PathVariable String maintenanceId) {
        return new ResponseUtil(200, "Ok", service.searchMaintenance(maintenanceId));
    }

    @GetMapping(path = "/generateMaintenanceId", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil generateMaintenanceId() {
        return new ResponseUtil(200, "Ok", service.generateMaintenanceId());
    }

    @PutMapping(path = "/{maintenanceId}/{cost}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil updateMaintenanceCost(@PathVariable String maintenanceId, @PathVariable double cost) {
        service.updateMaintenanceCost(maintenanceId, cost);
        return new ResponseUtil(200, "Updated", null);
    }

    @GetMapping(path = "/underMaintenances", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil getAllUnderMaintenances() {
        return new ResponseUtil(200, "Ok", service.getAllUnderMaintenances());
    }

    @GetMapping(path = "/getAll/{fromDate}/{toDate}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil getAllMaintenancesByDateRange(@PathVariable String fromDate, @PathVariable String toDate) {
        return new ResponseUtil(200, "Ok", service.getAllMaintenancesByDateRange(fromDate, toDate));
    }

    @GetMapping(path = "/sum/{fromDate}/{toDate}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil getSumOfMaintenanceAmount(@PathVariable String fromDate, @PathVariable String toDate) {
        return new ResponseUtil(200, "Ok", service.getSumOfMaintenanceAmount(fromDate, toDate));
    }
}
