package lk.ijse.spring.service;

import lk.ijse.spring.dto.CarDTO;

import java.util.List;

public interface CarService {
    void saveCar(CarDTO dto);

    void updateCar(CarDTO dto);

    void deleteCar(String registrationNO);

    List<CarDTO> getAllCars();

    CarDTO searchCar(String registrationNO);

    void updateCarStatus(String registrationNO, String status);

    void updateCarFilePaths(String frontImg, String backImg, String interImg, String sideImg, String registrationID);

    List<CarDTO> getAllCarsByStatus(String status);

    int getCountOfCarsByStatus(String status);

    List<String> getCarRegistrationNumbersByType(String type);
}
