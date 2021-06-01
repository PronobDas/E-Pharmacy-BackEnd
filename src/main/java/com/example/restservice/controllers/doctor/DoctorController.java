package com.example.restservice.controllers.doctor;

import com.example.restservice.models.doctor.Doctor;
import com.example.restservice.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/api/v1")
public class DoctorController {
    @Autowired
    DoctorRepository doctorRepository;

    @PostMapping("/doctors")
    public ResponseEntity<Doctor> createDoctor(@RequestBody Doctor doctor) {
        try {
            Doctor _doctor = doctorRepository.save(new Doctor(doctor.getName(), doctor.getDesignation(), doctor.getSpeciality(), doctor.getLocationId(), doctor.getDay(), doctor.getTime(), doctor.getContactNo()));
            return new ResponseEntity<>(_doctor, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/doctors")
    public ResponseEntity<List<Doctor>> getAllDoctors() {
        try {
            List<Doctor> doctors = new ArrayList<Doctor>();
            doctorRepository.findAll().forEach(doctors::add);

            if (doctors.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(doctors, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/doctors/id/{id}")
    public ResponseEntity<Doctor> getDoctorById(@PathVariable("id") String id) {
        Optional<Doctor> doctorData = doctorRepository.findById(id);

        if (doctorData.isPresent()) {
            return new ResponseEntity<>(doctorData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/doctors/locationId/{locationId}")
    public ResponseEntity<List<Doctor>> getDoctorByLocationId(@PathVariable("locationId") String locationId) {
        List<Doctor> doctors = new ArrayList<Doctor>();
        doctorRepository.findByLocationId(locationId).forEach(doctors::add);

        if (!doctors.isEmpty()) {
            return new ResponseEntity<>(doctors, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/doctors/name/{name}")
    public ResponseEntity<List<Doctor>> getDoctorByName(@PathVariable("name") String name) {
        List<Doctor> doctors = new ArrayList<Doctor>();
        doctorRepository.findByNameContaining(name).forEach(doctors::add);

        if (!doctors.isEmpty()) {
            return new ResponseEntity<>(doctors, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/doctors/speciality/{speciality}")
    public ResponseEntity<List<Doctor>> getDoctorBySpeciality(@PathVariable("speciality") String speciality) {
        List<Doctor> doctors = new ArrayList<Doctor>();
        doctorRepository.findBySpecialityContaining(speciality).forEach(doctors::add);

        if (!doctors.isEmpty()) {
            return new ResponseEntity<>(doctors, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/doctors/designation/{designation}")
    public ResponseEntity<List<Doctor>> getDoctorByDesignation(@PathVariable("designation") String designation) {
        List<Doctor> doctors = new ArrayList<Doctor>();
        doctorRepository.findByDesignationContaining(designation).forEach(doctors::add);

        if (!doctors.isEmpty()) {
            return new ResponseEntity<>(doctors, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/doctors/{id}")
    public ResponseEntity<Doctor> updateDoctor(@PathVariable("id") String id, @RequestBody Doctor doctor) {
        Optional<Doctor> doctorData = doctorRepository.findById(id);
        if (doctorData.isPresent()){
            Doctor _doctor = doctorData.get();

            _doctor.setName(doctor.getName());
            _doctor.setDesignation(doctor.getDesignation());
            _doctor.setSpeciality(doctor.getSpeciality());
            _doctor.setLocationId(doctor.getLocationId());
            _doctor.setDay(doctor.getDay());
            _doctor.setTime(doctor.getTime());
            _doctor.setContactNo(doctor.getContactNo());

            return new ResponseEntity<>(doctorRepository.save(_doctor), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/doctors/{id}")
    public ResponseEntity<HttpStatus> deleteDoctor(@PathVariable("id") String id) {
        try {
            doctorRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/doctors")
    public ResponseEntity<HttpStatus> deleteAllDoctors() {
        try {
            doctorRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}