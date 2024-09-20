package br.com.app.client.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.app.client.model.CardResponseModel;

@FeignClient(name = "cardClient", url = "${feign.client.config.card-service.url}")
public interface CardClient {

    @GetMapping
    List<CardResponseModel> getCardByClientId(@RequestParam("clientId") Long clientId);
}
