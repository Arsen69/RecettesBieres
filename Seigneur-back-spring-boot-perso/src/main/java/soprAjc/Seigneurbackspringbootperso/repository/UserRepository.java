package soprAjc.Seigneurbackspringbootperso.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import soprAjc.Seigneurbackspringbootperso.model.User;

public interface UserRepository extends JpaRepository<User, Long>{
	
	Optional<User> findByLogin(String login);

}
