package com.premierinc.informatics.qmr.adminui.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.premierinc.informatics.qmr.adminui.controller.NpiFacilityTranmissionController;
import com.premierinc.informatics.qmr.adminui.domain.ae.entity.NpiFacilityTransmission;
import com.premierinc.informatics.qmr.adminui.domain.ae.entity.NpiFacilityTransmissionId;
import com.premierinc.informatics.qmr.adminui.service.NpiFacilityTransmissionService;
import com.premierinc.informatics.qmr.adminui.util.TestConstants;

@RunWith(MockitoJUnitRunner.class)
public class NpiFacilityTransmissionControllerTest {

	private static final Date JAN_01_2014;
	private static final Date DEC_31_2014;

	static {
		JAN_01_2014 = new GregorianCalendar(2014, 0, 1).getTime();
		DEC_31_2014 = new GregorianCalendar(2014, 11, 31).getTime();
	}

	@Mock
	private NpiFacilityTransmissionService npiFacilityTransmissionService;

	@InjectMocks
	private NpiFacilityTranmissionController npiFacilityTranmissionController;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		// Process mock annotations
		MockitoAnnotations.initMocks(this);

		// Setup Spring test in standalone mode
		this.mockMvc = MockMvcBuilders.standaloneSetup(npiFacilityTranmissionController).build();
	}

	@Test
	public void testGetNpiFacilityTransmissionRecords() throws Exception {

		NpiFacilityTransmissionId id = new NpiFacilityTransmissionId("TestClient", "TestFacility", 1L, 1L, 1L, "test", "test", "test", JAN_01_2014);
		NpiFacilityTransmission npi = new NpiFacilityTransmission(id, DEC_31_2014, 1);

		List<NpiFacilityTransmission> content = new ArrayList<NpiFacilityTransmission>(Arrays.asList(npi));
		Page<NpiFacilityTransmission> page = new PageImpl<NpiFacilityTransmission>(content, null, 1L);

		when(npiFacilityTransmissionService.findAll(Mockito.any(Pageable.class))).thenReturn(page);

		MockHttpServletRequestBuilder createMessage = post("/npi-facility-transmission/records")
				.param("_search", "false")
				.param("page", "1")
				.param("rows", "25")
				.param("sidx", "id")
				.param("sord", "asc");

		mockMvc.perform(createMessage)
			.andExpect(status().is2xxSuccessful())
			.andExpect(content().contentType(TestConstants.APPLICATION_JSON))
			.andExpect(jsonPath("$.page", equalTo("1")))
			.andExpect(jsonPath("$.total", equalTo("1")))
			.andExpect(jsonPath("$.records", equalTo("1")))
			.andExpect(jsonPath("$.rows", hasSize(1)))
			.andExpect(jsonPath("$.rows[0].id", equalTo("TestClient~~TestFacility~~1~~1~~1~~test~~test~~test~~2014-01-01")))
			.andExpect(jsonPath("$.rows[0].clientId", equalTo("TestClient")))
			.andExpect(jsonPath("$.rows[0].facilityId", equalTo("TestFacility")))
			.andExpect(jsonPath("$.rows[0].initiativeId", equalTo(1)))
			.andExpect(jsonPath("$.rows[0].measureSetId", equalTo(1)))
			.andExpect(jsonPath("$.rows[0].msMeasureId", equalTo(1)))
			.andExpect(jsonPath("$.rows[0].entityType", equalTo("test")))
			.andExpect(jsonPath("$.rows[0].entityCode", equalTo("test")))
			.andExpect(jsonPath("$.rows[0].npiNum", equalTo("test")))
			.andExpect(jsonPath("$.rows[0].effDate", equalTo("2014-01-01")))
			.andExpect(jsonPath("$.rows[0].expDate", equalTo("2014-12-31")))
			.andExpect(jsonPath("$.rows[0].transmitInd", equalTo(1)));

		Mockito.verify(npiFacilityTransmissionService, Mockito.times(1)).findAll(Mockito.any(Pageable.class));
		Mockito.verifyNoMoreInteractions(npiFacilityTransmissionService);
	}

	@Test
	public void testCreateNpiFacilityTransmissionRecord() throws Exception {

		NpiFacilityTransmissionId id = new NpiFacilityTransmissionId("TestClient", "TestFacility", 1L, 1L, 1L, "test", "test", "test", JAN_01_2014);
		NpiFacilityTransmission npi = new NpiFacilityTransmission(id, DEC_31_2014, 1);

		when(npiFacilityTransmissionService.save(Mockito.any(NpiFacilityTransmission.class))).thenReturn(npi);

		MockHttpServletRequestBuilder createMessage = post("/npi-facility-transmission/create")
				.param("id", "1")
				.param("clientId", "TestClient")
				.param("facilityId", "TestFacility")
				.param("initiativeId", "1")
				.param("measureSetId", "1")
				.param("msMeasureId", "1")
				.param("entityType", "test")
				.param("entityCode", "12345")
				.param("npiNum", "54321")
				.param("effDate", "01/01/2014")
				.param("expDate", "12/31/2014")
				.param("transmitInd", "1");

		mockMvc.perform(createMessage)
			.andExpect(status().is2xxSuccessful())
			.andExpect(content().contentType(TestConstants.APPLICATION_JSON))
			.andExpect(jsonPath("$.success", equalTo(true)));

		Mockito.verify(npiFacilityTransmissionService, Mockito.times(1)).save(Mockito.any(NpiFacilityTransmission.class));
		Mockito.verifyNoMoreInteractions(npiFacilityTransmissionService);
	}

	@Test
	public void testUpdateNpiFacilityTransmissionRecordFailure() throws Exception {

		NpiFacilityTransmissionId id = new NpiFacilityTransmissionId("TestClient", "TestFacility", 1L, 1L, 1L, "test", "test", "test", JAN_01_2014);
		NpiFacilityTransmission npi = new NpiFacilityTransmission(id, DEC_31_2014, 1);

		when(npiFacilityTransmissionService.save(Mockito.any(NpiFacilityTransmission.class))).thenReturn(npi);

		MockHttpServletRequestBuilder createMessage = post("/npi-facility-transmission/update")
				.param("id", "1")
				.param("clientId", "TestClient")
				.param("facilityId", "TestFacility")
				.param("initiativeId", "1")
				.param("measureSetId", "1")
				.param("msMeasureId", "1")
				.param("entityType", "test")
				.param("entityCode", "12345")
				.param("npiNum", "54321")
				.param("effDate", "01/01/2014")
				.param("expDate", "12/31/2014")
				.param("transmitInd", "1");

		mockMvc.perform(createMessage)
			.andExpect(status().is2xxSuccessful())
			.andExpect(content().contentType(TestConstants.APPLICATION_JSON))
			.andExpect(jsonPath("$.success", equalTo(true)))
			.andExpect(jsonPath("$.message", hasSize(0)));

		Mockito.verify(npiFacilityTransmissionService, Mockito.times(1)).save(Mockito.any(NpiFacilityTransmission.class));
		Mockito.verifyNoMoreInteractions(npiFacilityTransmissionService);
	}

	@Test
	public void testDeleteNpiFacilityTransmissionRecordFailure() throws Exception {

		MockHttpServletRequestBuilder createMessage = post("/npi-facility-transmission/delete")
				.param("id", "TESTCLIENT~~TESTFACILITY~~1~~1~~1~~TEST~~19345~~54321~~2014-01-01");

		mockMvc.perform(createMessage)
			.andExpect(status().is2xxSuccessful())
			.andExpect(content().contentType(TestConstants.APPLICATION_JSON))
			.andExpect(jsonPath("$.success", equalTo(true)))
			.andExpect(jsonPath("$.message", hasSize(0)));

		Mockito.verify(npiFacilityTransmissionService, Mockito.times(1)).delete(Mockito.any(NpiFacilityTransmissionId.class));
		Mockito.verifyNoMoreInteractions(npiFacilityTransmissionService);
	}

}
