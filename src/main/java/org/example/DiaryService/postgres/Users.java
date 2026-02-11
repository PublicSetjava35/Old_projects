package org.example.DiaryService.postgres;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "Users", indexes = @Index(columnList = "token"))
public class Users {
      @Id
      @GeneratedValue(strategy = GenerationType.IDENTITY)
      private Integer id;
      @Column(unique = true)
      private String token;
      @OneToMany(mappedBy = "users")
      private List<Task> taskList;

    public List<Task> getTaskList() {
        return taskList;
    }

    public void setTaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    public Users(String token) {
          this.token = token;
      }

      public Users() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}