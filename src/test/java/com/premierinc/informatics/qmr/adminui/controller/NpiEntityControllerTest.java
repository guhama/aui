package com.premierinc.informatics.qmr.adminui.controller;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
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

import com.premierinc.informatics.qmr.adminui.domain.ae.entity.NpiEntity;
import com.premierinc.informatics.qmr.adminui.domain.ae.entity.NpiEntityId;
import com.premierinc.informatics.qmr.adminui.domain.ae.entity.NpiFacilityTransmission;
import com.premierinc.informatics.qmr.adminui.domain.ae.entity.NpiFacilityTransmissionId;
import com.premierinc.informatics.qmr.adminui.service.NpiEntityService;
import com.premierinc.informatics.qmr.adminui.util.TestConstants;

@RunWith(MockitoJUnitRunner.class)
public class NpiEntityControllerTest {

	private static final Date JAN_01_2014;
	private static final Date DEC_31_2014;

	static {
		JAN_01_2014 = new GregorianCalendar(2014, 0, 1).getTime();
		DEC_31_2014 = new GregorianCalendar(2014, 11, 31).getTime();
	}

	@Mock
	private NpiEntityService npiEntityService;

	@InjectMocks
	private NpiEntityController npiEntityController;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		// Process mock annotations
		MockitoAnnotations.initMocks(this);

		// Setup Spring test in standalone mode
		this.mockMvc = MockMvcBuilders.standaloneSetup(npiEntityController).build();
	}

	
	@Test
	public void testGetNpiEntityRecords() throws Exception {

		 NpiEntityId id =
				  new NpiEntityId(TestConstants.TEST_ENTITY_TYPE_TIN,TestConstants.TEST_ENTITY_CODE,
				            TestConstants.TEST_NPI, JAN_01_2014);
		  NpiEntity npi = new NpiEntity(id, DEC_31_2014);

		List<NpiEntity> content = new ArrayList<NpiEntity>(Arrays.asList(npi));
		Page<NpiEntity> page = new PageImpl<NpiEntity>(content, null, 1L);

		when(npiEntityService.findAll(Mockito.any(Pageable.class))).thenReturn(page);

		MockHttpServletRequestBuilder createMessage = post("/npi-entity/records")
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
			.andExpect(jsonPath("$.rows[0].id", equalTo("TIN~~TestEntityCode~~TestNPI~~2014-01-01")))
			.andExpect(jsonPath("$.rows[0].entityType", equalTo(TestConstants.TEST_ENTITY_TYPE_TIN)))
			.andExpect(jsonPath("$.rows[0].entityCode", equalTo(TestConstants.TEST_ENTITY_CODE)))
			.andExpect(jsonPath("$.rows[0].npiNum", equalTo(TestConstants.TEST_NPI)))
			.andExpect(jsonPath("$.rows[0].effDate", equalTo("2014-01-01")))
			.andExpect(jsonPath("$.rows[0].expDate", equalTo("2014-12-31")));
	

		Mockito.verify(npiEntityService, Mockito.times(1)).findAll(Mockito.any(Pageable.class));
		Mockito.verifyNoMoreInteractions(npiEntityService);
	}

	@Test
	public void testCreateNpiEntityRecord() throws Exception {

		NpiEntityId id =
				  new NpiEntityId(TestConstants.TEST_ENTITY_TYPE_TIN,TestConstants.TEST_ENTITY_CODE,
				            TestConstants.TEST_NPI, JAN_01_2014);
		  NpiEntity npi = new NpiEntity(id, DEC_31_2014);

		when(npiEntityService.save(Mockito.any(NpiEntity.class))).thenReturn(npi);

		MockHttpServletRequestBuilder createMessage = post("/npi-entity/create")
				.param("id", "1")
				.param("entityType", TestConstants.TEST_ENTITY_TYPE_TIN)
				.param("entityCode", TestConstants.TEST_ENTITY_CODE)
				.param("npiNum", TestConstants.TEST_NPI)
				.param("effDate", "01/01/2014")
				.param("expDate", "12/31/2014");
				

		mockMvc.perform(createMessage)
			.andExpect(status().is2xxSuccessful())
			.andExpect(content().contentType(TestConstants.APPLICATION_JSON))
			.andExpect(jsonPath("$.success", equalTo(true)));

		Mockito.verify(npiEntityService, Mockito.times(1)).save(Mockito.any(NpiEntity.class));
		Mockito.verifyNoMoreInteractions(npiEntityService);
	}

	@Test
	public void testUpdateNpiEntityRecordFailure() throws Exception {

		NpiEntityId id =
				  new NpiEntityId(TestConstants.TEST_ENTITY_TYPE_TIN,TestConstants.TEST_ENTITY_CODE,
				            TestConstants.TEST_NPI, JAN_01_2014);
		  NpiEntity npi = new NpiEntity(id, DEC_31_2014);

		when(npiEntityService.save(Mockito.any(NpiEntity.class))).thenReturn(npi);

		MockHttpServletRequestBuilder createMessage = post("/npi-entity/update")
				.param("id", "1")
				.param("entityType", TestConstants.TEST_ENTITY_TYPE_TIN)
				.param("entityCode", TestConstants.TEST_ENTITY_CODE)
				.param("npiNum", TestConstants.TEST_NPI)
				.param("effDate", "01/01/2014")
				.param("expDate", "12/31/2014");

		mockMvc.perform(createMessage)
			.andExpect(status().is2xxSuccessful())
			.andExpect(content().contentType(TestConstants.APPLICATION_JSON))
			.andExpect(jsonPath("$.success", equalTo(true)))
			.andExpect(jsonPath("$.message", hasSize(0)));

		Mockito.verify(npiEntityService, Mockito.times(1)).save(Mockito.any(NpiEntity.class));
		Mockito.verifyNoMoreInteractions(npiEntityService);
	}

	@Test
	public void testDeleteNpiEntityRecordFailure() throws Exception {

		MockHttpServletRequestBuilder createMessage = post("/npi-entity/delete")
				.param("id", "TIN~~TestEntityCode~~TestNPI~~2014-01-01");

		mockMvc.perform(createMessage)
			.andExpect(status().is2xxSuccessful())
			.andExpect(content().contentType(TestConstants.APPLICATION_JSON))
			.andExpect(jsonPath("$.success", equalTo(true)))
			.andExpect(jsonPath("$.message", hasSize(0)));

		Mockito.verify(npiEntityService, Mockito.times(1)).delete(Mockito.any(NpiEntityId.class));
		Mockito.verifyNoMoreInteractions(npiEntityService);
	}

	
	

}
