package info.javalab.customer;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class CustomerDataAccessService implements CustomerDao{

    // db
    private static List<Customer> customers;

    static {
        customers = new ArrayList<>();
        Customer korben = new Customer(
                1,
                "Korben",
                "korben@mail.com",
                33
        );
        customers.add(korben);
        Customer dallas = new Customer(
                2,
                "Dallas",
                "dallas@mail.com",
                32
        );
        customers.add(dallas);
    }

    @Override
    public List<Customer> selectAllCustomers() {
        return customers;
    }

    @Override
    public Optional<Customer> selectCustomerById(Integer id) {
        return   customers.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst();

    }
}
