package com.medilabosolutions.patientinfoservice.repository;

import com.medilabosolutions.patientinfoservice.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer> {

}
