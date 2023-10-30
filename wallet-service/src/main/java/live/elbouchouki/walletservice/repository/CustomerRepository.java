package live.elbouchouki.billingservice.repository;

import live.elbouchouki.customerservice.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, String> {
    int countByEmail(String email);
}
