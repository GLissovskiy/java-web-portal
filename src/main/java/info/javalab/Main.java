package info.javalab;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;



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
@RestController

public class Main {

    // db
    private static List<Customer> customers;

    static {
        customers = new ArrayList<>();
        Customer korben = new Customer(
                1,
                "Korben",
                "korben@mail.com",
                33
        );
        customers.add(korben);
        Customer dallas = new Customer(
                2,
                "Dallas",
                "dallas@mail.com",
                32
        );
        customers.add(dallas);
    }

    public static void main(String[] args){
        System.out.println(customers);
        SpringApplication.run(Main.class, args);

    }

    static class Customer{
        private Integer id;
        private String name;
        private String email;
        private Integer age;

        public Customer(){}

        public Customer(Integer id, String name, String email, Integer age) {
            this.id = id;
            this.name = name;
            this.email = email;
            this.age = age;

        }

        public Integer getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getEmail() {
            return email;
        }

        public Integer getAge() {
            return age;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

        @Override
        public String toString() {
            return "Customer{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", email='" + email + '\'' +
                    ", age=" + age +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (o == null || getClass() != o.getClass()) return false;
            Customer customer = (Customer) o;
            return Objects.equals(id, customer.id) && Objects.equals(name, customer.name) && Objects.equals(email, customer.email) && Objects.equals(age, customer.age);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, name, email, age);
        }
    }


    @GetMapping("/check")
    public CheckResponse check(
            @RequestParam(value = "name", required = false) String appName){

        String appMessage = appName == null || appName.isBlank() ? "Application started..." : appName + " application started...";
         CheckResponse response = new CheckResponse(
                 appMessage,
                List.of("Golang", "Java", "Javascript"),
                new Person("Korben", 30, 30_000)
        );
         return response;
    }

    record Person(String name, int age , double amount){}

    record CheckResponse(
            String check,
            List<String> languages,
            Person person){
    }

    /*

    // jackson make conversion java classes to JSON objects
    record CheckResponse(String check){} // equals to the next class:
    class CheckResponse {
        private final String check;

        public CheckResponse(String check) {
            this.check = check;
        }

        public String getCheck() {
            return check;
        }

        @Override
        public String toString() {
            return "CheckResponse{" +
                    "check='" + check + '\'' +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (o == null || getClass() != o.getClass()) return false;
            CheckResponse that = (CheckResponse) o;
            return Objects.equals(check, that.check);
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(check);
        }
    }
    */



}
