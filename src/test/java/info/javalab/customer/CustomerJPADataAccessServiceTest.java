package info.javalab.customer;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

class CustomerJPADataAccessServiceTest {

    private CustomerJPADataAccessService underTest;
    private AutoCloseable closeable;

    @Mock
    private CustomerRepository customerRepository;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
        underTest = new CustomerJPADataAccessService(customerRepository);
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }

    @Test
    void selectAllCustomers() {
        // When
        underTest.selectAllCustomers();

        // Then
        Mockito.verify(customerRepository)
                .findAll();
    }

    @Test
    void selectCustomerById() {
        // Given

        int id = 1;
        // When
        underTest.selectCustomerById(id);

        // Then
        Mockito.verify(customerRepository)
                .findById(id);
    }

    @Test
    void insertCustomer() {

        // Given
        Customer customer = new Customer(
                1 , "Korben", "korben@example.xom", 22
        );
        // When
        underTest.insertCustomer(customer);
        // Then
        Mockito.verify(customerRepository).save(customer);
    }

    @Test
    void updateCustomer() {

        // Given
        Customer customer = new Customer(
                1 , "Korben", "korben@example.xom", 22
        );
        // When
        underTest.updateCustomer(customer);
        // Then
        Mockito.verify(customerRepository).save(customer);
    }

    @Test
    void deleteCustomer() {

        // Given
        int id = 1;
        // When
        underTest.deleteCustomer(id);
        // Then
        Mockito.verify(customerRepository).deleteById(id);
    }

    @Test
    void existsCustomerByEmail() {

        // Given
        String email = "korben@example.xom";
        // When

        underTest.existsCustomerByEmail(email);

        // Then
        Mockito.verify(customerRepository)
                .existsCustomerByEmail(email);
    }

    @Test
    void existsCustomerById() {

        // Given
        int id = 1;
        // When
        underTest.existsCustomerById(id);
        // Then
        Mockito.verify(customerRepository).existsCustomerById(id);
    }
}