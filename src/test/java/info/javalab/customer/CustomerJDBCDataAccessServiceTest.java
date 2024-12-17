package info.javalab.customer;

import info.javalab.AbstractTestcontainersUnitTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class CustomerJDBCDataAccessServiceTest extends AbstractTestcontainersUnitTest {

    private CustomerJDBCDataAccessService underTest;
    private CustomerRowMapper customerRowMapper = new CustomerRowMapper();

    @BeforeEach
    void setUp() {
        underTest = new CustomerJDBCDataAccessService(
                getJdbcTemplate(),
                customerRowMapper

        );

    }

    @Test
    void selectAllCustomers() {
        //Given
        Customer customer = new Customer(
                FAKER.name().fullName(),
                FAKER.internet().safeEmailAddress()+ "-"+UUID.randomUUID(),
                20
        );

        underTest.insertCustomer(customer);

        //When

        List<Customer> customers = underTest.selectAllCustomers();

        //Then
        assertThat(customers).isNotEmpty();

    }

    @Test
    void selectCustomerById() {

        //Given

        String email = FAKER.internet().safeEmailAddress()+ "-"+UUID.randomUUID();
        Customer customer = new Customer(
                FAKER.name().fullName(),
                email,
                20
        );

        underTest.insertCustomer(customer);

        Integer id = underTest.selectAllCustomers()
                .stream()
                .filter(c -> c.getEmail().equals(email))
                .map(c -> c.getId())
                .findFirst()
                .orElseThrow();

        // When

        Optional<Customer> actual = underTest.selectCustomerById(id);

        // Then
        assertThat(actual).isPresent().hasValueSatisfying( c->{
            assertThat(c.getId()).isEqualTo(id);
            assertThat(c.getEmail()).isEqualTo(customer.getEmail());
            assertThat(c.getName()).isEqualTo(customer.getName());
            assertThat(c.getAge()).isEqualTo(customer.getAge());
        });

    }

    @Test
    void willReturnEmptyWhenSelectCustomerById() {

        // Given
        int id = -1;

        // When
        var actual = underTest.selectCustomerById(id);


        // Then
        assertThat(actual).isEmpty();


    }

    @Test
    void insertCustomer() {
    }

    @Test
    void updateCustomer() {
    }

    @Test
    void deleteCustomer() {
    }

    @Test
    void existsCustomerByEmail() {
    }

    @Test
    void existsCustomerById() {
    }
}