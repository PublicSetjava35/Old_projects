package org.example.MainApp.service;

import jakarta.persistence.EntityExistsException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.MainApp.entity.Person;
import org.example.MainApp.entity.Resource;
import org.example.MainApp.repository.PersonRepository;
import org.example.MainApp.repository.ResourceRepository;
import org.example.MainApp.security.SecurityConfiguration;
import org.springframework.stereotype.Service;

@Service
public class ControllerService {
       private final SecurityConfiguration securityConfiguration;
       private final PersonRepository personRepository;
       private final ResourceRepository resourceRepository;
       public ControllerService(PersonRepository personRepository, ResourceRepository resourceRepository, SecurityConfiguration securityConfiguration) {
           this.personRepository = personRepository;
           this.resourceRepository = resourceRepository;
           this.securityConfiguration = securityConfiguration;
       }
       @Transactional
       public void savePerson(String email, String password) {
           if(email == null || email.isBlank()) throw new IllegalArgumentException("Error, email not found");
           if(password == null || password.isBlank()) throw new IllegalArgumentException("Error, password not found");
           if(personRepository.existsByEmail(email)) throw new EntityExistsException("Sory, email is duplicated");
           String encode = securityConfiguration.encode().encode(password);
           personRepository.save(new Person(email, encode));
       }
       @Transactional
       public void saveResource(String item) {
           if(item == null || item.isBlank()) throw new IllegalArgumentException("Error, item not found");
           resourceRepository.save(new Resource(item));
       }
}