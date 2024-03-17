package com.medilabosolutions.patientinfoservice.controller;

import com.medilabosolutions.patientinfoservice.model.Patient;
import com.medilabosolutions.patientinfoservice.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.lang.reflect.Field;
import java.util.List;

@RestController
@RequestMapping
public class PatientController {

    @Autowired
    private PatientRepository patientRepository;

    @GetMapping("/patient/list")
    public ResponseEntity<List<Patient>> getAllPatients() {
        List<Patient> patients = patientRepository.findAll();
        return new ResponseEntity<>(patients, HttpStatus.OK);
    }

    @GetMapping("/patient/details/{id}")
    public ResponseEntity<Patient> getPatientById(@PathVariable Integer id) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Patient not found with id: " + id));
        return new ResponseEntity<>(patient, HttpStatus.OK);
    }

    @PostMapping("/patient/validate")
    public ResponseEntity<Patient> addPatient(@RequestBody Patient patient) {
        Patient savedPatient = patientRepository.save(patient);
        return new ResponseEntity<>(savedPatient, HttpStatus.CREATED);
    }

    @PutMapping("/patient/update/{id}")
    public ResponseEntity<Patient> updatePatient(@PathVariable Integer id, @RequestBody Patient patientDetails) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Patient not found with id: " + id));

        // Update patient details if provided
        for (Field field : Patient.class.getDeclaredFields()) {
            try {
                field.setAccessible(true);
                Object value = field.get(patientDetails);
                if (value != null) {
                    field.set(patient, value);
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        Patient updatedPatient = patientRepository.save(patient);
        return new ResponseEntity<>(updatedPatient, HttpStatus.OK);
    }

    @DeleteMapping("/patient/delete/{id}")
    public ResponseEntity<String> deletePatient(@PathVariable Integer id) {
        if (!patientRepository.existsById(id)) {
            return new ResponseEntity<>("Patient not found with id: " + id, HttpStatus.NOT_FOUND);
        }
        patientRepository.deleteById(id);
        return new ResponseEntity<>("Patient deleted successfully", HttpStatus.OK);
    }

}
