package soprAjc.Seigneurbackspringbootperso.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import soprAjc.Seigneurbackspringbootperso.model.Compagnon;
import soprAjc.Seigneurbackspringbootperso.model.Personnage;

public interface CompagnonRepository extends JpaRepository<Compagnon, Long>{

	public List<Compagnon> findByNomContaining(String string);
	
	@Query("select c from Compagnon c left join fetch c.maitre where c.id=:id")
	Optional<Compagnon> findByIdWithMaitre(@Param("id") Long id);

	@Transactional
	@Modifying
	@Query("update Compagnon c set c.maitre=null where c.maitre=:maitre")
	void updateMaitreCompagnon(@Param("maitre") Personnage maitre);

	@Transactional
	@Modifying
	@Query("delete Compagnon c where c.maitre=:maitre")
	void deleteByMaitre(@Param("maitre") Personnage maitre);

}
