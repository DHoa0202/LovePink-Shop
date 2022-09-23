package lovepink.model.reponsitories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import lovepink.entities.Role;

@Repository
public interface RoleReponsitory extends JpaRepository<Role,String> {
	
}
