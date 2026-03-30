package org.example.DiaryService.diary;

public class TaskResponse {
      private String content;
      private Integer id;

      public Integer getId() {
            return id;
      }

      public void setId(Integer id) {
            this.id = id;
      }

      public TaskResponse(Integer id, String content) {
            this.content = content;
            this.id = id;
      }

      public String getContent() {
            return content;
      }

      public void setContent(String content) {
            this.content = content;
      }
}