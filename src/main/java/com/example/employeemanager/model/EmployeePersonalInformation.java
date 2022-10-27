package com.example.employeemanager.model;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
public class EmployeePersonalInformation implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long id;
    @Past
    private LocalDate birthDate;
    @Pattern(regexp = "^m|f$", message="Not matching the pattern 'm' or 'f'")
    private String sex;
    @NotBlank(message = "country field is blank")
    private String country;
    @NotBlank(message = "address field is blank")
    private String address;
    @Pattern(regexp = "^married|single$", message="Not matching the pattern 'married' or 'single'")
    private String familyStatus;
    @Min(value = 1, message = "English level should be between 1 and 5")
    @Max(value = 5, message = "English level should be between 1 and 5")
    private int englishLevel;
    @ElementCollection
    private List<Integer> salaryRange;
    @ElementCollection
    private List<String> languages;
    @OneToOne(cascade = CascadeType.ALL)
    private Employee employee;


    public EmployeePersonalInformation() {
    }

    public EmployeePersonalInformation(Long id, LocalDate birthDate, String sex, String country, String address, String familyStatus, int englishLevel, List<Integer> salaryRange, List<String> languages, Employee employee) {
        this.id = id;
        this.birthDate = birthDate;
        this.sex = sex;
        this.country = country;
        this.address = address;
        this.familyStatus = familyStatus;
        this.englishLevel = englishLevel;
        this.salaryRange = salaryRange;
        this.languages = languages;
        this.employee = employee;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFamilyStatus() {
        return familyStatus;
    }

    public void setFamilyStatus(String familyStatus) {
        this.familyStatus = familyStatus;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public List<Integer> getSalaryRange() {
        return salaryRange;
    }

    public void setSalaryRange(List<Integer> salaryRange) {
        this.salaryRange = salaryRange;
    }

    public int getEnglishLevel() {
        return englishLevel;
    }

    public void setEnglishLevel(int englishLevel) {
        this.englishLevel = englishLevel;
    }

    public List<String> getLanguages() {
        return languages;
    }

    public void setLanguages(List<String> languages) {
        this.languages = languages;
    }

    @Override
    public String toString() {
        return "EmployeePersonalInformation{" +
                "id=" + id +
                ", birthDate=" + birthDate +
                ", sex='" + sex + '\'' +
                ", country='" + country + '\'' +
                ", address='" + address + '\'' +
                ", familyStatus='" + familyStatus + '\'' +
                ", languages=" + languages +
                ", employee=" + employee +
                '}';
    }
}
