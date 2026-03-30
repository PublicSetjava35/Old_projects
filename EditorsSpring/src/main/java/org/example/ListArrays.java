package org.example;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import java.io.*;
public class ListArrays {
    public static void main(String[] args) throws IOException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ProjectConfig.class);
        CommentService commentService = context.getBean("commentService", CommentService.class);
        CommentService commentService2 = context.getBean("commentService", CommentService.class);
        boolean in = commentService == commentService2;
        System.out.println(in);
    }
}