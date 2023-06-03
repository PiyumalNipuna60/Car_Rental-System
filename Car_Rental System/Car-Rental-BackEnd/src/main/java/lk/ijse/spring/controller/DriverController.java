package lk.ijse.spring.controller;

import lk.ijse.spring.dto.DriverDTO;
import lk.ijse.spring.service.DriverService;
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
@RequestMapping("api/v1/driver")
@CrossOrigin
public class DriverController {
    @Autowired
    DriverService service;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil getAllDrivers() {
        return new ResponseUtil(200, "Ok", service.getAllDrivers());
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil saveDriver(@RequestBody DriverDTO dto) {
        System.out.println(dto.toString());
        service.saveDriver(dto);
        return new ResponseUtil(200, "Saved", null);
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil updateCustomer(@RequestBody DriverDTO dto) {
        service.updateDriver(dto);
        return new ResponseUtil(200, "Updated", null);
    }

    @DeleteMapping(params = {"licenceNo"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil deleteDriver(@RequestParam String licenceNo) {
        service.deleteDriver(licenceNo);
        return new ResponseUtil(200, "Deleted", null);
    }

    @GetMapping(path = "/{licenceNo}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil searchDriver(@PathVariable String licenceNo) {
        return new ResponseUtil(200, "Ok", service.searchDriver(licenceNo));
    }

    @GetMapping(path = "/{username}/{password}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil searchDriverByUsernameAndPassword(@PathVariable String username, @PathVariable String password) {
        if (service.findDriverByUsername(username)) {
            if (service.findDriverByPassword(password)) {
                return new ResponseUtil(200, "Login Successful", true);
            } else {
                return new ResponseUtil(404, "Incorrect Password", false);
            }
        } else {
            return new ResponseUtil(404, "Incorrect Username", false);
        }
    }

    @GetMapping(path = "/set/{username}/{password}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil findDriverByUsernameAndPassword(@PathVariable String username, @PathVariable String password) {
        return new ResponseUtil(200, "Ok", service.findDriverByUsernameAndPassword(username, password));
    }

    @PutMapping(path = "/updateAvailable/{licenceNo}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil updateDriverAvailable(@PathVariable String licenceNo){
        service.updateDriverAvailable(licenceNo);
        return new ResponseUtil(200,"Updated",null);
    }

    @PutMapping(path = "/updateNonAvailable/{licenceNo}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil updateDriverNonAvailable(@PathVariable String licenceNo){
        service.updateDriverNonAvailable(licenceNo);
        return new ResponseUtil(200,"Ok",null);
    }

    @GetMapping(path = "/getAllAvailableDrivers",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil getAllAvailableDrivers(){
        return new ResponseUtil(200,"Ok",service.getAllAvailableDrivers());
    }

    @GetMapping(path = "/getAllNonAvailableDrivers",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil getAllNonAvailableDrivers(){
        return new ResponseUtil(200,"Ok",service.getAllNonAvailableDrivers());
    }

    @GetMapping(path = "/count/{availability}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil getCountOfCustomersByAvailability(@PathVariable boolean availability){
        return new ResponseUtil(200,"Ok",service.getCountOfDriversByStatus(availability));
    }

    @GetMapping(path = "/getRandomDriver",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil getRandomDriver(){
        return new ResponseUtil(200,"Ok",service.getRandomDriver());
    }
}
