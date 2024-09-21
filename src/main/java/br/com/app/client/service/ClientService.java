package br.com.app.client.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.app.client.entity.ClientEntity;
import br.com.app.client.exception.ClientAlreadyExistsException;
import br.com.app.client.exception.ClientNotFoundException;
import br.com.app.client.mapper.ClientMapper;
import br.com.app.client.model.ClientRequestModel;
import br.com.app.client.model.ClientResponseModel;
import br.com.app.client.repository.ClientRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClientService implements IClientService {
	
	private final ClientRepository clientRepository;

	@Override
	public List<ClientResponseModel> findAll() {
		List<ClientEntity> clients = clientRepository.findAll();
		
		if(!clients.isEmpty()) {
			return ClientMapper.INSTANCE.toListResponseModel(clients);
		}
		
		return new ArrayList<>();
	}

	@Override
	public ClientResponseModel searchById(Long id) {
		return ClientMapper.INSTANCE.toResponseModel(findById(id));
	}
	
	@Transactional
	@Override
	public ClientResponseModel insert(ClientRequestModel clientRequestModel) {
		
		validateIfExistsDocument(clientRequestModel.getDocument());
		
		return ClientMapper.INSTANCE.toResponseModel(
				save(ClientMapper.INSTANCE.toEntity(clientRequestModel)));
	}

	@Transactional
	@Override
	public ClientResponseModel update(Long id, ClientRequestModel clientRequestModel) {
		ClientEntity clientEntity = findById(id);
		validateIfExistsDocument(clientRequestModel.getDocument());
		
		BeanUtils.copyProperties(clientRequestModel, clientEntity, "id");
		return ClientMapper.INSTANCE.toResponseModel(save(clientEntity));
	}

	@Transactional
	@Override
	public void removeById(Long id) {
		ClientEntity clientEntity = findById(id);
		clientRepository.deleteById(clientEntity.getId());
	}

	private ClientEntity findById(Long id) {
		return clientRepository.findById(id)
				.orElseThrow(() -> new ClientNotFoundException(id));
	}
	
	private ClientEntity save(ClientEntity clientEntity) {
		return clientRepository.save(clientEntity);
	}
	
	private boolean existsByDocument(String document) {
		return clientRepository.existsByDocument(document);
	}

	private void validateIfExistsDocument(String document) {
		if(existsByDocument(document)) {
			throw new ClientAlreadyExistsException(document);
		}
	}

}
