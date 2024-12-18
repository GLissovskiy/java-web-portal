package info.javalab.customer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest // load application context with a bunch of beans we do not need
class CustomerRepositoryTest {

    @Autowired // equals to the public constructor of the class with class as parameter
    private CustomerRepository underTest;

    @Autowired
    private ApplicationContext applicationContext;

    /*
    public CustomerRepositoryTest(CustomerRepository underTest) {
        this.underTest = underTest;
    }
     */

    @BeforeEach
    void setUp() {
        System.out.println(applicationContext.getBeanDefinitionCount());
    }

    @Test
    void existsCustomerByEmail() {
    }

    @Test
    void existsCustomerById() {
    }
}