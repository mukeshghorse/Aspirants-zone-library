package com.library.responseDto;

public class StudentDetailsResponseDto {
    private String firstName;
    private String lastName;
    private String address;
    private String dateOfBirth;
    private String fatherName;
    private String email;
    private String phoneNumber;
    private String highestQualification;
    private String aadhaarNumber;
    private String aadhaarImage;
    private String profilePicture;
    private String registrationDate;

    public String getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(String registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public StudentDetailsResponseDto(String firstName) {
        this.firstName = firstName;
    }


    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public StudentDetailsResponseDto() {
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }



    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getHighestQualification() {
        return highestQualification;
    }

    public void setHighestQualification(String highestQualification) {
        this.highestQualification = highestQualification;
    }

    public String getAadhaarNumber() {
        return aadhaarNumber;
    }

    public void setAadhaarNumber(String aadhaarNumber) {
        this.aadhaarNumber = aadhaarNumber;
    }

    public String getAadhaarImage() {
        return aadhaarImage;
    }

    public void setAadhaarImage(String aadhaarImage) {
        this.aadhaarImage = aadhaarImage;
    }
}
