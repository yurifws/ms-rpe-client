package br.com.app.client.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueMappingStrategy;
import org.mapstruct.factory.Mappers;

import br.com.app.client.entity.ClientEntity;
import br.com.app.client.model.CardResponseModel;
import br.com.app.client.model.ClientRequestModel;
import br.com.app.client.model.ClientResponseModel;
import br.com.app.client.model.FullClientResponseModel;

@Mapper(componentModel = "spring", nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL)
public interface ClientMapper {

	ClientMapper INSTANCE = Mappers.getMapper(ClientMapper.class);

	ClientEntity toEntity(ClientRequestModel clientRequestModel);

	ClientResponseModel toResponseModel(ClientEntity clientEntity);

	List<ClientResponseModel> toListResponseModel(List<ClientEntity> clientEntities);

	@Mapping(source = "clientResponseModel.id", target = "id")
	@Mapping(source = "clientResponseModel.name", target = "name")
	@Mapping(source = "clientResponseModel.document", target = "document")
	@Mapping(source = "clientResponseModel.status", target = "status")
	@Mapping(source = "clientResponseModel.dateOfBirth", target = "dateOfBirth")
	@Mapping(source = "clientResponseModel.dateCreated", target = "dateCreated")
	@Mapping(source = "clientResponseModel.dateUpdated", target = "dateUpdated")
	@Mapping(source = "cardResponseModel", target = "card")
	FullClientResponseModel toResponseModel(ClientResponseModel clientResponseModel, CardResponseModel cardResponseModel);

}

