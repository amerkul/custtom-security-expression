package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import io.restassured.RestAssured;
import io.restassured.authentication.FormAuthConfig;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.springframework.http.MediaType;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class DemoApplicationTests {

	@Test
	public void givenUserWithReadPrivilegeAndHasPermission_whenGetFooById_thenOK() {
		Response response = givenAuth("john", "123").get("http://localhost:8080/fine");
		assertEquals(200, response.getStatusCode());
	}

	@Test
	public void givenUserWithNoWritePrivilegeAndHasPermission_whenPostFoo_thenForbidden() {
		Response response = givenAuth("john", "123").contentType(MediaType.APPLICATION_JSON_VALUE)
													.body("Everything is fine! - returnObject, 'read'")
													.post("http://localhost:8080/read");
		assertEquals(403, response.getStatusCode());
	}

	@Test
	public void givenUserWithWritePrivilegeAndHasPermission_whenPostFoo_thenOk() {
		Response response = givenAuth("tom", "111").contentType(MediaType.APPLICATION_JSON_VALUE)
												   .body("Everything is fine! - #foo, 'write'")
												   .post("http://localhost:8080/write");
		assertEquals(200, response.getStatusCode());
	}

	private RequestSpecification givenAuth(String username, String password) {
		FormAuthConfig formAuthConfig =
				new FormAuthConfig("http://localhost:8080/login", "username", "password");

		return RestAssured.given().auth().form(username, password, formAuthConfig);
	}

}
