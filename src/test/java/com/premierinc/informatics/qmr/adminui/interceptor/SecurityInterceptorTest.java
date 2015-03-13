package com.premierinc.informatics.qmr.adminui.interceptor;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;

import com.premierinc.informatics.qmr.adminui.domain.ae.entity.UserInfo;
import com.premierinc.informatics.qmr.adminui.domain.ae.entity.UserInfoId;
import com.premierinc.informatics.qmr.adminui.domain.ae.repository.UserInfoRepo;
import com.premierinc.informatics.qmr.adminui.util.Constants;
import com.premierinc.informatics.qmr.adminui.util.TestConstants;

@RunWith(MockitoJUnitRunner.class)
public class SecurityInterceptorTest {
  
  @Mock
  private UserInfoRepo userInfoRepo;

  @InjectMocks
  private SecurityInterceptor securityInterceptor;

  @Before
  public void setup() {
      // Process mock annotations
      MockitoAnnotations.initMocks(this);
  }

  @Test
  public void testCreateSessionSuccess() throws Exception {
    MockHttpServletRequest request = new MockHttpServletRequest();
    request.addParameter(Constants.LMS_ID, "12345");
    
    MockHttpServletResponse response = new MockHttpServletResponse();
    
    UserInfoId id = new UserInfoId(TestConstants.TEST_USER);
    UserInfo user = new UserInfo(id);
    
    when(userInfoRepo.findByLmsId(Mockito.any(Long.class))).thenReturn(user);
    
    Boolean result = securityInterceptor.preHandle(request, response, null);
    
    assertTrue(result);
    
    Mockito.verify(userInfoRepo, Mockito.times(1)).findByLmsId(Mockito.any(Long.class));
    Mockito.verifyNoMoreInteractions(userInfoRepo);
  }
  
  @Test
  public void testSessionCreated() throws Exception {
    UserInfoId id = new UserInfoId(TestConstants.TEST_USER);
    UserInfo user = new UserInfo(id);
    
    MockHttpSession session = new MockHttpSession();
    session.setAttribute(Constants.USER, user);
    
    MockHttpServletRequest request = new MockHttpServletRequest();
    request.setSession(session);
    
    MockHttpServletResponse response = new MockHttpServletResponse();
    
    Boolean result = securityInterceptor.preHandle(request, response, null);
    
    assertTrue(result);
    Mockito.verifyZeroInteractions(userInfoRepo);
  }

  @Test
  public void testNoSessionNoLmsidFailure() throws Exception {
    MockHttpServletRequest request = new MockHttpServletRequest();
    
    MockHttpServletResponse response = new MockHttpServletResponse();
    
    UserInfoId id = new UserInfoId(TestConstants.TEST_USER);
    UserInfo user = new UserInfo(id);
    
    Boolean result = securityInterceptor.preHandle(request, response, null);
    
    assertFalse(result);
    Mockito.verifyZeroInteractions(userInfoRepo);
  }

  @Test
  public void testCreateSessionNoUserInfoFailure() throws Exception {
    MockHttpServletRequest request = new MockHttpServletRequest();
    request.addParameter(Constants.LMS_ID, "12345");
    
    MockHttpServletResponse response = new MockHttpServletResponse();
    
    UserInfoId id = new UserInfoId(TestConstants.TEST_USER);
    UserInfo user = new UserInfo(id);
    
    when(userInfoRepo.findByLmsId(Mockito.any(Long.class))).thenReturn(null);
    
    Boolean result = securityInterceptor.preHandle(request, response, null);
    
    assertFalse(result);
    
    Mockito.verify(userInfoRepo, Mockito.times(1)).findByLmsId(Mockito.any(Long.class));
    Mockito.verifyNoMoreInteractions(userInfoRepo);
  }

  @Test
  public void testBadLmsidFailure() throws Exception {
    MockHttpServletRequest request = new MockHttpServletRequest();
    request.addParameter(Constants.LMS_ID, "Invalid");
    
    MockHttpServletResponse response = new MockHttpServletResponse();
    
    Boolean result = securityInterceptor.preHandle(request, response, null);
    
    assertFalse(result);
    
    Mockito.verifyZeroInteractions(userInfoRepo);
  }

}
