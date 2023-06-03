package lk.ijse.spring.service;

import lk.ijse.spring.dto.CarRentReturnDTO;

import java.util.List;

public interface CarRentReturnService {
    void saveCarRentReturn(CarRentReturnDTO dto);

    void updateCarRentReturn(CarRentReturnDTO dto);

    void deleteCarRentReturn(String returnId);

    CarRentReturnDTO searchCarRentReturn(String returnId);

    List<CarRentReturnDTO> getAllCarRentReturns();

    String generateReturnId();
}
