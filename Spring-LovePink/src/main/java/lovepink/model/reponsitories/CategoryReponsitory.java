package lovepink.model.reponsitories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import lovepink.entities.Category;

@Repository
public interface CategoryReponsitory extends JpaRepository<Category,String> {
	
}
