package croplanet.admin.user.repository;

import croplanet.admin.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findBySessionId(String sessionId);

}
