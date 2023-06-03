package lk.ijse.spring.repo;

import lk.ijse.spring.config.JPAConfig;
import lk.ijse.spring.entity.Admin;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author : M-Prageeth
 * @created : 08/07/2022 - 7:19 PM
 **/
@WebAppConfiguration
@ContextConfiguration(classes = {JPAConfig.class})
@ExtendWith(SpringExtension.class)
class AdminRepoTest {
    @Autowired
    AdminRepo repo;

    @Test
    public void findAdminUsername(){
        System.out.println(repo.findAdminByUsername("prageeth").isPresent());
    }
}