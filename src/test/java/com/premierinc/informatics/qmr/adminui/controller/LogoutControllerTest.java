package com.premierinc.informatics.qmr.adminui.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.premierinc.informatics.qmr.adminui.controller.LogoutController;
import com.premierinc.informatics.qmr.adminui.domain.ae.entity.UserInfo;
import com.premierinc.informatics.qmr.adminui.domain.ae.entity.UserInfoId;
import com.premierinc.informatics.qmr.adminui.util.Constants;
import com.premierinc.informatics.qmr.adminui.util.TestConstants;

@RunWith(MockitoJUnitRunner.class)
public class LogoutControllerTest {

	@InjectMocks
	private LogoutController logoutController;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		// Process mock annotations
		MockitoAnnotations.initMocks(this);

		// Setup Spring test in standalone mode
		this.mockMvc = MockMvcBuilders.standaloneSetup(logoutController).build();
	}

	@Test
	public void testLogout() throws Exception {
		
		UserInfoId id = new UserInfoId(TestConstants.TEST_USER);
		UserInfo user = new UserInfo(id);
		
		MockHttpSession session = new MockHttpSession();
		session.setAttribute(Constants.USER, user);
		MockHttpServletRequestBuilder createMessage = get("/logout").session(session);

		mockMvc.perform(createMessage)
			.andExpect(status().is3xxRedirection())
			.andExpect(redirectedUrl("https://www.premierinc.com"));
	}

}