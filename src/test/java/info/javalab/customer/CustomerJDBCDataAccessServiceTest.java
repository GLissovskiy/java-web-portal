package info.javalab.customer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.junit.jupiter.api.Assertions.*;

class CustomerJDBCDataAccessServiceTest {

    private CustomerJDBCDataAccessService underTest;
    private CustomerRowMapper customerRowMapper = new CustomerRowMapper();

    @BeforeEach
    void setUp() {
        underTest = new CustomerJDBCDataAccessService(
                new JdbcTemplate(),
                customerRowMapper

        );



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