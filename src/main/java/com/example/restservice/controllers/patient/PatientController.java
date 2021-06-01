package com.example.restservice.controllers.patient;

import com.example.restservice.models.patient.Patient;
import com.example.restservice.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class PatientController {
    @Autowired
    PatientRepository patientRepository;

    @PostMapping("/patients")
    public ResponseEntity<Patient> createPatient(@RequestBody Patient patient) {
        try {
            Patient _patient = patientRepository.save(new Patient(patient.getRegId(), patient.getDoctorId(), patient.getHospitalId(), patient.getUpi(), patient.getPatientName(), patient.getRegDatetime(), patient.getDateOfBirth(), patient.getAge(), patient.getGender(), patient.getOccupation(), patient.getHealthInsuranceNo(), patient.getHealthCareProvider(), patient.getAddress(), patient.getContactNo(), patient.getCreatedOn()));
            return new ResponseEntity<>(_patient, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/patients")
    public ResponseEntity<List<Patient>> getAllPatients() {
        try {
            List<Patient> patients = new ArrayList<Patient>();

            patientRepository.findAll().forEach(patients::add);

            if (patients.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(patients, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/patients/id/{upi}")
    public ResponseEntity<Patient> getPatientById(@PathVariable("upi") String upi) {
        Optional<Patient> patientData = patientRepository.findByUpi(upi);

        if (patientData.isPresent()) {
            return new ResponseEntity<>(patientData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/patients/name/{patientName}")
    public ResponseEntity<List<Patient>> getPatientByName(@PathVariable("patientName") String patientName) {
        List<Patient> patients = new ArrayList<Patient>();
        patientRepository.findByPatientNameContaining(patientName).forEach(patients::add);

        if (!patients.isEmpty()) {
            //patientRepository.findByPatientNameContaining(patientName).forEach(patients::add);
            return new ResponseEntity<>(patients, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/patients/{patientName}/{dateOfBirth}")
    public ResponseEntity<Patient> getPatientByNameDateOfBirth(@RequestBody Patient p) {
        //String date = p.getDateOfBirth();
        //SimpleDateFormat formatter=new SimpleDateFormat("dd-MM-yyyy");
        //Date date1=formatter.parse(date);
        Optional<Patient> patientData = patientRepository.findByPatientNameAndDateOfBirth(p.getPatientName(), p.getDateOfBirth());

        if (patientData.isPresent()) {
            return new ResponseEntity<>(patientData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/patients/{upi}")
    public ResponseEntity<Patient> updatePatient(@PathVariable("upi") String upi, @RequestBody Patient patient) {
        Optional<Patient> patientData = patientRepository.findByUpi(upi);

        if (patientData.isPresent()) {
            Patient _patient = patientData.get();

            if (patient.getPatientName() != null) {
                _patient.setPatientName(patient.getPatientName());
            }

            if (patient.getDateOfBirth() != null) {
                _patient.setDateOfBirth(patient.getDateOfBirth());
            }

            if (patient.getAge() != null) {
                _patient.setAge(patient.getAge());
            }

            if (patient.getGender() != null) {
                _patient.setGender(patient.getGender());
            }

            if (patient.getOccupation() != null) {
                _patient.setOccupation(patient.getOccupation());
            }

            if (patient.getHealthInsuranceNo() != null) {
                _patient.setHealthInsuranceNo(patient.getHealthInsuranceNo());
            }

            if (patient.getHealthCareProvider() != null) {
                _patient.setHealthCareProvider(patient.getHealthCareProvider());
            }

            if (patient.getAddress() != null) {
                _patient.setAddress(patient.getAddress());
            }

            if (patient.getContactNo() != null) {
                _patient.setContactNo(patient.getContactNo());
            }

            if (patient.getDoctorId() != null) {
                _patient.setDoctorId(patient.getDoctorId());
            }

            return new ResponseEntity<>(patientRepository.save(_patient), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/patients/{upi}")
    public ResponseEntity<HttpStatus> deletePatient(@PathVariable("upi") String upi) {
        try {
            patientRepository.deleteById(upi);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/patients")
    public ResponseEntity<HttpStatus> deleteAllPatients() {
        try {
            patientRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}