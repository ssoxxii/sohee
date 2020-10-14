package xiaomi.domain.entity;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VerificationRepository extends JpaRepository<Verification, Long>{

	Optional<Verification> findByEmailAndCode(String email, String code); //<entity,id type>
	
}
