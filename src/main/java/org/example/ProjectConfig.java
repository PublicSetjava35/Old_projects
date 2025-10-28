package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.*;

@Configuration
@ComponentScan(basePackages = "org.example")
@PropertySource("classpath:hibernates.properties")
public class ProjectConfig {
    @Autowired
    private CommentService commentService;

    @Bean
    @Scope(BeanDefinition.SCOPE_PROTOTYPE)
    CommentService commentService() {
        return new CommentService();
    }
    @Bean
    @Scope(BeanDefinition.SCOPE_PROTOTYPE)
    public String commentService2() {
        commentService.protoType();
        return commentService.protoType();
    }
}