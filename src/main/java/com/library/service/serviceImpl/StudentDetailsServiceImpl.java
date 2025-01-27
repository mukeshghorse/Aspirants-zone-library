package com.library.service.serviceImpl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.library.entity.StudentDetails;
import com.library.exceptions.ResourceNotFoundException;
import com.library.exceptions.StudentAlreadyFoundException;
import com.library.repository.StudentDetailsRepo;
import com.library.requestDto.StudentDetailsRequestDto;
import com.library.responseDto.StudentDetailsResponseDto;
import com.library.service.StudentDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.Optional;

@Service
public class StudentDetailsServiceImpl implements StudentDetailsService {

    @Autowired
    private StudentDetailsRepo studentDetailsRepo;

    @Autowired
    private Cloudinary cloudinary;

    @Override
    public StudentDetailsResponseDto registerStudent(StudentDetailsRequestDto studentDetailsRequestDto, MultipartFile aadhaarImage, MultipartFile profilePicture) {
        Optional<StudentDetails> existingStudent = studentDetailsRepo.findByAadhaarNumber(studentDetailsRequestDto.getAadhaarNumber());
        if (existingStudent.isPresent()) {
            throw new StudentAlreadyFoundException("Student with this Aadhaar number already registered");
        }

        try {
            if (aadhaarImage == null || profilePicture == null) {
                throw new IllegalArgumentException("Aadhaar image and profile picture are required");
            }

            String aadhaarImageUrl = uploadImageToCloud(aadhaarImage);
            String profilePictureUrl = uploadImageToCloud(profilePicture);

            StudentDetails studentDetails = new StudentDetails();
            studentDetails.setCreatedOn(new Date());
            studentDetails.setUpdatedOn(new Date());
            studentDetails.setFirstName(studentDetailsRequestDto.getFirstName());
            studentDetails.setLastName(studentDetailsRequestDto.getLastName());
            studentDetails.setAddress(studentDetailsRequestDto.getAddress());
            studentDetails.setDateOfBirth(studentDetailsRequestDto.getDateOfBirth());
            studentDetails.setFatherName(studentDetailsRequestDto.getFatherName());
            studentDetails.setEmail(studentDetailsRequestDto.getEmail());
            studentDetails.setPassword(studentDetailsRequestDto.getPassword());
            studentDetails.setPhoneNumber(studentDetailsRequestDto.getPhoneNumber());
            studentDetails.setHighestQualification(studentDetailsRequestDto.getHighestQualification());
            studentDetails.setAadhaarNumber(studentDetailsRequestDto.getAadhaarNumber());
            studentDetails.setRegistrationDate(studentDetailsRequestDto.getRegistrationDate());
            studentDetails.setAadhaarImage(aadhaarImageUrl);
            studentDetails.setProfilePicture(profilePictureUrl);

            StudentDetails savedStudent = studentDetailsRepo.save(studentDetails);

            return mapToDto(savedStudent);

        } catch (IOException e) {
            throw new RuntimeException("Failed to upload images to Cloudinary", e);
        }
    }

    @Override
    public Page<StudentDetailsResponseDto> getAllActiveStudent(int page, int size, String sortBy, String sortDirection) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(sortDirection), sortBy));

        Page<StudentDetails> activeStudentsPage = studentDetailsRepo.findByIsActiveTrue(pageable);

        if (activeStudentsPage.isEmpty()) {
            throw new ResourceNotFoundException("No active students found");
        }

        return activeStudentsPage.map(this::mapToDto);
    }

    @Override
    public Page<StudentDetailsResponseDto> getAllInactiveStudent(int page, int size, String sortBy, String sortDirection) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(sortDirection), sortBy));

        Page<StudentDetails> inactiveStudentsPage = studentDetailsRepo.findByIsActiveFalse(pageable);

        if (inactiveStudentsPage.isEmpty()) {
            throw new ResourceNotFoundException("No inactive students found");
        }

        return inactiveStudentsPage.map(this::mapToDto);
    }

    @Override
    public Page<StudentDetailsResponseDto> getAllStudents(int page, int size, String sortBy, String sortDirection) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(sortDirection), sortBy));

        Page<StudentDetails> studentPage = studentDetailsRepo.findAll(pageable);

        if (studentPage.isEmpty()) {
            throw new ResourceNotFoundException("No students found");
        }

        return studentPage.map(this::mapToDto);
    }


    @Override
    public String deleteStudent(Long studentId){
        StudentDetails studentDetails = studentDetailsRepo.findByStudentIdAndIsActiveTrue(studentId).orElseThrow(() -> new ResourceNotFoundException("Student Not found with given Id"));
        studentDetails.setIsActive(false);
        studentDetailsRepo.save(studentDetails);
        return "Student Deleted Successfully";
    }

    private String uploadImageToCloud(MultipartFile file) throws IOException {
        Map uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
        return (String) uploadResult.get("url");
    }

    private StudentDetailsResponseDto mapToDto(StudentDetails studentDetails) {
        StudentDetailsResponseDto dto = new StudentDetailsResponseDto();
        dto.setFirstName(studentDetails.getFirstName());
        dto.setLastName(studentDetails.getLastName());
        dto.setAddress(studentDetails.getAddress());
        dto.setDateOfBirth(studentDetails.getDateOfBirth());
        dto.setFatherName(studentDetails.getFatherName());
        dto.setEmail(studentDetails.getEmail());
        dto.setPhoneNumber(studentDetails.getPhoneNumber());
        dto.setHighestQualification(studentDetails.getHighestQualification());
        dto.setAadhaarNumber(studentDetails.getAadhaarNumber());
        dto.setAadhaarImage(studentDetails.getAadhaarImage());
        dto.setProfilePicture(studentDetails.getProfilePicture());
        dto.setRegistrationDate(studentDetails.getRegistrationDate());
        return dto;
    }
}