package hr.spring.fina.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import hr.spring.fina.model.Obavijest;


public interface ObavijestRepository extends JpaRepository<Obavijest, Long> {

	@Query(
			  value = "SELECT *  FROM obavijesti u WHERE u.id_fakultet = ?1 ORDER BY u.datum DESC", 
			  nativeQuery = true)
	Collection<Obavijest> findAllByFakultet(long id_fakultet);
	
	@Query(
			  value = "SELECT *  FROM obavijesti u WHERE u.id_fakultet = ?1 AND u.id_studij = ?2 ORDER BY u.datum DESC", 
			  nativeQuery = true)
	Collection<Obavijest> findAllByFakultetStudij(long id_fakultet, long id_studij);

}
