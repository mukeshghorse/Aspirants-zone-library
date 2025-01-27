package com.library.repository;

import com.library.entity.StudentDetails;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentDetailsRepo extends JpaRepository<StudentDetails, Long> {
    Optional<StudentDetails> findByAadhaarNumber(String aadhaarNumber);

    Page<StudentDetails> findByIsActiveTrue(Pageable pageable);

    Page<StudentDetails> findByIsActiveFalse(Pageable pageable);

    Optional<StudentDetails> findByStudentIdAndIsActiveTrue(Long studentId);
}
