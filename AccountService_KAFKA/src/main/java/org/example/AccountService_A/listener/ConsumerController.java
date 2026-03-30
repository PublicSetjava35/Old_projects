package org.example.AccountService_A.listener;

import org.example.AccountService_A.controller.AccountService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class ConsumerController {
    private final AccountService accountService;
    public ConsumerController(AccountService accountService) {
        this.accountService = accountService;
    }
    @KafkaListener(topics = "account", groupId = "account-v")
    public void savePerson(String message) {
        try {
          accountService.saveAccount(message);
       } catch (Exception exception){
          System.err.println("Ошибка, пришел битый json!");
       }
    }
    @KafkaListener(topics = "item", groupId = "account-v")
    public void saveItem(String message) {
        try {
            accountService.saveInventory(message);
        } catch (Exception exception){
            System.err.println("Ошибка, пришел битый json!");
        }
    }
}