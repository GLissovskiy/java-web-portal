package info.javalab.customer;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class) // replace @AfterEach annotation and tearDown method
class CustomerServiceTest {

    @Mock
    private CustomerDao customerDao;
    private CustomerService underTest;


    @BeforeEach
    void setUp() {

        underTest = new CustomerService(customerDao);

    }

    @Test
    void getAllCustomers() {

        // When
        underTest.getAllCustomers();

        // Then
        Mockito.verify(customerDao).selectAllCustomers();

    }

    @Test
    void getCustomer() {

        int customerId = 100;
        Customer customer = new Customer(
                customerId, "Korben", "korben@test.com", 22
        );
        Mockito.when(customerDao
                        .selectCustomerById(customerId))
                        .thenReturn(Optional.of(customer));

        Customer actual = underTest.getCustomer(customerId);

        Assertions.assertThat(actual).isEqualTo(customer);
    }

    @Test
    void addCustomer() {
    }

    @Test
    void updateCustomer() {
    }

    @Test
    void deleteCustomer() {
    }
}