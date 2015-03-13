package com.premierinc.informatics.qmr.adminui.controller;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.nullValue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
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

import com.premierinc.informatics.qmr.adminui.controller.JobExecutionController;
import com.premierinc.informatics.qmr.adminui.domain.ae.entity.JobExecution;
import com.premierinc.informatics.qmr.adminui.domain.ae.entity.JobExecutionId;
import com.premierinc.informatics.qmr.adminui.service.JobExecutionService;
import com.premierinc.informatics.qmr.adminui.util.TestConstants;

@RunWith(MockitoJUnitRunner.class)
public class JobExecutionControllerTest {

	private static final Timestamp JAN_01_2014_0000_HOURS;
	private static final Timestamp JAN_01_2014_0001_HOURS;

	static {
		JAN_01_2014_0000_HOURS = new Timestamp(new GregorianCalendar(2014, 0, 1, 0, 0).getTimeInMillis());
		JAN_01_2014_0001_HOURS = new Timestamp(new GregorianCalendar(2014, 0, 1, 0, 1).getTimeInMillis());
	}

	@Mock
	private JobExecutionService jobExecutionService;

	@InjectMocks
	private JobExecutionController jobExecutionController;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		// Process mock annotations
		MockitoAnnotations.initMocks(this);

		// Setup Spring test in standalone mode
		this.mockMvc = MockMvcBuilders.standaloneSetup(jobExecutionController).build();
	}

	@Test
	public void testGetNpiFacilityTransmissionRecords() throws Exception {
		
		JobExecutionId id = new JobExecutionId(1L);
		JobExecution job = new JobExecution(id, JAN_01_2014_0000_HOURS, JAN_01_2014_0001_HOURS, "COMPLETED", "COMPLETED", null);

		List<JobExecution> content = new ArrayList<JobExecution>(Arrays.asList(job));
		Page<JobExecution> page = new PageImpl<JobExecution>(content, null, 1L);

		when(jobExecutionService.findAll(Mockito.any(Pageable.class))).thenReturn(page);

		MockHttpServletRequestBuilder createMessage = post("/job-execution/records")
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
			.andExpect(jsonPath("$.rows[0].jobExecutionId", equalTo(1)))
			.andExpect(jsonPath("$.rows[0].startTime", equalTo("2014-01-01 00:00")))
			.andExpect(jsonPath("$.rows[0].endTime", equalTo("2014-01-01 00:01")))
			.andExpect(jsonPath("$.rows[0].status", equalTo("COMPLETED")))
			.andExpect(jsonPath("$.rows[0].exitCode", equalTo("COMPLETED")))
			.andExpect(jsonPath("$.rows[0].exitMessage", nullValue()));

		Mockito.verify(jobExecutionService, Mockito.times(1)).findAll(Mockito.any(Pageable.class));
		Mockito.verifyNoMoreInteractions(jobExecutionService);
	}

}
