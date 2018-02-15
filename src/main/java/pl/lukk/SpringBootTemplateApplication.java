package pl.lukk;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import pl.lukk.service.StorageService;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class SpringBootTemplateApplication
{

    public static void main(String[] args)
    {
        SpringApplication.run(SpringBootTemplateApplication.class, args);
    }

    @Bean
    CommandLineRunner init(StorageService storageService)
    {
        return (args) ->
        {
            storageService.init();
        };
    }
}
