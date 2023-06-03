package lk.ijse.spring.controller;

import lk.ijse.spring.dto.CarRentDTO;
import lk.ijse.spring.service.CarRentService;
import lk.ijse.spring.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * @author : M-Prageeth
 * @created : 07/07/2022 - 7:16 PM
 **/
@RestController
@RequestMapping("api/v1/CarRent")
@CrossOrigin
public class CarRentController {

    @Autowired
    CarRentService service;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil getAllCarRents() {
        return new ResponseUtil(200, "Ok", service.getAllCarRents());
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil addCarRent(@RequestBody CarRentDTO dto) {
        System.out.println(dto.toString());
        service.addCarRent(dto);
        return new ResponseUtil(200, "Saved", null);
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil updateCarRent(@RequestBody CarRentDTO dto) {
        service.updateCarRent(dto);
        return new ResponseUtil(200, "Updated", null);
    }

    @DeleteMapping(params = {"rentId"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil deleteCarRent(@RequestParam String rentId) {
        service.deleteCarRent(rentId);
        return new ResponseUtil(200, "Deleted", null);
    }

    @GetMapping(path = "/{rentId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil searchCarRent(@PathVariable String rentId) {
        return new ResponseUtil(200, "Ok", service.searchCarRent(rentId));
    }

    @PutMapping(path = "/{rentId}/{status}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil updateCarRentStatus(@PathVariable String rentId, @PathVariable String status) {
        service.updateCarRentStatus(rentId, status);
        return new ResponseUtil(200, "Ok", null);
    }

    @GetMapping(path = "/get/{status}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil getAllCarRentsByStatus(@PathVariable String status) {
        return new ResponseUtil(200, "Ok", service.getCarRentsByStatus(status));
    }

    @GetMapping(path = "/getCarRents/{status}/{licenceNo}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil getAllCarRentsByDrivingLicence(@PathVariable String status, @PathVariable String licenceNo) {
        return new ResponseUtil(200, "Ok", service.getCarRentsByDrivingLicenceNo(status, licenceNo));
    }

    @GetMapping(path = "/generateRentId", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil generateRentId() {
        return new ResponseUtil(200, "Ok", service.generateRentId());
    }

    @GetMapping(path = "/countTodayBookings/{today}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil getTodayBookingCount(@PathVariable String today) {
        System.out.println(today);
        return new ResponseUtil(200, "Ok", service.getTodayBookingCount(today));
    }

    @GetMapping(path = "/getTodayBookings/{today}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil getTodayBookings(@PathVariable String today) {
        return new ResponseUtil(200, "Ok", service.getTodayBookings(today));
    }

    @GetMapping(path = "/getMyCarRents/{customerId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil getMyCarRents(@PathVariable String customerId) {
        return new ResponseUtil(200, "Ok", service.getCarRentsByCustomerId(customerId));
    }
}
