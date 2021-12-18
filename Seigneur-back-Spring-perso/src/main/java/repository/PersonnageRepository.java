package repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import model.Personnage;

public interface PersonnageRepository extends JpaRepository<Personnage, Long>{

	
	public List<Personnage> findByNom(String nom);
	
	public List<Personnage> findByVivantTrue();
	
	public List<Personnage> findByVivantFalse();
	
	@Query("SELECT distinct p FROM Personnage p left join fetch p.quetes q where p.id=:id")
	Optional<Personnage> findByIdWithQuetes(@Param("id") Long id);
	
	@Query("SELECT distinct p FROM Personnage p left join fetch p.inventaire i where p.id=:id")
	Optional<Personnage> findByIdWithInventaire(@Param("id") Long id);
	
	@Query("SELECT distinct p FROM Personnage p left join fetch p.inventaire i left join fetch p.quetes q where p.id=:id")
	Optional<Personnage> findByIdWithInventaireAndQuetes(@Param("id") Long id);
	
	
	
}
