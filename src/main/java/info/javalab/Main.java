package info.javalab;

import info.javalab.customer.Customer;
import info.javalab.customer.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.List;

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
        ConfigurableApplicationContext applicationContext = SpringApplication.run(Main.class, args);
        printBeans(applicationContext);

    }

    @Bean
    CommandLineRunner runner(CustomerRepository customerRepository) {
        return args -> {
            Customer korben = new Customer(
                    "Korben",
                    "korben@mail.com",
                    33
            );
            Customer dallas = new Customer(
                    "Dallas",
                    "dallas@mail.com",
                    32
            );

            List<Customer> customers = List.of(korben, dallas);
            customerRepository.saveAll(customers);
        };
    }

    @Bean("fooo")
    public Foo getFoo(){
        return new Foo("bar");
    }
    record Foo(String name){}

    private static void printBeans(ConfigurableApplicationContext ctx) {

        String[] beanDefinitionNames = ctx.getBeanDefinitionNames();

        for (String beanDefinitionName : beanDefinitionNames) {
            System.out.println(beanDefinitionName);

        }
    }

}
