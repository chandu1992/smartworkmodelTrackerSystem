package com.jarvis.worksmartmoduelcompliancetracker.repository;

import com.jarvis.worksmartmoduelcompliancetracker.entity.ExceptionOdDataEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ExceptionOdDataRepo extends JpaRepository<ExceptionOdDataEntity,Long> {

    Optional<ExceptionOdDataEntity> findByRequestId(Long requestId);


    @Query("SELECT e FROM ExceptionOdDataEntity e WHERE e.managerEmpId = :managerEmpId AND e.odStatus = 0")
    List<ExceptionOdDataEntity> findRePendingWithManager(@Param("managerEmpId") String managerEmpId);


    @Query("SELECT e FROM ExceptionOdDataEntity e WHERE e.empId = :empId AND e.odStatus = 0")
    List<ExceptionOdDataEntity> findPendingRaisesByUser(String empId);


    @Modifying
    @Query("DELETE FROM ExceptionOdDataEntity e WHERE e.requestId = :requestId")
    void deleteByRequestId(@Param("requestId") Long requestId);


    @Query("SELECT e FROM ExceptionOdDataEntity e WHERE e.odStatus = 0")
    List<ExceptionOdDataEntity> findAllNonComplianceData();


    @Query("""
        SELECT e
        FROM ExceptionalOndataEntity e
        WHERE e.startDate <= :endDate
        AND (e.endDate IS NULL OR e.endDate >= :startDate)
        AND e.empId = :empId
    """)
    List<ExceptionOdDataEntity> findOdAlreadyRaiseOrNot(
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate,
            @Param("empId") String empId
    );

}
