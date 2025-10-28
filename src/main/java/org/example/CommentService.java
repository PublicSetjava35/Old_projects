package org.example;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
@Service
public class CommentService {
    @Value("${suma}")
    private int num;

    public String protoType() {
        return "protype: " + num;
    }
}