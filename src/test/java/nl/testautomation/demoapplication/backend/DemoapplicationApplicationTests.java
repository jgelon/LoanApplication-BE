package nl.testautomation.demoapplication.backend;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@SpringBootTest(properties = "foo=bar")
class DemoapplicationApplicationTests {

	@Value("${foo}")
	String foo;

	@Test
	void test(){
		assertThat(foo, is("bar"));
	}

}
