package org.example.ClientMainApp.dto;
import lombok.Data;
@Data
public class ResourceDTO {
       private String item;
       private Long id;
       public ResourceDTO(String item) {
              this.item = item;
       }
       public ResourceDTO() {}
}