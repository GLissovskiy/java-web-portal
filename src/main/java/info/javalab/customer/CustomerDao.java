package info.javalab.customer;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


public interface CustomerDao {

    List<Customer> selectAllCustomers();
    Optional<Customer> selectCustomerById(Integer id);
    void insertCustomer(Customer customer);
    void updateCustomer(Customer customer);
    void deleteCustomer(Integer id);
    boolean existsCustomerByEmail(String email);
    boolean existsCustomerById(Integer id);
}
