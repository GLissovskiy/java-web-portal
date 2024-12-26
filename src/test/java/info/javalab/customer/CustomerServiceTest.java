package info.javalab.customer;

import info.javalab.exception.DuplicateResourceException;
import info.javalab.exception.ResourceNotFoundException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
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
    void willThrowWhenGetCustomerReturnsEmptyOptional() {

        int customerId = 100;

        Mockito.when(customerDao
                        .selectCustomerById(customerId))
                .thenReturn(Optional.empty());

        // Then
        assertThatThrownBy(() -> underTest.getCustomer(customerId))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining("customer with id [%s] not found".formatted(customerId));
    }

    @Test
    void addCustomer() {

        String email = "korben@test.com";


        Mockito.when(customerDao.existsCustomerByEmail(email))
                .thenReturn(Boolean.FALSE);

        CustomerRegistrationRequest request = new CustomerRegistrationRequest(
                "Korben"
                , email
                , 22
        );

        underTest.addCustomer(request);

        ArgumentCaptor<Customer> customerCaptor = ArgumentCaptor.forClass(Customer.class);

        Mockito.verify(customerDao).insertCustomer(customerCaptor.capture());

        Customer capturedCustomer =  customerCaptor.getValue();

        assertThat(capturedCustomer.getId()).isNull();
        assertThat(capturedCustomer.getName()).isEqualTo(request.name());
        assertThat(capturedCustomer.getEmail()).isEqualTo(request.email());
        assertThat(capturedCustomer.getAge()).isEqualTo(request.age());

    }



    @Test
    void willThrowWhenEmailExistsWhileAddCustomer() {

        String email = "korben@test.com";


        Mockito.when(customerDao.existsCustomerByEmail(email))
                .thenReturn(Boolean.TRUE);

        CustomerRegistrationRequest request = new CustomerRegistrationRequest(
                "Korben"
                , email
                , 22
        );

        //underTest.addCustomer(request);

        assertThatThrownBy(() -> underTest.addCustomer(request))
                .isInstanceOf(DuplicateResourceException.class)
                .hasMessage("customer with email [%s] exists".formatted(email));

        Mockito.verify(customerDao, Mockito.never()).insertCustomer(Mockito.any());
    }

    @Test
    void updateCustomer() {
    }

    @Test
    void deleteCustomer() {
    }
}