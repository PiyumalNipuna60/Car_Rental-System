package lk.ijse.spring.service.impl;

import lk.ijse.spring.dto.CarRentDTO;
import lk.ijse.spring.entity.CarRent;
import lk.ijse.spring.repo.CarRentRepo;
import lk.ijse.spring.service.CarRentService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CarRentServiceImpl implements CarRentService {

    @Autowired
    CarRentRepo repo;

    @Autowired
    ModelMapper mapper;

    @Override
    public void addCarRent(CarRentDTO dto) {
        if (!repo.existsById(dto.getRentId())) {
            repo.save(mapper.map(dto, CarRent.class));
        } else {
            throw new RuntimeException("Booking Already Exists...");
        }
    }

    @Override
    public void updateCarRent(CarRentDTO dto) {
        if (repo.existsById(dto.getRentId())) {
            repo.save(mapper.map(dto, CarRent.class));
        } else {
            throw new RuntimeException("No Such CarRents To Update");
        }
    }

    @Override
    public void deleteCarRent(String rentId) {
        if (repo.existsById(rentId)) {
            repo.deleteById(rentId);
        } else {
            throw new RuntimeException("No Such CarRents To Delete");
        }
    }

    @Override
    public CarRentDTO searchCarRent(String rentId) {
        if (repo.existsById(rentId)) {
            return mapper.map(repo.findById(rentId).get(), CarRentDTO.class);
        } else {
            throw new RuntimeException("Car Rent Not Found...");
        }
    }

    @Override
    public List<CarRentDTO> getAllCarRents() {
        return mapper.map(repo.findAll(), new TypeToken<List<CarRentDTO>>() {
        }.getType());
    }

    @Override
    public String generateRentId() {
        String lastId = repo.generateRentId();
        String id = "";

        if (lastId != null) {
            int tempId = Integer.parseInt(lastId.split("-")[1]);
            tempId = tempId + 1;
            if (tempId <= 9) {
                id = "RT0-000" + tempId;
            } else if (tempId <= 99) {
                id = "RT0-00" + tempId;
            } else if (tempId <= 999) {
                id = "RT0-0" + tempId;
            } else if (tempId <= 9999) {
                id = "RT0-" + tempId;
            }
        } else {
            id = "RT0-0001";
        }
        return id;
    }

    @Override
    public void updateCarRentStatus(String rentId, String status) {
        if (repo.existsById(rentId)) {
            repo.updateCarRentStatus(rentId, status);
        } else {
            throw new RuntimeException("No Such CarRent To Update");
        }
    }

    @Override
    public List<CarRentDTO> getCarRentsByStatus(String status) {
        return mapper.map(repo.getAllByStatus(status), new TypeToken<List<CarRentDTO>>() {
        }.getType());
    }

    @Override
    public List<CarRentDTO> getCarRentsByDrivingLicenceNo(String status, String licenceNo) {
        return mapper.map(repo.getAllByDrivingLicenceNo(status, licenceNo), new TypeToken<List<CarRentDTO>>() {
        }.getType());
    }

    @Override
    public int getTodayBookingCount(String today) {
        return repo.getTodayBookingCount(today);
    }

    @Override
    public List<CarRentDTO> getTodayBookings(String today) {
        return mapper.map(repo.getTodayBookings(today), new TypeToken<List<CarRentDTO>>() {
        }.getType());
    }

    @Override
    public List<CarRentDTO> getCarRentsByCustomerId(String customerId) {
        return mapper.map(repo.getAllByCustomerId(customerId), new TypeToken<List<CarRentDTO>>() {
        }.getType());
    }
}
