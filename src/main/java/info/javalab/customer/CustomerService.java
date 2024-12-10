package info.javalab.customer;

import info.javalab.exception.DuplicateResourceException;
import info.javalab.exception.RequestValidationException;
import info.javalab.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerDao customerDao;

    public CustomerService(@Qualifier("jpa") CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    public List<Customer> getAllCustomers(){
        return customerDao.selectAllCustomers();
    }

    public Customer getCustomer(Integer id){
        return customerDao.selectCustomerById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "customer with id [%s] not found".formatted(id)));

    }

    public void addCustomer(CustomerRegistrationRequest customerRegistrationRequest ){

        String email = customerRegistrationRequest.email();
        if (customerDao.existsCustomerByEmail(email)){
            throw new DuplicateResourceException("customer with email [%s] exists".formatted(email));
        }

        Customer customer = new Customer(
                customerRegistrationRequest.name(),
                customerRegistrationRequest.email(),
                customerRegistrationRequest.age()
        );
        customerDao.insertCustomer(customer);
    }

    public void updateCustomer(Integer customerId, CustomerUpdateRequest customerUpdateRequest ){

        Customer customer = getCustomer(customerId);

        boolean changed = false;

        if (customerUpdateRequest.name() != null && !customerUpdateRequest.name().equals(customer.getName())){
            customer.setName(customerUpdateRequest.name());
            changed = true;
        }

        if (customerUpdateRequest.age() != null && !customerUpdateRequest.age().equals(customer.getAge())){
            customer.setAge(customerUpdateRequest.age());
            changed = true;
        }

        if (customerUpdateRequest.email() != null && !customerUpdateRequest.email().equals(customer.getEmail())){

            if (customerDao.existsCustomerByEmail(customerUpdateRequest.email())){
                throw new DuplicateResourceException("customer with email [%s] exists".formatted(customerUpdateRequest.email()));
            }

            customer.setEmail(customerUpdateRequest.email());
            changed = true;
        }

        if (!changed){
            throw new RequestValidationException("no data changes found");
        }

        customerDao.updateCustomer(customer);

    }



    public void deleteCustomer(Integer customerId){

        // check if exist
        if (!customerDao.existsCustomerById(customerId)){
            throw new ResourceNotFoundException("customer with id [%s] not found".formatted(customerId));
        }
        customerDao.deleteCustomer(customerId);
        // delete
    }

}
