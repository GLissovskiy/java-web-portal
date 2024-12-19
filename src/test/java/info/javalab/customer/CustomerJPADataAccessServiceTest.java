package info.javalab.customer;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
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
    }

    @Test
    void selectCustomerById() {
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