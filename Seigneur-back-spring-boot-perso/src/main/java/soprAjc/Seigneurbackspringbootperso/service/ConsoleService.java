package soprAjc.Seigneurbackspringbootperso.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import soprAjc.Seigneurbackspringbootperso.repository.UserRepository;

@Service
public class ConsoleService implements CommandLineRunner {

	@Autowired
	private UserRepository userRepo;
	@Autowired
	private PasswordEncoder passwordEncoder;

	private static final Logger LOGGER = LoggerFactory.getLogger(ConsoleService.class);

	@Override
	public void run(String... args) throws Exception {
		//initPassword();
	}

	private void initPassword() {
		userRepo.findAll().forEach(u -> {
			u.setPassword(passwordEncoder.encode(u.getLogin()));
			userRepo.save(u);
		});
	}

}
