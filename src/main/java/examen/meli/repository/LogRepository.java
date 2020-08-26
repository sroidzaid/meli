package examen.meli.repository;

import examen.meli.entity.LogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LogRepository extends JpaRepository<LogEntity, Long> {

    LogEntity findByCountry(String country);

    @Query(value = "SELECT MIN(distance) FROM log" , nativeQuery = true)
    Double findMin();

    @Query(value = "SELECT MAX(distance) FROM log" , nativeQuery = true)
    Double findMax();

    @Query(value = "SELECT SUM(distance*invocations)/SUM(invocations) from log" , nativeQuery = true)
    Double findProm();
}
