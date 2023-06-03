package lk.ijse.spring.service.impl;

import lk.ijse.spring.dto.MaintenanceDTO;
import lk.ijse.spring.entity.Maintenance;
import lk.ijse.spring.repo.MaintenanceRepo;
import lk.ijse.spring.service.MaintenanceService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MaintenanceServiceImpl implements MaintenanceService {

    @Autowired
    MaintenanceRepo repo;

    @Autowired
    ModelMapper mapper;


    @Override
    public void addMaintenance(MaintenanceDTO dto) {
        if (!repo.existsById(dto.getMaintenanceId())) {
            repo.save(mapper.map(dto, Maintenance.class));
        } else {
            throw new RuntimeException("Maintenance Already Exists...");
        }
    }

    @Override
    public void updateMaintenance(MaintenanceDTO dto) {
        if (repo.existsById(dto.getMaintenanceId())) {
            repo.save(mapper.map(dto, Maintenance.class));
        } else {
            throw new RuntimeException("No Such Maintenance To Update");
        }
    }

    @Override
    public void deleteMaintenance(String maintenanceId) {
        if (repo.existsById(maintenanceId)) {
            repo.deleteById(maintenanceId);
        } else {
            throw new RuntimeException("No Such Maintenance To Delete");
        }
    }

    @Override
    public MaintenanceDTO searchMaintenance(String maintenanceId) {
        if (repo.existsById(maintenanceId)) {
            return mapper.map(repo.findById(maintenanceId).get(), MaintenanceDTO.class);
        } else {
            throw new RuntimeException("Maintenance Not Found...");
        }
    }

    @Override
    public List<MaintenanceDTO> getAllMaintenances() {
        return mapper.map(repo.findAll(), new TypeToken<List<MaintenanceDTO>>() {
        }.getType());
    }

    @Override
    public String generateMaintenanceId() {
        String lastId = repo.generateMaintenanceId();
        String id = "";

        if (lastId != null) {
            int tempId = Integer.parseInt(lastId.split("-")[1]);
            tempId = tempId + 1;
            if (tempId <= 9) {
                id = "MT-000" + tempId;
            } else if (tempId <= 99) {
                id = "MT-00" + tempId;
            } else if (tempId <= 999) {
                id = "MT-0" + tempId;
            } else if (tempId <= 9999) {
                id = "MT-" + tempId;
            }
        } else {
            id = "MT-0001";
        }
        return id;
    }

    @Override
    public void updateMaintenanceCost(String maintenanceId, double cost) {
        if (repo.existsById(maintenanceId)) {
            repo.updateMaintenanceCost(maintenanceId, cost);
        } else {
            throw new RuntimeException("No Such Maintenance To Update");
        }
    }

    @Override
    public List<MaintenanceDTO> getAllUnderMaintenances() {
        return mapper.map(repo.getAllUnderMaintenances(), new TypeToken<List<MaintenanceDTO>>() {
        }.getType());
    }

    @Override
    public List<MaintenanceDTO> getAllMaintenancesByDateRange(String fromDate, String toDate) {
        return mapper.map(repo.getAllMaintenancesByDateRange(fromDate, toDate), new TypeToken<List<MaintenanceDTO>>() {
        }.getType());
    }

    @Override
    public double getSumOfMaintenanceAmount(String fromDate, String toDate) {
        return repo.getSumOfMaintenanceAmount(fromDate, toDate);
    }
}
