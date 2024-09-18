package br.com.app.client.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.NullValueMappingStrategy;
import org.mapstruct.factory.Mappers;

import br.com.app.client.entity.ClientEntity;
import br.com.app.client.model.ClientRequestModel;
import br.com.app.client.model.ClientResponseModel;

@Mapper(componentModel = "spring", nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL)
public interface ClientMapper {

	ClientMapper INSTANCE = Mappers.getMapper(ClientMapper.class);

	ClientEntity toEntity(ClientRequestModel clientRequestModel);
	
	ClientResponseModel toResponseModel(ClientEntity clientEntity);

	List<ClientResponseModel> toListResponseModel(List<ClientEntity> clientEntities);
	
}

