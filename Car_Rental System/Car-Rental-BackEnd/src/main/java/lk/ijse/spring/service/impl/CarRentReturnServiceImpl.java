package lk.ijse.spring.service.impl;

import lk.ijse.spring.dto.CarRentReturnDTO;
import lk.ijse.spring.entity.CarRentReturn;
import lk.ijse.spring.repo.CarRentReturnRepo;
import lk.ijse.spring.service.CarRentReturnService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CarRentReturnServiceImpl implements CarRentReturnService {

    @Autowired
    CarRentReturnRepo repo;

    @Autowired
    ModelMapper mapper;

    @Override
    public void saveCarRentReturn(CarRentReturnDTO dto) {
        System.out.println(dto.toString());
        if (!repo.existsById(dto.getReturnId())) {
            repo.save(mapper.map(dto,CarRentReturn.class));
//            repo.saveCarRentReturn(dto.getReturnId(), dto.getDate(), dto.getNoOfKm(), dto.getPayment().getPaymentId(), dto.getRental().getRentId());
        } else {
            throw new RuntimeException("CarRentReturn Already Exists...");
        }
    }

    @Override
    public void updateCarRentReturn(CarRentReturnDTO dto) {
        if (repo.existsById(dto.getReturnId())) {
            repo.save(mapper.map(dto, CarRentReturn.class));
        } else {
            throw new RuntimeException("No Such CarRentReturn To Update");
        }
    }

    @Override
    public void deleteCarRentReturn(String returnId) {
        if (repo.existsById(returnId)) {
            repo.deleteById(returnId);
        } else {
            throw new RuntimeException("No Such CarRentReturn To Delete");
        }
    }

    @Override
    public CarRentReturnDTO searchCarRentReturn(String returnId) {
        if (repo.existsById(returnId)) {
            return mapper.map(repo.findById(returnId).get(), CarRentReturnDTO.class);
        } else {
            throw new RuntimeException("CarRentReturn Not Found...");
        }
    }

    @Override
    public List<CarRentReturnDTO> getAllCarRentReturns() {
        return mapper.map(repo.findAll(), new TypeToken<List<CarRentReturnDTO>>() {
        }.getType());
    }

    @Override
    public String generateReturnId() {
        String lastId = repo.generateReturnId();
        String id = "";

        if (lastId != null) {
            int tempId = Integer.parseInt(lastId.split("-")[1]);
            tempId = tempId + 1;
            if (tempId <= 9) {
                id = "RTN-000" + tempId;
            } else if (tempId <= 99) {
                id = "RTN-00" + tempId;
            } else if (tempId <= 999) {
                id = "RTN-0" + tempId;
            } else if (tempId <= 9999) {
                id = "RTN-" + tempId;
            }
        } else {
            id = "RTN-0001";
        }
        return id;
    }
}
