package com.example.employeemanager.repo;

import com.example.employeemanager.model.EmployeePersonalInformation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeePersonalInformationRepo extends JpaRepository<EmployeePersonalInformation,Long> {


    Optional<EmployeePersonalInformation> findEmployeePersonalInformationById(Long id);

    void deleteEmployeePersonalInformationById(Long id);
}
