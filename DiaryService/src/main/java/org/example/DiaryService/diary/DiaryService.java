package org.example.DiaryService.diary;

import jakarta.transaction.Transactional;
import org.example.DiaryService.postgres.RepositoryJPA;
import org.example.DiaryService.postgres.Task;
import org.example.DiaryService.postgres.TaskRepository;
import org.example.DiaryService.postgres.Users;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class DiaryService {
      private final RepositoryJPA repositoryJPA;
      private TaskRepository taskRepository;
      public DiaryService(RepositoryJPA repositoryJPA, TaskRepository taskRepository) {
          this.repositoryJPA = repositoryJPA;
          this.taskRepository = taskRepository;
      }

      @Transactional
      public List<TaskResponse> taskList(String token) {
          List<Task> taskList = taskRepository.findByUsersToken(token);
          return taskList.stream().map(e -> new TaskResponse(e.getId(), e.getContent())).toList();
      }


      @Transactional
      public Task saveContentUser(String content, String token) {
          Users users = repositoryJPA.findUsersByToken(token).orElseThrow(() -> new RuntimeException("Ошибка, пользователь не найден"));
          Task task = new Task();
          task.setContent(content);
          task.setUsers(users);
          users.getTaskList().add(task);
          return taskRepository.save(task);
      }
      @Transactional
      public void createUser(String token) {
          Users users = new Users(token);
          repositoryJPA.save(users);
      }

    public void deleteLastTask(String token) {
     List<Task> taskList = taskRepository.findByUsersToken(token);
     if(taskList.isEmpty()) {
         new RuntimeException("Список пуст!");
     }
     Task task = taskList.get((taskList.size() - 1));
     taskRepository.delete(task);
    }
}