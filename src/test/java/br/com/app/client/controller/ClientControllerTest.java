package br.com.app.client.controller;

import static br.com.app.client.constants.RestConstants.PATH_CLIENTS;
import static br.com.app.client.constants.RestConstants.PATH_VARIABLE_ID;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import br.com.app.client.enuns.StatusEnum;
import br.com.app.client.model.ClientRequestModel;
import br.com.app.client.model.ClientResponseModel;
import br.com.app.client.service.ClientService;
import br.com.app.client.testdata.ClientRequestModelTestData;
import br.com.app.client.testdata.ClientResponseModelTestData;

@WebMvcTest(ClientController.class)
class ClientControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private ClientService clientService;
	
	@Test
	void findAll() throws Exception {
		List<ClientResponseModel> expected = List.of(ClientResponseModelTestData.getClientResponseModel());
		
		when(clientService.findAll()).thenReturn(expected);
		
		mockMvc.perform(MockMvcRequestBuilders.get(PATH_CLIENTS)
				.contentType(MediaType.APPLICATION_JSON_VALUE))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(jsonPath("$[0].id").value(expected.get(0).getId()))
		.andExpect(jsonPath("$[0].name").value(expected.get(0).getName()))
		.andExpect(jsonPath("$[0].document").value(expected.get(0).getDocument()))
		.andExpect(jsonPath("$[0].dateOfBirth").value(expected.get(0).getDateOfBirth().toString()))
		.andExpect(jsonPath("$[0].status").value(expected.get(0).getStatus().name()))
		.andExpect(jsonPath("$[0].dateCreated").value(expected.get(0).getDateCreated().toString()))
		.andExpect(jsonPath("$[0].dateUpdated").value(expected.get(0).getDateUpdated().toString()));

		verify(clientService).findAll();
	}
	
	@Test
	void searchById() throws Exception {

		Long id = 1234L;
		ClientResponseModel expected = ClientResponseModelTestData.getClientResponseModel();
		
		when(clientService.searchById(id)).thenReturn(expected);
		
		mockMvc.perform(MockMvcRequestBuilders.get(PATH_CLIENTS + PATH_VARIABLE_ID, id)
				.contentType(MediaType.APPLICATION_JSON_VALUE))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.id").value(expected.getId()))
		.andExpect(jsonPath("$.name").value(expected.getName()))
		.andExpect(jsonPath("$.document").value(expected.getDocument()))
		.andExpect(jsonPath("$.dateOfBirth").value(expected.getDateOfBirth().toString()))
		.andExpect(jsonPath("$.status").value(expected.getStatus().name()))
		.andExpect(jsonPath("$.dateCreated").value(expected.getDateCreated().toString()))
		.andExpect(jsonPath("$.dateUpdated").value(expected.getDateUpdated().toString()));
		
		verify(clientService).searchById(id);
	}
	
	@Test
	void insert() throws Exception {

		ClientRequestModel request = ClientRequestModelTestData.getClientRequestModel();
		ClientResponseModel expected = ClientResponseModelTestData.getClientResponseModel();
		when(clientService.insert(request)).thenReturn(expected);
		
		String body = """
				{
					"name": "Name 1",
					"status": "ATIVO",
					"document": "001023145610",
					"dateOfBirth": "19941021"
				}
				""";
		
		mockMvc.perform(MockMvcRequestBuilders.post(PATH_CLIENTS)
				.content(body)
				.accept(MediaType.APPLICATION_JSON_VALUE)
				.contentType(MediaType.APPLICATION_JSON_VALUE))
		.andDo(print())
		.andExpect(status().isCreated())
		.andExpect(jsonPath("$.id").value(expected.getId()))
		.andExpect(jsonPath("$.name").value(expected.getName()))
		.andExpect(jsonPath("$.document").value(expected.getDocument()))
		.andExpect(jsonPath("$.dateOfBirth").value(expected.getDateOfBirth().toString()))
		.andExpect(jsonPath("$.status").value(expected.getStatus().name()))
		.andExpect(jsonPath("$.dateCreated").value(expected.getDateCreated().toString()))
		.andExpect(jsonPath("$.dateUpdated").value(expected.getDateUpdated().toString()));
		
		verify(clientService).insert(request);
	}
	
	@Test
	void update() throws Exception {
		Long id = 1234L;
		ClientRequestModel request = ClientRequestModelTestData.getClientRequestModel();
		request.setName("Name 2");
		request.setStatus(StatusEnum.CANCELADO);
		request.setDocument("00212312344");
		request.setDateOfBirth(LocalDate.of(1995, 9, 25));
		
		ClientResponseModel expected = ClientResponseModelTestData.getClientResponseModel();
		expected.setName("Name 2");
		expected.setStatus(StatusEnum.CANCELADO);
		expected.setDocument("00212312344");
		expected.setDateOfBirth(LocalDate.of(1995, 9, 25));
		
		when(clientService.update(id, request)).thenReturn(expected);
		
		String body = """
				{
					"name": "Name 2",
					"status": "CANCELADO",
					"document": "00212312344",
					"dateOfBirth": "19950925"
				}
				""";
		
		mockMvc.perform(MockMvcRequestBuilders.put(PATH_CLIENTS + PATH_VARIABLE_ID, id)
				.content(body)
				.accept(MediaType.APPLICATION_JSON_VALUE)
				.contentType(MediaType.APPLICATION_JSON_VALUE))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.id").value(expected.getId()))
		.andExpect(jsonPath("$.name").value(expected.getName()))
		.andExpect(jsonPath("$.document").value(expected.getDocument()))
		.andExpect(jsonPath("$.dateOfBirth").value(expected.getDateOfBirth().toString()))
		.andExpect(jsonPath("$.status").value(expected.getStatus().name()))
		.andExpect(jsonPath("$.dateCreated").value(expected.getDateCreated().toString()))
		.andExpect(jsonPath("$.dateUpdated").value(expected.getDateUpdated().toString()));
		
		verify(clientService).update(id, request);
	}
	
	@Test
	void removeById() throws Exception {

		Long id = 1234L;
		doNothing().when(clientService).removeById(id);
		
		mockMvc.perform(MockMvcRequestBuilders.delete(PATH_CLIENTS + PATH_VARIABLE_ID, id)
				.contentType(MediaType.APPLICATION_JSON_VALUE))
		.andDo(print())
		.andExpect(status().isNoContent());
		
		verify(clientService).removeById(id);
	}
	
}
