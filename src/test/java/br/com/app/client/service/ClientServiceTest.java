package br.com.app.client.service;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.app.client.entity.ClientEntity;
import br.com.app.client.enuns.StatusEnum;
import br.com.app.client.exception.ClientAlreadyExistsException;
import br.com.app.client.exception.ClientNotFoundException;
import br.com.app.client.model.ClientRequestModel;
import br.com.app.client.model.ClientResponseModel;
import br.com.app.client.repository.ClientRepository;
import br.com.app.client.testdata.ClientEntityTestData;
import br.com.app.client.testdata.ClientRequestModelTestData;

@SpringBootTest
class ClientServiceTest {
	
	@Mock
	ClientRepository clientRepository;
	
	@InjectMocks
	ClientService clientService;
	
	@Test
	void findAll() {
		when(clientRepository.findAll()).thenReturn(List.of(ClientEntityTestData.getClientEntity()));
		
		List<ClientResponseModel> actual = clientService.findAll();
		
		verify(clientRepository).findAll();
		
		assertFalse(actual.isEmpty());
	}
	
	@Test
	void findAll_returnEmpty() {
		when(clientRepository.findAll()).thenReturn(new ArrayList<>());
		
		List<ClientResponseModel> actual = clientService.findAll();
		
		verify(clientRepository).findAll();
		
		assertTrue(actual.isEmpty());
	}

	@Test
	void searchById() {
		Long id = 1234L;
		when(clientRepository.findById(id)).thenReturn(Optional.of(ClientEntityTestData.getClientEntity()));
		
		ClientResponseModel actual = clientService.searchById(id);
		
		verify(clientRepository).findById(id);

		assertNotNull(actual);
	}
	
	@Test
	void searchById_throwClientNotFoundException() {
		Long id = 9999L;
		when(clientRepository.findById(id)).thenReturn(Optional.empty());
		
		assertThrows(ClientNotFoundException.class, () -> clientService.searchById(id)); 
		
		verify(clientRepository).findById(id);
	}

	@Test
	void insert() {
		ClientEntity mockEntity = ClientEntityTestData.getClientEntity();
		mockEntity.setId(null);
		mockEntity.setDateCreated(null);
		mockEntity.setDateUpdated(null);
		
		ClientEntity expected = ClientEntityTestData.getClientEntity();
		when(clientRepository.existsByDocument(mockEntity.getDocument())).thenReturn(false);
		when(clientRepository.save(mockEntity)).thenReturn(expected);
		
		ClientRequestModel request = ClientRequestModelTestData.getClientRequestModel();
		
		ClientResponseModel actual = clientService.insert(request);

		verify(clientRepository).existsByDocument(mockEntity.getDocument());
		verify(clientRepository).save(mockEntity);
		
		assertNotNull(actual);
	}
	
	@Test
	void insert_throwClientAlreadyExistsException() {
		ClientEntity mockEntity = ClientEntityTestData.getClientEntity();
		mockEntity.setId(null);
		mockEntity.setDateCreated(null);
		mockEntity.setDateUpdated(null);
		
		ClientEntity expected = ClientEntityTestData.getClientEntity();
		when(clientRepository.existsByDocument(mockEntity.getDocument())).thenReturn(true);
		when(clientRepository.save(mockEntity)).thenReturn(expected);
		
		ClientRequestModel request = ClientRequestModelTestData.getClientRequestModel();
		
		assertThrows(ClientAlreadyExistsException.class, () -> clientService.insert(request));
		
		verify(clientRepository).existsByDocument(mockEntity.getDocument());
		verify(clientRepository, times(0)).save(mockEntity);
		
	}

	@Test
	void update() {
		Long id = 1234L;
		
		ClientEntity expected = ClientEntityTestData.getClientEntity();
		expected.setName("Name 2");
		expected.setStatus(StatusEnum.CANCELADO);
		
		ClientRequestModel request = ClientRequestModelTestData.getClientRequestModel();
		request.setName("Name 2");
		request.setStatus(StatusEnum.CANCELADO);
		

		when(clientRepository.findById(id)).thenReturn(Optional.of(ClientEntityTestData.getClientEntity()));
		when(clientRepository.existsByDocument(request.getDocument())).thenReturn(false);
		when(clientRepository.save(expected)).thenReturn(expected);

		
		ClientResponseModel actual = clientService.update(id, request);

		verify(clientRepository).findById(id);
		verify(clientRepository).existsByDocument(request.getDocument());
		verify(clientRepository).save(expected);
		
		assertNotNull(actual);
	}
	
	@Test
	void update_throwClientAlreadyExistsException() {
		Long id = 1234L;
		
		ClientEntity expected = ClientEntityTestData.getClientEntity();
		expected.setName("Name 2");
		expected.setStatus(StatusEnum.CANCELADO);
		
		ClientRequestModel request = ClientRequestModelTestData.getClientRequestModel();
		request.setName("Name 2");
		request.setStatus(StatusEnum.CANCELADO);
		

		when(clientRepository.findById(id)).thenReturn(Optional.of(ClientEntityTestData.getClientEntity()));
		when(clientRepository.existsByDocument(request.getDocument())).thenReturn(true);
		when(clientRepository.save(expected)).thenReturn(expected);

		assertThrows(ClientAlreadyExistsException.class, () -> clientService.update(id, request));

		verify(clientRepository).findById(id);
		verify(clientRepository).existsByDocument(request.getDocument());
		verify(clientRepository, times(0)).save(expected);
		
	}

	@Test
	void removeById() {
		Long id = 1234L;

		when(clientRepository.findById(id)).thenReturn(Optional.of(ClientEntityTestData.getClientEntity()));
		doNothing().when(clientRepository).deleteById(id);
		
		clientService.removeById(id);
		
		verify(clientRepository).findById(id);
		verify(clientRepository).deleteById(id);
		
	}

}
