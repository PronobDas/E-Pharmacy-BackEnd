package com.example.restservice.controllers.order;

import com.example.restservice.models.medicine.Medicine;
import com.example.restservice.models.order.Order;
import com.example.restservice.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
                    order.getLocation(),
                    order.getTotalPrice(),
                    order.getPrescription(),
                    order.getUnits(),
                    order.getMedicines()
            ));
            return new ResponseEntity<>(_order, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/orders/{orderId}/medicine/{unit}")
    public ResponseEntity<Order> addMedicine(@PathVariable String orderId, @PathVariable int unit ,@RequestBody Medicine medicine){
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
    public ResponseEntity<Order> getOrderById(@PathVariable String id){
        Optional<Order> orderData = orderRepository.findById(id);

        if (orderData.isPresent())
            return new ResponseEntity<>(orderData.get(), HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}