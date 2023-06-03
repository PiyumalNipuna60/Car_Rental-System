package lk.ijse.spring.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lk.ijse.spring.entity.CarRent;
import lk.ijse.spring.entity.Payment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.time.LocalDate;
import java.util.Date;

/**
 * @author : M-Prageeth
 * @created : 07/07/2022 - 7:04 PM
 **/
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class CarRentReturnDTO {
    private String returnId;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private String date;
    private double noOfKm;
    private CarRentDTO rental;
    private PaymentDTO payment;
}
