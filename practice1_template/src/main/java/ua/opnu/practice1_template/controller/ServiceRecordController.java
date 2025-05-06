package ua.opnu.practice1_template.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ua.opnu.practice1_template.entity.ServiceRecordEntity;
import ua.opnu.practice1_template.services.ServiceRecordServices;

@RestController
@RequestMapping("/servicerecords")
public class ServiceRecordController {

    private final ServiceRecordServices serviceRecordsServices;

    public ServiceRecordController(ServiceRecordServices serviceRecordsServices){
        this.serviceRecordsServices = serviceRecordsServices;
    }
    
    @PostMapping
    public ResponseEntity<ServiceRecordEntity> createMechanic(@RequestBody ServiceRecordEntity servicerecord) {
        return new ResponseEntity<>(serviceRecordsServices.createServiceRecord(servicerecord), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ServiceRecordEntity>> getAllMechanics() {
        return new ResponseEntity<>(serviceRecordsServices.getAllServiceRecords(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServiceRecordEntity> updateMechanic(@PathVariable Long id, @RequestBody ServiceRecordEntity servicerecord) {
        return new ResponseEntity<>(serviceRecordsServices.updateServiceRecord(id, servicerecord), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public HttpStatus deleteMechanic(@PathVariable Long id){
        serviceRecordsServices.deleteServiceRecord(id);
        return HttpStatus.OK;
    }
    
    @GetMapping("/price/{id}")
    public ResponseEntity<BigDecimal> totalPrice(@PathVariable Long id){
        return new ResponseEntity<>(serviceRecordsServices.totalPrice(id), HttpStatus.OK);
    }

    @GetMapping("/mechanicServices/{id}")
    public ResponseEntity<List<ServiceRecordEntity>> findAllByMechanicId(@PathVariable Long id){
        return new ResponseEntity<>(serviceRecordsServices.findAllByMechanicId(id), HttpStatus.OK);
    }
}
