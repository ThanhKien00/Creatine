package io.creatine;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import static org.assertj.core.api.Assertions.assertThatCode;

@Import(TestcontainersConfiguration.class)
@SpringBootTest
class CreatineApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	public void testApplicationStart() {
		assertThatCode(() -> CreatineApplication.main(new String[]{}))
				.doesNotThrowAnyException();
	}

}
