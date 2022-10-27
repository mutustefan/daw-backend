package com.example.employeemanager.service;

import com.example.employeemanager.model.EmployeePersonalInformation;
import com.example.employeemanager.repo.EmployeePersonalInformationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeePersonalInformationService  {

    @Autowired
    private EmployeePersonalInformationRepo personalInformationRepo;

    public EmployeePersonalInformation addEmployeePersonalInformation(EmployeePersonalInformation personalInformation){
        return personalInformationRepo.save(personalInformation);
    }

    public List<EmployeePersonalInformation> findAllEmployeePersonalInformation(){
        return personalInformationRepo.findAll();
    }

    public EmployeePersonalInformation findByIdEmployeePersonalInformation(Long id){
        return personalInformationRepo.findEmployeePersonalInformationById(id)
                .orElseThrow(() -> new RuntimeException("Personal Information not found"));
    }

    public EmployeePersonalInformation updateEmployeePersonalInformation(EmployeePersonalInformation information){
        return personalInformationRepo.save(information);
    }

    @Transactional
    public void deleteEmployeePersonalInformation(Long id){
        personalInformationRepo.deleteEmployeePersonalInformationById(id);
    }
}
