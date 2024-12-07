package com.test_task.restaurant.services;

import com.test_task.restaurant.exception.ResourceNotFoundException;
import com.test_task.restaurant.models.Supplier;
import com.test_task.restaurant.repositories.SupplierRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SupplierService {

    private final SupplierRepository supplierRepository;

    public SupplierService(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    public Supplier createSupplier(Supplier supplier) {
        return supplierRepository.save(supplier);
    }

    public Supplier findSupplierById(Long id) {
        return supplierRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found supplier with such id: " + id));
    }

    public List<Supplier> findAllSuppliers() {
        return supplierRepository.findAll();
    }

    public void deleteSupplierById(Long id) {
        Optional<Supplier> supplier = supplierRepository.findById(id);
        if (supplier.isEmpty()) throw new ResourceNotFoundException("Not found supplier with such id: " + id);
        supplierRepository.deleteById(id);
    }
}
