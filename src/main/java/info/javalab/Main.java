package info.javalab;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
* @SpringBootApplication equals to next 3 annotations:
* @ComponentScan(basePackages = "info.javalab")
* @EnableAutoConfiguration
* @Configuration
* */

/*
* @RestController equals to next 2 annotations:
* @Controller
* @ResponseBody
* */

@SpringBootApplication

public class Main {



    public static void main(String[] args){
        //System.out.println(customers);
        SpringApplication.run(Main.class, args);

    }

}
