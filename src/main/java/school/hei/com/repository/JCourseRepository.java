package school.hei.com.repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import school.hei.com.entity.JCourse;

@Repository
public interface JCourseRepository extends JpaRepository<JCourse, UUID> {}
