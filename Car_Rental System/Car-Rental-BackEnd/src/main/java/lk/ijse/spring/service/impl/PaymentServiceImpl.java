package lk.ijse.spring.service.impl;

import lk.ijse.spring.dto.PaymentDTO;
import lk.ijse.spring.entity.Payment;
import lk.ijse.spring.repo.PaymentRepo;
import lk.ijse.spring.service.PaymentService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    PaymentRepo repo;

    @Autowired
    ModelMapper mapper;

    @Override
    public void savePayment(PaymentDTO dto) {
        if (!repo.existsById(dto.getPaymentId())) {
            repo.save(mapper.map(dto, Payment.class));
        } else {
            throw new RuntimeException("Payment Already Exists...");
        }
    }

    @Override
    public void updatePayment(PaymentDTO dto) {
        if (repo.existsById(dto.getPaymentId())) {
            repo.save(mapper.map(dto, Payment.class));
        } else {
            throw new RuntimeException("No Such Payment To Update");
        }
    }

    @Override
    public void deletePayment(String paymentId) {
        if (repo.existsById(paymentId)) {
            repo.deleteById(paymentId);
        } else {
            throw new RuntimeException("No Such Payment To Delete");
        }
    }

    @Override
    public PaymentDTO searchPayment(String paymentId) {
        if (repo.existsById(paymentId)) {
            return mapper.map(repo.findById(paymentId).get(), PaymentDTO.class);
        } else {
            throw new RuntimeException("Payment Not Found...");
        }
    }

    @Override
    public List<PaymentDTO> getAllPayments() {
        return mapper.map(repo.findAll(), new TypeToken<List<PaymentDTO>>() {
        }.getType());
    }

    @Override
    public List<PaymentDTO> getAllPaymentsByDateRange(String fromDate, String toDate) {
        return mapper.map(repo.getAllPaymentsByDateRange(fromDate, toDate), new TypeToken<List<PaymentDTO>>() {
        }.getType());
    }

    @Override
    public List<PaymentDTO> getAllPaymentsByCustomerId(String customerId) {
        return mapper.map(repo.getAllPaymentsByCustomerId(customerId), new TypeToken<List<PaymentDTO>>() {
        }.getType());
    }

    @Override
    public String generatePaymentId() {
        String lastId = repo.generatePaymentId();
        String id = "";

        if (lastId != null) {
            int tempId = Integer.parseInt(lastId.split("-")[1]);
            tempId = tempId + 1;
            if (tempId <= 9) {
                id = "PAY-000" + tempId;
            } else if (tempId <= 99) {
                id = "PAY-00" + tempId;
            } else if (tempId <= 999) {
                id = "PAY-0" + tempId;
            } else if (tempId <= 9999) {
                id = "PAY-" + tempId;
            }
        } else {
            id = "PAY-0001";
        }
        return id;
    }

    @Override
    public void deletePaymentByRentId(String rentId) {
        if (repo.existsByRentalRentId(rentId)){
            repo.deleteAllByRentalRentId(rentId);
        } else {
            throw new RuntimeException("Payment Not Found");
        }
    }

    @Override
    public double calculatePaidPayments(String rentId) {
        if (repo.existsByRentalRentId(rentId)){
            return repo.getSumOfPaidPayments(rentId);
        } else {
            throw new RuntimeException("No Payment Found");
        }
    }

    @Override
    public double getSumOfPaymentsByDateRange(String fromDate, String toDate) {
        return repo.getSumOfPaymentAmount(fromDate, toDate);
    }
}
