package org.example.ClientService_B.dto;

public class ItemDTO {
    private String item;
    private Integer id;

    public ItemDTO(Integer id, String item) {
        this.item = item;
        this.id = id;
    }
    public ItemDTO() {}

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}