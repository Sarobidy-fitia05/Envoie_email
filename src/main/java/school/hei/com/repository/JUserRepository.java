package school.hei.com.repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import school.hei.com.entity.JUser;

@Repository
public interface JUserRepository extends JpaRepository<JUser, UUID> {}
