package com.example.employeemanager.resources;

import com.example.employeemanager.model.EmployeePersonalInformation;
import com.example.employeemanager.service.EmployeePersonalInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



@RestController
@RequestMapping("/employee-personal-information")
public class EmployeePersonalInformationResource {

    private EmployeePersonalInformationService personalInformationService;

    @Autowired
    public EmployeePersonalInformationResource(EmployeePersonalInformationService personalInformationService) {
        this.personalInformationService = personalInformationService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<EmployeePersonalInformation>> getAllEmployeePersonalInformation() {
        List<EmployeePersonalInformation> employeePersonalInformationList = personalInformationService
                .findAllEmployeePersonalInformation();
        return new ResponseEntity<>(employeePersonalInformationList, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<EmployeePersonalInformation> getEmployeePersonalInformationById(@PathVariable("id") Long id) {
        EmployeePersonalInformation employeePersonalInformation = personalInformationService
                .findByIdEmployeePersonalInformation(id);
        return new ResponseEntity<>(employeePersonalInformation, HttpStatus.OK);

    }

    @PostMapping("/add")
    public ResponseEntity<EmployeePersonalInformation> addEmployeePersonalInformation(@Valid @RequestBody EmployeePersonalInformation information) {
        EmployeePersonalInformation employeePersonalInformation = personalInformationService
                .addEmployeePersonalInformation(information);
        return new ResponseEntity<>(employeePersonalInformation, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<EmployeePersonalInformation> updateEmployeePersonalInformation(@Valid @RequestBody EmployeePersonalInformation information) {
        EmployeePersonalInformation employeePersonalInformation = personalInformationService
                .updateEmployeePersonalInformation(information);
        return new ResponseEntity<>(employeePersonalInformation, HttpStatus.OK);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> deleteEmployeePersonalInformation(@PathVariable("id") Long id) {
        personalInformationService.deleteEmployeePersonalInformation(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String,String> handleValidationException(MethodArgumentNotValidException ex){
        Map<String, String> errors = new  HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
