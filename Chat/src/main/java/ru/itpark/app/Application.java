package ru.itpark.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import ru.itpark.models.*;

// говорим, что у нас SpringBoot-приложение
// таким образом, автоматически будет запущен tomcat, hibernate
// и куча всего еще
// приложение будет автоматически задеплоено в localhost:8080
@SpringBootApplication
// подключаем JPA-репозитории
@EnableJpaRepositories(basePackages = "ru.itpark.repository")
// здесь вы говорите, какими сущностями будет оперировать
@EntityScan(basePackages = "ru.itpark.models", basePackageClasses = {Jsr310JpaConverters.class})
// чтобы Spring где искать компоненты
@ComponentScan(value = "ru.itpark")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }
}
