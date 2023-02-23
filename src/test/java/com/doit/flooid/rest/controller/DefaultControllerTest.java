package com.doit.flooid.rest.controller;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.doit.flooid.rest.data.ResponseData;

class DefaultControllerTest {

	private DefaultController  controller = new DefaultController();
	
	@Test
	void testDefaultController() {
		assertThat(controller).isNotNull();
	}

	@Test
	void testDefaultControllerHome() throws Exception {
		ResponseEntity<ResponseData> home = controller.home().call();
		assertThat(home).isNotNull();
		assertThat(home.getStatusCode()).isNotNull().isEqualTo(HttpStatus.OK);
	}
	
	@Test
	void testDefaultControllerDelay() throws Exception {
		ResponseEntity<ResponseData> home = controller.delayResponse().call();
		assertThat(home).isNotNull();
		assertThat(home.getStatusCode()).isNotNull().isEqualTo(HttpStatus.OK);
	}
}
