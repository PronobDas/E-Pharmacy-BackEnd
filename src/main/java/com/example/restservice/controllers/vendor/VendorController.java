package com.example.restservice.controllers.vendor;

import com.example.restservice.models.vendor.Vendor;
import com.example.restservice.repository.VendorRepository;
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
public class VendorController {
    @Autowired
    VendorRepository vendorRepository;

    @PostMapping("/vendors")
    public ResponseEntity<Vendor> createVendor(@RequestBody Vendor vendor) {
        try {
            Vendor _vendor = vendorRepository.save(new Vendor(vendor.getName(), vendor.getLocationId(), vendor.getContactNo(), vendor.getStatus()));
            return new ResponseEntity<>(_vendor, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/vendors")
    public ResponseEntity<List<Vendor>> getAllVendors() {
        try {
            List<Vendor> vendors = new ArrayList<Vendor>();
            vendorRepository.findAll().forEach(vendors::add);

            if (vendors.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(vendors, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/vendors/id/{id}")
    public ResponseEntity<Vendor> getVendorById(@PathVariable("id") String id) {
        Optional<Vendor> vendorData = vendorRepository.findById(id);

        if (vendorData.isPresent()) {
            return new ResponseEntity<>(vendorData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/vendors/locationId/{locationId}")
    public ResponseEntity<List<Vendor>> getVendorByLocationId(@PathVariable("locationId") String locationId) {
        List<Vendor> vendors = new ArrayList<Vendor>();
        vendorRepository.findByLocationId(locationId).forEach(vendors::add);

        if (!vendors.isEmpty()) {
            return new ResponseEntity<>(vendors, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/vendors/{id}")
    public ResponseEntity<Vendor> updateVendor(@PathVariable("id") String id, @RequestBody Vendor vendor) {
        Optional<Vendor> vendorData = vendorRepository.findById(id);
        if (vendorData.isPresent()){
            Vendor _vendor = vendorData.get();

            _vendor.setName(vendor.getName());
            _vendor.setLocationId(vendor.getLocationId());
            _vendor.setContactNo(vendor.getContactNo());
            _vendor.setStatus(vendor.getStatus());

            return new ResponseEntity<>(vendorRepository.save(_vendor), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/vendors/{id}")
    public ResponseEntity<HttpStatus> deleteVendor(@PathVariable("id") String id) {
        try {
            vendorRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
