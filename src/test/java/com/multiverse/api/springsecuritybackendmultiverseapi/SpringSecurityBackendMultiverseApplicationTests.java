package com.multiverse.api.springsecuritybackendmultiverseapi;

import com.multiverse.api.springsecuritybackendmultiverseapi.auth.AuthenticationResponse;
import com.multiverse.api.springsecuritybackendmultiverseapi.auth.AuthenticationService;
import com.multiverse.api.springsecuritybackendmultiverseapi.user.UserRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

@RunWith(MockitoJUnitRunner.class)
public class SpringSecurityBackendMultiverseApplicationTests {

	@Mock
	AuthenticationService authenticationService;

	@Test
	@DisplayName("Email stored correctly and passed to jwt")
	public void testEmailisStored(){
		UserRequest userRequest = UserRequest.builder()
				.email("test")
				.firstname("test")
				.lastname("test")
				.password("test")
				.build();

		String token = authenticationService.register(userRequest).getToken();
		System.out.println(token);
	}

	@Test
	void contextLoads() {

	}

}
