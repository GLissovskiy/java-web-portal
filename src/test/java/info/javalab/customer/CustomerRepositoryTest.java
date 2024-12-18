package info.javalab.customer;

import info.javalab.AbstractTestcontainersUnitTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


 //@SpringBootTest // load application context with a bunch of beans we do not need (268 beans to test simple method)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CustomerRepositoryTest extends AbstractTestcontainersUnitTest {

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
        underTest.deleteAll(); // clean db before start (can be checked as debug  underTest.findAll())
        System.out.println(applicationContext.getBeanDefinitionCount());
    }

     @Test
     void existsCustomerByEmailFailsWhenEmailNotPresent() {


         //Given

         String email = FAKER.internet().safeEmailAddress()+ "-"+ UUID.randomUUID();

         // When

         var actual = underTest.existsCustomerByEmail(email);

         // Then
         assertThat(actual).isFalse();

     }

    @Test
    void existsCustomerByEmail() {


        //Given

        String email = FAKER.internet().safeEmailAddress()+ "-"+ UUID.randomUUID();
        Customer customer = new Customer(
                FAKER.name().fullName(),
                email,
                20
        );

        underTest.save(customer);

        // When

        var actual = underTest.existsCustomerByEmail(email);

        // Then
        assertThat(actual).isTrue();

    }

     @Test
     void existsCustomerByIdFailsWhenIdNotPresent() {

         //Given

         Integer id = -1;

         // When

         var actual = underTest.existsCustomerById(id);

         // Then
         assertThat(actual).isFalse();

     }

    @Test
    void existsCustomerById() {

        //Given

        String email = FAKER.internet().safeEmailAddress()+ "-"+ UUID.randomUUID();
        Customer customer = new Customer(
                FAKER.name().fullName(),
                email,
                20
        );

        underTest.save(customer);

        Integer id = underTest.findAll()
                .stream()
                .filter(c -> c.getEmail().equals(email))
                .map(c -> c.getId())
                .findFirst()
                .orElseThrow();

        // When

        var actual = underTest.existsCustomerById(id);

        // Then
        assertThat(actual).isTrue();

    }
}