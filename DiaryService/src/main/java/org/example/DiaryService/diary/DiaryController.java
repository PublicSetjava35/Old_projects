package org.example.DiaryService.diary;
import org.example.DiaryService.postgres.Task;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class DiaryController {
    private final DiaryService diaryService;

    public DiaryController(DiaryService diaryService) {
        this.diaryService = diaryService;
    }

    @PostMapping("/create_content")
    public Task tasks(@RequestParam String content, @RequestParam String token) {
       return diaryService.saveContentUser(content, token);
    }

    @GetMapping("/get_content")
    public List<TaskResponse> task(@RequestParam String token) {
        return diaryService.taskList(token);
    }

    @PostMapping("/create_user")
    public void creatUser(@RequestParam String token) {
        if(!token.isEmpty()) {
            try {
                diaryService.createUser(token);
            } catch (Exception exception) {
                System.out.println("Обрабатываем временно, но так нельзя, для тестов пойдет");
            }
        }
        System.out.println("Ошибка, этот токен уже существует!");
    }
    @DeleteMapping("/delete_content")
    public void deleteLastTask(@RequestParam String token) {
        diaryService.deleteLastTask(token);
    }
}