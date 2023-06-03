package lk.ijse.spring.service;

import lk.ijse.spring.dto.DriverDTO;

import java.util.List;

public interface DriverService {
    void saveDriver(DriverDTO dto);

    void updateDriver(DriverDTO dto);

    void deleteDriver(String licenceNo);

    DriverDTO searchDriver(String licenceNo);

    List<DriverDTO> getAllDrivers();

    boolean findDriverByUsername(String username);

    boolean findDriverByPassword(String password);

    DriverDTO findDriverByUsernameAndPassword(String username, String password);

    void updateDriverNonAvailable(String licenceNo);

    void updateDriverAvailable(String licenceNo);

    List<DriverDTO> getAllAvailableDrivers();

    List<DriverDTO> getAllNonAvailableDrivers();

    int getCountOfDriversByStatus(boolean availability);

    List<DriverDTO> getRandomDriver();
}
