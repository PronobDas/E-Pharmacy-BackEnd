package com.example.restservice.controllers.medicine;

import com.example.restservice.models.medicine.Medicine;
import com.example.restservice.repository.MedicineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1")
public class MedicineController {
    @Autowired
    MedicineRepository medicineRepository;

    @PostMapping("/medicines")
    public ResponseEntity<Medicine> createMedicine(@RequestBody Medicine medicine) {
        try {
            Medicine _medicine = medicineRepository.save(new Medicine(
                    medicine.getName(),
                    medicine.getGenericName(),
                    medicine.getCompanyName(),
                    medicine.getWeight(),
                    medicine.getUnitPrice(),
                    medicine.getSensitivity(),
                    medicine.getImageURL()
            ));
            return new ResponseEntity<>(_medicine, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/medicines")
    public ResponseEntity<List<Medicine>> getAllMedicines() {
        try {
            List<Medicine> medicines = new ArrayList<Medicine>();
            medicineRepository.findAll().forEach(medicines::add);

            if (medicines.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(medicines, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/medicines/id/{id}")
    public ResponseEntity<Medicine> getMedicineById(@PathVariable("id") String id) {
        Optional<Medicine> medicineData = medicineRepository.findById(id);
        if (medicineData.isPresent()) {
            return new ResponseEntity<>(medicineData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/medicines/name/{name}")
    public ResponseEntity<List<Medicine>> getMedicineByName(@PathVariable("name") String name) {
        try {
            List<Medicine> medicines = new ArrayList<Medicine>();
            medicineRepository.findByNameContaining(name).forEach(medicines::add);

            if (medicines.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(medicines, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/medicines/genericName/{genericName}")
    public ResponseEntity<List<Medicine>> getMedicineByGenericName(@PathVariable("genericName") String genericName) {
        try {
            List<Medicine> medicines = new ArrayList<Medicine>();
            medicineRepository.findByGenericNameContaining(genericName).forEach(medicines::add);

            if (medicines.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(medicines, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/medicines/{id}")
    public ResponseEntity<Medicine> updateMedicine(@PathVariable("id") String id, @RequestBody Medicine medicine) {
        Optional<Medicine> medicineData = medicineRepository.findById(id);
        if (medicineData.isPresent()) {
            Medicine _medicine = medicineData.get();

            _medicine.setName(medicine.getName());
            _medicine.setGenericName(medicine.getGenericName());
            _medicine.setCompanyName(medicine.getCompanyName());
            _medicine.setWeight(medicine.getWeight());
            _medicine.setUnitPrice(medicine.getUnitPrice());
            _medicine.setSensitivity(medicine.getSensitivity());
            _medicine.setImageURL(medicine.getImageURL());

            return new ResponseEntity<>(medicineRepository.save(_medicine), HttpStatus.OK);
        } else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/medicines/{id}")
    public ResponseEntity<HttpStatus> deleteMedicine(@PathVariable("id") String id) {
        try {
            medicineRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
