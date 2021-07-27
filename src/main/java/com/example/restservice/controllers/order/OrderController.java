package com.example.restservice.controllers.order;

import com.example.restservice.models.medicine.Medicine;
import com.example.restservice.models.order.Order;
import com.example.restservice.repository.OrderRepository;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class OrderController {
    @Autowired
    OrderRepository orderRepository;

    @PostMapping("/orders")
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        try {
            Order _order = orderRepository.save(new Order(
                    order.getUserId(),
                    order.getPhone(),
                    order.getLocation(),
                    order.getTotalPrice(),
                    order.getUnits(),
                    order.getMedicines()
            ));
            return new ResponseEntity<>(_order, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //For adding medicines to the order
    @PutMapping("/orders/{orderId}/medicine/{unit}")
    public ResponseEntity<Order> addMedicine(@PathVariable String orderId, @RequestBody Medicine medicine, @PathVariable int unit){
        Optional<Order> orderData = orderRepository.findById(orderId);

        if (orderData.isPresent()) {
            Order _order = orderData.get();

            _order.setMedicine(medicine);
            _order.setUnit(unit);
            _order.setTotalPrice(_order.calculateTotalPrice());
            return new ResponseEntity<>(orderRepository.save(_order), HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    //For adding prescription to the order
    @PutMapping("/orders/{orderId}/prescription")
    public ResponseEntity<Order> addPrescription(@PathVariable String orderId, @RequestParam("prescription")MultipartFile prescription, Model model) throws IOException {
        Optional<Order> orderData = orderRepository.findById(orderId);

        if (orderData.isPresent()) {
            Order _order = orderData.get();

            _order.setPrescription(new Binary(BsonBinarySubType.BINARY, prescription.getBytes()));

            return new ResponseEntity<>(orderRepository.save(_order), HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/orders")
    public ResponseEntity<List<Order>> getAllOrders() {
        try {
            List<Order> orders = new ArrayList<Order>();
            orderRepository.findAll().forEach(orders::add);

            if (orders.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(orders, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/orders/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable String id) {
        try {
            Optional<Order> orderData = orderRepository.findById(id);

            if (orderData.isPresent())
                return new ResponseEntity<>(orderData.get(), HttpStatus.OK);
            else
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/orders/user/{userId}")
    public ResponseEntity<List<Order>> getOrderByUserId(@PathVariable String userId){
        try {
            List<Order> orderData = new ArrayList<>();
            orderRepository.findByUserId(userId).forEach(orderData::add);

            if (orderData.isEmpty())
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            else
                return new ResponseEntity<>(orderData, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Getting order list based on confirmed or not.
    @GetMapping("/orders/confirmed/{tfValue}")
    public ResponseEntity<List<Order>> getOrderByConfirmed(@PathVariable Boolean tfValue){
        try {
            List<Order> orderData = new ArrayList<>();
            orderRepository.findByConfirmed(tfValue).forEach(orderData::add);

            if (orderData.isEmpty())
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            else
                return new ResponseEntity<>(orderData, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/orders/{id}")
    public ResponseEntity<Order> updateOrder(@PathVariable String id, @RequestBody Order order){
        Optional<Order> orderData = orderRepository.findById(id);

        if (orderData.isPresent()) {
            Order _order = orderData.get();

            _order.setUserId(order.getUserId());
            _order.setPhone(order.getPhone());
            _order.setUnits(order.getUnits());
            _order.setTotalPrice(order.getTotalPrice());
            _order.setLocation(order.getLocation());
            _order.setMedicines(order.getMedicines());
            _order.setConfirmed(order.getConfirmed());
            _order.setPrescription(order.getPrescription());

            return new ResponseEntity<>(orderRepository.save(_order), HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("orders/{id}")
    public ResponseEntity<HttpStatus> deleteOrder(@PathVariable("id") String id) {
        try {
            orderRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
