package lk.ijse.spring.repo;

import lk.ijse.spring.entity.Customer;
import lk.ijse.spring.entity.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface DriverRepo extends JpaRepository<Driver, String> {
    Optional<Driver> findDriverByUsername(String username);

    Optional<Driver> findDriverByPassword(String password);

    Optional<Driver> findDriverByUsernameAndPassword(String username, String password);

    @Modifying
    @Transactional
    @Query(value = "UPDATE Driver SET availability = false WHERE licenceNo=:licenceNo", nativeQuery = true)
    void updateDriverNonAvailable(@Param("licenceNo") String licenceNo);

    @Modifying
    @Transactional
    @Query(value = "UPDATE Driver SET availability = true WHERE licenceNo=:licenceNo", nativeQuery = true)
    void updateDriverAvailable(@Param("licenceNo") String licenceNo);

    @Query(value = "SELECT * FROM Driver WHERE availability=true",nativeQuery = true)
    List<Driver> getAllAvailableDrivers();

    @Query(value = "SELECT * FROM Driver WHERE availability=false",nativeQuery = true)
    List<Driver> getAllNonAvailableDrivers();

    @Query(value = "SELECT COUNT(licenceNo) FROM Driver WHERE availability=:availability",nativeQuery = true)
    int getCountOfDriversByStatus(@Param("availability") boolean availability);

    @Query(value = "SELECT * FROM Driver WHERE availability=true ORDER BY RAND() LIMIT 1",nativeQuery = true)
    List<Driver> getRandomDriver();
}
