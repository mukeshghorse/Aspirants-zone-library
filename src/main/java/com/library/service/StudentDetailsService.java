package com.library.service;

import com.library.requestDto.StudentDetailsRequestDto;
import com.library.responseDto.StudentDetailsResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


public interface StudentDetailsService {

    StudentDetailsResponseDto registerStudent(StudentDetailsRequestDto studentDetails, MultipartFile aadhaarImage, MultipartFile profilePicture) throws Exception;


    Page<StudentDetailsResponseDto> getAllActiveStudent(int page, int size, String sortBy, String sortDirection);

    Page<StudentDetailsResponseDto> getAllInactiveStudent(int page, int size, String sortBy, String sortDirection);

    Page<StudentDetailsResponseDto> getAllStudents(int page, int size, String sortBy, String sortDirection);

    String deleteStudent(Long studentId);
}
