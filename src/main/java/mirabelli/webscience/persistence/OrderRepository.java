package mirabelli.webscience.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    String findOrderStatusById(Long id);

    @Query("SELECT o FROM Order o WHERE o.orderStatus = :status ORDER BY o.createdAt ASC")
    Order findOldestOrderByStatus(@Param("status") String status);

    boolean existsByOrderStatus(String orderStatus);

}