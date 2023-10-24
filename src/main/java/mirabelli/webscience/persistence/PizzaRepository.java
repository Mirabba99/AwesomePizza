package mirabelli.webscience.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PizzaRepository extends JpaRepository<Pizza, Long> {

    Pizza findByName(String name);

    void deleteById(Long id);

}


