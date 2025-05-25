package com.example.Spring_core_tasks;
import java.text.SimpleDateFormat;
import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class DateConfig {
  @Bean("formatter")
  @Profile("ru")
  public SimpleDateFormat ru() {
    return new SimpleDateFormat("EEEE, d MMMM, yyyy", new Locale("ru"));
  }

  @Bean("formatter")
  @Profile("en")
  public SimpleDateFormat en() {
    return new SimpleDateFormat("EEEE, MMMM d, yyyy", Locale.ENGLISH);
  }

  @Bean("isoFormatter")
  public SimpleDateFormat iso() {
    return new SimpleDateFormat("yyyy-MM-dd");
  }
}