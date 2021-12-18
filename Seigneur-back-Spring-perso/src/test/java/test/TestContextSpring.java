package test;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import config.AppConfig;

public class TestContextSpring {

	@Test
	public void testContextSpring() {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
		assertNotNull(ctx);
	}

}
