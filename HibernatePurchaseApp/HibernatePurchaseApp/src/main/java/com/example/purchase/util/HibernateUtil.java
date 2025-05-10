package com.example.purchase.util;

import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.yaml.snakeyaml.Yaml;
public class HibernateUtil {
  private static final SessionFactory sessionFactory = buildSessionFactory();

  private static SessionFactory buildSessionFactory() {
    // 1) читаем YAML
    Yaml yaml = new Yaml();
    try (InputStream in = 
            HibernateUtil.class.getClassLoader()
                             .getResourceAsStream("application.yml")) {
      Map<String, Object> root = yaml.load(in);
      Map<String, Object> hib = (Map<String, Object>) root.get("hibernate");

      // 2) превращаем в Properties
      Properties props = new Properties();
      Map<String,Object> conn = (Map<String,Object>) hib.get("connection");
      props.put("hibernate.connection.url", conn.get("url"));
      props.put("hibernate.connection.username", conn.get("username"));
      props.put("hibernate.connection.password", conn.get("password"));
      props.put("hibernate.connection.driver_class", conn.get("driver_class"));
      props.put("hibernate.hbm2ddl.auto", hib.get("hbm2ddl_auto"));
      props.put("hibernate.show_sql", hib.get("show_sql"));
      props.put("hibernate.format_sql", hib.get("format_sql"));

      // 3) строим реестр и саму фабрику
      StandardServiceRegistry registry = 
         new StandardServiceRegistryBuilder()
           .applySettings(props)
           .build();

      MetadataSources sources = new MetadataSources(registry)
          .addAnnotatedClass(com.example.purchase.entity.Person.class)
          .addAnnotatedClass(com.example.purchase.entity.Product.class)
          .addAnnotatedClass(com.example.purchase.entity.Purchase.class);

      Metadata meta = sources.getMetadataBuilder().build();
      return meta.getSessionFactoryBuilder().build();
    } catch (Exception e) {
      throw new ExceptionInInitializerError(e);
    }
  }

  public static SessionFactory getSessionFactory() {
    return sessionFactory;
  }
}
