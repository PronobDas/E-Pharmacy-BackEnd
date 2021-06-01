package com.example.restservice.controllers.report;

import com.example.restservice.models.patient.Patient;
import com.example.restservice.models.report.Report;
import com.example.restservice.repository.PatientRepository;
import com.example.restservice.repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class ReportController {

    @Autowired
    ReportRepository reportRepository;

    @GetMapping("/reports")
    public ResponseEntity<List<Report>> getAllReports() {
        try {
            List<Report> reports = new ArrayList<Report>();
            reportRepository.findAll().forEach(reports::add);

            if (reports.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(reports, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/reports/id/{id}")
    public ResponseEntity<Report> getReportById(@PathVariable("id") String id) {
        Optional<Report> reportData = reportRepository.findById(id);

        if (reportData.isPresent()) {
            return new ResponseEntity<>(reportData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/reports/patient/{patientId}")
    public ResponseEntity<Report> getReportByPatientId(@PathVariable("patientId") String patientId) {
        Optional<Report> reportData = reportRepository.findByPatientId(patientId);

        if (reportData.isPresent()) {
            return new ResponseEntity<>(reportData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Autowired
    PatientRepository patientRepository;

    @GetMapping("/reports/namedob")
    public ResponseEntity<Report> getReportByPatientNameAndDob(@RequestBody Patient p) {
        Optional<Patient> patientData = patientRepository.findByPatientNameAndDateOfBirth(p.getPatientName(), p.getDateOfBirth());
        if (patientData.isPresent()) {
            Patient patient = patientData.get();
            Optional<Report> reportData = reportRepository.findByPatientId(patient.getUpi());
            if (reportData.isPresent()) {
                return new ResponseEntity<>(reportData.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @PostMapping("/reports")
    public ResponseEntity<Report> createReport(@RequestBody Report report) {
        try {
            Report _report = reportRepository.save(new Report(
                    report.getPatientId(),
                    report.getDoctorId(),
                    report.getBloodPressure(),
                    report.getPulseRate(),
                    report.getWeight(),
                    report.getAllergies(),
                    report.getDisabilities(),
                    report.getMedicines(),
                    report.getDiets(),
                    report.getPatientHistory(),
                    report.getFollowupDoctorId()
            ));
            return new ResponseEntity<>(_report, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/reports/{id}")
    public ResponseEntity<Report> updateReport(@PathVariable("id") String id, @RequestBody Report report) {
        Optional<Report> reportData = reportRepository.findById(id);

        if (reportData.isPresent()) {
            Report _report = reportData.get();

            if (report.getPatientId() != null) {
                _report.setPatientId(report.getPatientId());
            }

            if (report.getDoctorId() != null) {
                _report.setDoctorId(report.getDoctorId());
            }

            if (report.getBloodPressure() != null) {
                _report.setBloodPressure(report.getBloodPressure());
            }

            if (report.getPulseRate() != null) {
                _report.setPulseRate(report.getPulseRate());
            }

            if (report.getWeight() != null) {
                _report.setWeight(report.getWeight());
            }

            if (report.getAllergies() != null) {
                _report.setAllergies(report.getAllergies());
            }

            if (report.getDisabilities() != null) {
                _report.setDisabilities(report.getDisabilities());
            }

            if (report.getMedicines() != null) {
                _report.setMedicines((report.getMedicines()));
            }

            if (report.getDiets() != null) {
                _report.setDiets(report.getDiets());
            }

            if (report.getPatientHistory() != null) {
                _report.setPatientHistory(report.getPatientHistory());
            }

            if (report.getFollowupDoctorId() != null) {
                _report.setFollowupDoctorId(report.getFollowupDoctorId());
            }

            return new ResponseEntity<>(reportRepository.save(_report), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/reports/{id}")
    public ResponseEntity<HttpStatus> deleteReport(@PathVariable("id") String id) {
        try {
            reportRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/reports")
    public ResponseEntity<HttpStatus> deleteAllReports() {
        try {
            reportRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}