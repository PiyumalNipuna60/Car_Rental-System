package lk.ijse.spring.controller;

import lk.ijse.spring.dto.CarRentReturnDTO;
import lk.ijse.spring.service.CarRentReturnService;
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
@RequestMapping("api/v1/carRentReturn")
@CrossOrigin
public class CarRentReturnController {

    @Autowired
    CarRentReturnService service;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil getAllCarRentReturns() {
        return new ResponseUtil(200, "Ok", service.getAllCarRentReturns());
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil saveCarRentReturn(@RequestBody CarRentReturnDTO dto) {
        System.out.println(dto.toString());
        service.saveCarRentReturn(dto);
        return new ResponseUtil(200, "Saved", null);
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil updateCarRentReturn(@RequestBody CarRentReturnDTO dto) {
        service.updateCarRentReturn(dto);
        return new ResponseUtil(200, "Updated", null);
    }

    @DeleteMapping(params = {"returnId"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil deleteCarRentReturn(@RequestParam String returnId) {
        service.deleteCarRentReturn(returnId);
        return new ResponseUtil(200, "Deleted", null);
    }

    @GetMapping(path = "/{returnId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil searchCarRentReturn(@PathVariable String returnId) {
        return new ResponseUtil(200, "OK", service.searchCarRentReturn(returnId));
    }

    @GetMapping(path = "/generateReturnId", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil generateReturnId() {
        return new ResponseUtil(200, "Ok", service.generateReturnId());
    }
}
