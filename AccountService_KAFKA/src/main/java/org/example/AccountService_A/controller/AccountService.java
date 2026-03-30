package org.example.AccountService_A.controller;
import jakarta.persistence.EntityExistsException;
import jakarta.transaction.Transactional;
import org.example.AccountService_A.dto.InventoryDTO;
import org.example.AccountService_A.dto.PersonDTO;
import org.example.AccountService_A.entity.Inventory;
import org.example.AccountService_A.entity.Person;
import org.example.AccountService_A.resository.InventoryRepository;
import org.example.AccountService_A.resository.PersonRepository;
import org.example.AccountService_A.security.ClassSecurity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;
@Service
public class AccountService {
    private final ObjectMapper objectMapper;
    private final PersonRepository personRepository;
    private final InventoryRepository inventoryRepository;
    private final ClassSecurity classSecurity;
    public AccountService(PersonRepository personRepository, InventoryRepository inventoryRepository, ClassSecurity classSecurity, ObjectMapper objectMapper) {
        this.personRepository = personRepository;
        this.inventoryRepository = inventoryRepository;
        this.classSecurity = classSecurity;
        this.objectMapper = objectMapper;
    }
    @Transactional
    public void saveAccount(String message) {
     PersonDTO user = objectMapper.readValue(message, PersonDTO.class);
     if(user.getEmail() == null || user.getEmail().isBlank()) {
         throw new IllegalArgumentException("Error, email not found");
     }
     if(user.getPassword() == null || user.getPassword().isBlank()) {
         throw new IllegalArgumentException("Error, password not found");
     }
     if(personRepository.existsByEmail(user.getEmail())) {
         throw new EntityExistsException("Error, email duplicated!");
     }
     String hashed = classSecurity.passwordEncoder().encode(user.getPassword());
      personRepository.save(new Person(user.getEmail(), hashed));
    }
    @Transactional
    public void saveInventory(String message) {
        InventoryDTO inventory = objectMapper.readValue(message, InventoryDTO.class);
        if(inventory.getItem() == null || inventory.getItem().isBlank()) {
            throw new IllegalArgumentException("Error, item not found");
        }
        Person person = personRepository.findPersonById(inventory.getId())
                .orElseThrow(() -> new EntityExistsException("Error, person not found"));
        inventoryRepository.save(new Inventory(inventory.getItem(), person));
    }
}