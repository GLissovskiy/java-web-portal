package info.javalab.customer;

import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
 * @RestController equals to next 2 annotations:
 * @Controller
 * @ResponseBody
 * */
@RestController
//@RequestMapping("api/v1/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    /*
    @GetMapping("api/v1/customers") equal to
    @RequestMapping(path = "api/v1/customers", method = RequestMethod.GET)
     */
    @GetMapping("api/v1/customers")
    public List<Customer> getCustomers() {
        return customerService.getAllCustomers();
    }

    @GetMapping("api/v1/customers/{customerId}")
    public Customer getCustomer(@PathVariable("customerId") Integer customerId) {
        return customerService.getCustomer(customerId);
    }

    @PostMapping("api/v1/customers")
    public void registerCustomer(@RequestBody CustomerRegistrationRequest request) {
        customerService.addCustomer(request);
    }

    @DeleteMapping("api/v1/customers/{customerId}")
    public void deleteCustomer(@PathVariable("customerId") Integer customerId) {
        customerService.deleteCustomer(customerId);
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
