package lk.ijse.spring.repo;

import lk.ijse.spring.config.JPAConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author : M-Prageeth
 * @created : 27/07/2022 - 12:52 PM
 **/
@WebAppConfiguration
@ContextConfiguration(classes = {JPAConfig.class})
@ExtendWith(SpringExtension.class)
class PaymentRepoTest {

    @Autowired
    PaymentRepo repo;

    @Test
    public void testOne(){
        boolean b = repo.existsByRentalRentId("RT0-0001");
        System.out.println(b);
    }
}