package org.example.AccountService.service;

import org.example.AccountService.basedb.AccountDTO;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:8080")
public class AccountController {

    private AccountService service;

    public AccountController(AccountService service) {
        this.service = service;
    }

    @PostMapping("/login")
    public Map<String, Object> mapEntity(@RequestBody AccountDTO accountDTO) {
        try {
            String email = accountDTO.getEmail();
            String password = accountDTO.getPassword();
            if(email.isBlank() && password.isBlank()) return Map.of("ke_null", true);
            String hash = service.hashedPassword(accountDTO.getPassword());
            service.saveBase(accountDTO.getEmail(), hash, service.time());

            return Map.of("ok_ful", true, "timer_log", service.time());
        } catch (DataIntegrityViolationException exception) {
            return Map.of("err_ful", true);
        }
    }
}