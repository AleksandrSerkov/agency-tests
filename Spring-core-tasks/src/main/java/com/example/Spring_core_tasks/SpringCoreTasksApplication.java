package com.example.Spring_core_tasks;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({ DateConfig.class })                  // форматтеры
// metrics пакуются автоматически через @ComponentScan
public class SpringCoreTasksApplication {
  public static void main(String[] args) {
    // например: -Dspring.profiles.active=ru
    SpringApplication.run(SpringCoreTasksApplication.class, args);
  }
}

