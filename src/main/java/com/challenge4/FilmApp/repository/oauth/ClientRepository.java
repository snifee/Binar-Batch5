package com.challenge4.FilmApp.repository.oauth;


import com.challenge4.FilmApp.entity.oauth.Client;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ClientRepository extends PagingAndSortingRepository<Client, Long> {

    Client findOneByClientId(String clientId);

}
