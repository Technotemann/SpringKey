package key.reel.demo.dao;

import key.reel.demo.entity.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleJpaDao extends CrudRepository<Role, Integer> {

}
