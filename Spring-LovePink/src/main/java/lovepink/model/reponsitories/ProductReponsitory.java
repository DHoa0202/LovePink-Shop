package lovepink.model.reponsitories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import lovepink.entities.Product;

@Repository
public interface ProductReponsitory extends JpaRepository<Product, Integer> {
	@Query("SELECT p FROM Product p WHERE p.category.id=?1")
	public List<Product> findByCategory(String cid);
	
}
