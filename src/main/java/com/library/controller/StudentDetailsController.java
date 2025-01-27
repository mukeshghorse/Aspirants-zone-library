package com.library.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.library.exceptions.ResourceNotFoundException;
import com.library.payloads.ApiResponseMessage;
import com.library.payloads.PaginationMetadata;
import com.library.requestDto.StudentDetailsRequestDto;
import com.library.responseDto.StudentDetailsResponseDto;
import com.library.service.StudentDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@RequestMapping("/api/student-details")
public class StudentDetailsController {

    @Autowired
    private StudentDetailsService studentDetailsService;

    @PostMapping(value = "/register-student")
    public ResponseEntity<ApiResponseMessage> registerStudent(
            @RequestPart("studentDetails") String studentDetailsRequestJson,
            @RequestPart("aadhaarImage") MultipartFile aadhaarImage,
            @RequestPart("profilePicture") MultipartFile profilePicture) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            StudentDetailsRequestDto studentDetailsRequestDto = objectMapper.readValue(studentDetailsRequestJson, StudentDetailsRequestDto.class);

            StudentDetailsResponseDto studentResponse = studentDetailsService.registerStudent(studentDetailsRequestDto, aadhaarImage, profilePicture);

            ApiResponseMessage apiResponse = new ApiResponseMessage(
                    HttpStatus.CREATED,
                    HttpStatus.CREATED.value(),
                    "Student registered successfully",
                    studentResponse,
                    null,
                    null
            );

            return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            ApiResponseMessage apiResponse = new ApiResponseMessage(
                    HttpStatus.BAD_REQUEST,
                    HttpStatus.BAD_REQUEST.value(),
                    e.getMessage(),
                    null,
                    null,
                    null
            );
            return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            ApiResponseMessage apiResponse = new ApiResponseMessage(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    "An error occurred while registering the student",
                    null,
                    null,
                    null
            );
            return new ResponseEntity<>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/active-students")
    public ResponseEntity<ApiResponseMessage> getAllActiveStudents(
            @RequestParam int page,
            @RequestParam int size,
            @RequestParam String sortBy,
            @RequestParam String sortDirection) {
        try {
            Page<StudentDetailsResponseDto> activeStudents = studentDetailsService.getAllActiveStudent(page, size, sortBy, sortDirection);

            PaginationMetadata paginationMetadata = new PaginationMetadata(
                    activeStudents.getTotalPages(),
                    activeStudents.getTotalElements(),
                    activeStudents.getNumber(),
                    activeStudents.getSize()
            );

            ApiResponseMessage apiResponse = new ApiResponseMessage(
                    HttpStatus.OK,
                    HttpStatus.OK.value(),
                    "Active students fetched successfully",
                    activeStudents.getContent(),
                    null,
                    paginationMetadata
            );

            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            ApiResponseMessage apiResponse = new ApiResponseMessage(
                    HttpStatus.NOT_FOUND,
                    HttpStatus.NOT_FOUND.value(),
                    e.getMessage(),
                    null,
                    null,
                    null
            );
            return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            ApiResponseMessage apiResponse = new ApiResponseMessage(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    "An error occurred while fetching active students",
                    null,
                    null,
                    null
            );
            return new ResponseEntity<>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/all-inactive-students")
    public ResponseEntity<ApiResponseMessage> getAllInactiveStudents(
            @RequestParam int page,
            @RequestParam int size,
            @RequestParam String sortBy,
            @RequestParam String sortDirection) {
        try {
            Page<StudentDetailsResponseDto> inactiveStudents = studentDetailsService.getAllInactiveStudent(page, size, sortBy, sortDirection);

            PaginationMetadata paginationMetadata = new PaginationMetadata(
                    inactiveStudents.getTotalPages(),
                    inactiveStudents.getTotalElements(),
                    inactiveStudents.getNumber(),
                    inactiveStudents.getSize()
            );

            ApiResponseMessage apiResponse = new ApiResponseMessage(
                    HttpStatus.OK,
                    HttpStatus.OK.value(),
                    "Inactive students fetched successfully",
                    inactiveStudents.getContent(),
                    null,
                    paginationMetadata
            );

            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            ApiResponseMessage apiResponse = new ApiResponseMessage(
                    HttpStatus.NOT_FOUND,
                    HttpStatus.NOT_FOUND.value(),
                    e.getMessage(),
                    null,
                    null,
                    null
            );
            return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            ApiResponseMessage apiResponse = new ApiResponseMessage(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    "An error occurred while fetching inactive students",
                    null,
                    null,
                    null
            );
            return new ResponseEntity<>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/all-students")
    public ResponseEntity<ApiResponseMessage> getAllStudents(
            @RequestParam int page,
            @RequestParam int size,
            @RequestParam String sortBy,
            @RequestParam String sortDirection) {
        try {
            Page<StudentDetailsResponseDto> students = studentDetailsService.getAllStudents(page, size, sortBy, sortDirection);

            PaginationMetadata paginationMetadata = new PaginationMetadata(
                    students.getTotalPages(),
                    students.getTotalElements(),
                    students.getNumber(),
                    students.getSize()
            );

            ApiResponseMessage apiResponse = new ApiResponseMessage(
                    HttpStatus.OK,
                    HttpStatus.OK.value(),
                    "Students fetched successfully",
                    students.getContent(),
                    null,
                    paginationMetadata
            );
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        } catch (Exception e) {
            ApiResponseMessage apiResponse = new ApiResponseMessage(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    "An error occurred while fetching students",
                    null,
                    null,
                    null
            );
            return new ResponseEntity<>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete-student/{studentId}")
    public ResponseEntity<ApiResponseMessage> deleteStudent(@PathVariable Long studentId) {
        try {
            String message = studentDetailsService.deleteStudent(studentId);
            ApiResponseMessage apiResponse = new ApiResponseMessage(
                    HttpStatus.OK,
                    HttpStatus.OK.value(),
                    message,
                    null,
                    null,
                    null
            );
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            ApiResponseMessage apiResponse = new ApiResponseMessage(
                    HttpStatus.NOT_FOUND,
                    HttpStatus.NOT_FOUND.value(),
                    e.getMessage(),
                    null,
                    null,
                    null
            );
            return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            ApiResponseMessage apiResponse = new ApiResponseMessage(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    "An error occurred while deleting the student",
                    null,
                    null,
                    null
            );
            return new ResponseEntity<>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}