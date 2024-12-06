package info.javalab;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public static void main(String[] args){
        SpringApplication.run(Main.class, args);

    }
    @GetMapping("/check")
    public CheckResponse check(){
        return new CheckResponse("Application started...");
    }
    /*
    record CheckResponse(String check){
    }
    */
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




}
