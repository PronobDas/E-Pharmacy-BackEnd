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
@RequestMapping("/api")
public class DoctorController {
    @Autowired
    DoctorRepository doctorRepository;

    @PostMapping("/doctors")
    public ResponseEntity<Doctor> createDoctor(@RequestBody Doctor doctor) {
        try {
            Doctor _doctor = doctorRepository.save(new Doctor(doctor.getDoctorId(), doctor.getHospitalId(), doctor.getDoctorName(), doctor.getSpeciality(), doctor.getAddress(), doctor.getAbout(), doctor.getCreatedOn()));
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

    @GetMapping("/doctors/id/{doctorId}")
    public ResponseEntity<Doctor> getDoctorByDoctorId(@PathVariable("doctorId") String doctorId) {
        Optional<Doctor> doctorData = doctorRepository.findByDoctorId(doctorId);

        if (doctorData.isPresent()) {
            return new ResponseEntity<>(doctorData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/doctors/name/{doctorName}")
    public ResponseEntity<List<Doctor>> getDoctorByDoctorName(@PathVariable("doctorName") String doctorName) {
        List<Doctor> doctors = new ArrayList<Doctor>();
        doctorRepository.findByDoctorNameContaining(doctorName).forEach(doctors::add);

        if (!doctors.isEmpty()) {
            //doctorRepository.findByDoctorNameContaining(doctorName).;
            return new ResponseEntity<>(doctors, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/doctors/{doctorId}")
    public ResponseEntity<Doctor> updateDoctor(@PathVariable("doctorId") String doctorId, @RequestBody Doctor doctor) {
        Optional<Doctor> doctorData = doctorRepository.findByDoctorId(doctorId);

        if (doctorData.isPresent()) {

            Doctor _doctor = doctorData.get();

            if (doctor.getDoctorName() != null) {
                _doctor.setDoctorName(doctor.getDoctorName());
            }

            if (doctor.getSpeciality() != null) {
                _doctor.setSpeciality(doctor.getSpeciality());
            }

            if (doctor.getAddress() != null) {
                _doctor.setAddress(doctor.getAddress());
            }

            if (doctor.getHospitalId() != null) {
                _doctor.setHospitalId(doctor.getHospitalId());
            }

            if (doctor.getAbout() != null) {
                _doctor.setAbout(doctor.getAbout());
            }

            //if (doctor.getProfile_picture() != null) {
            //    _doctor.setProfile_picture(doctor.getProfile_picture());
            //}

            return new ResponseEntity<>(doctorRepository.save(_doctor), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/doctors/{doctorId}")
    public ResponseEntity<HttpStatus> deleteDoctor(@PathVariable("doctorId") String doctorId) {
        try {
            doctorRepository.deleteById(doctorId);
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
