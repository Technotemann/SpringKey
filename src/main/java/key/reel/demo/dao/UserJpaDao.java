package key.reel.demo.dao;

import key.reel.demo.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserJpaDao extends CrudRepository<User, String> {

}
