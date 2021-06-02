package com.example.restservice.controllers.location;

import com.example.restservice.models.location.Location;
import com.example.restservice.repository.LocationRepository;
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
public class LocationController {
    @Autowired
    LocationRepository locationRepository;

    @PostMapping("/locations")
    public ResponseEntity<Location> createLocation(@RequestBody Location location) {
        try {
            Location _location = locationRepository.save(new Location(location.getLatitude(), location.getLongitude(), location.getUpazila(), location.getDistrict()));
            return new ResponseEntity<>(_location, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/locations")
    public ResponseEntity<List<Location>> getAllLocations() {
        try {
            List<Location> locations = new ArrayList<Location>();
            locationRepository.findAll().forEach(locations::add);

            if (locations.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(locations, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/locations/id/{id}")
    public ResponseEntity<Location> getLocationById(@PathVariable("id") String id) {
        Optional<Location> locationData = locationRepository.findById(id);

        if (locationData.isPresent()) {
            return new ResponseEntity<>(locationData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/locations/{id}")
    public ResponseEntity<Location> updateLocation(@PathVariable("id") String id, @RequestBody Location location) {
        Optional<Location> locationData = locationRepository.findById(id);

        if (locationData.isPresent()) {
            Location _location = locationData.get();

            _location.setLongitude(location.getLongitude());
            _location.setLatitude(location.getLatitude());
            _location.setUpazila(location.getUpazila());
            _location.setDistrict(location.getDistrict());

            return new ResponseEntity<>(locationRepository.save(_location), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/locations/{id}")
    public ResponseEntity<HttpStatus> deleteLocation(@PathVariable("id") String id) {
        try {
            locationRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
