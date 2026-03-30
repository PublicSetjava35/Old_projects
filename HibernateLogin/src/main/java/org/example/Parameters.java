package org.example;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

@Component
@Controller
public class Parameters {
    @Value("${num.keys}")
    private String keys;
    @Value("${num.level}")
    private int level;
    public String getKeys() {
        return keys;
    }
    public int getLevel() {
        return level;
    }
}