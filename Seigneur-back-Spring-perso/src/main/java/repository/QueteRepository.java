package repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import model.Quete;

public interface QueteRepository extends JpaRepository<Quete, Long>{
	
	public List<Quete> findAllByNomContaining(String string);

}
