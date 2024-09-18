package br.com.app.client.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.app.client.entity.ClientEntity;
import br.com.app.client.exception.ClientNotFoundException;
import br.com.app.client.mapper.ClientMapper;
import br.com.app.client.model.ClientRequestModel;
import br.com.app.client.model.ClientResponseModel;
import br.com.app.client.repository.ClientRepository;
import lombok.RequiredArgsConstructor;

public class ClientService {

}
