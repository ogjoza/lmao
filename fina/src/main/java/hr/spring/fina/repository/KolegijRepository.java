package hr.spring.fina.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import hr.spring.fina.model.Kolegij;


public interface KolegijRepository extends JpaRepository<Kolegij, Long> {

	@Query(
			  value = "SELECT *  FROM kolegij k WHERE k.studij = ?1 ORDER BY k.naziv", 
			  nativeQuery = true)
	Collection<Kolegij> odrediKolegijaPremaStudiju(String studij);
	
}
