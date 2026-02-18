package org.example.AccountService_A.dto;

public class InventoryDTO {
   private Integer id;
   private String item;

   public InventoryDTO(Integer id, String item) {
       this.id = id;
       this.item = item;
   }
   public InventoryDTO() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }
}