package org.example.AccountService_A.dto;

public class PersonDTO {
   private String email;
   private String password;

   public PersonDTO(String email, String password) {
       this.email = email;
       this.password = password;
   }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}