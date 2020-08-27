package examen.meli.repository;

import examen.meli.entity.LogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LogRepository extends JpaRepository<LogEntity, Long> {

    LogEntity findByCountry(String country);

    @Query(value = "SELECT SUM(distance*invocations)/SUM(invocations) from log" , nativeQuery = true)
    Double findProm();

    @Query(value = "select * from log where distance = (SELECT MAX(distance) FROM log)" , nativeQuery = true)
    List<LogEntity> findMax();

    @Query(value = "select * from log where distance = (SELECT MIN(distance) FROM log)" , nativeQuery = true)
    List<LogEntity> findMin();
}
