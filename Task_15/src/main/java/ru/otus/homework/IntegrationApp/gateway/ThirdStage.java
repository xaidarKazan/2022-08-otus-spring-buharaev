package ru.otus.homework.IntegrationApp.gateway;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import ru.otus.homework.IntegrationApp.domain.Caterpillar;
import ru.otus.homework.IntegrationApp.domain.Butterfly;

import java.util.Collection;

@MessagingGateway
public interface ThirdStage {

    @Gateway(requestChannel = "cocoonChannel", replyChannel = "butterflyChannel")
    Collection<Butterfly> process(Collection<Caterpillar> eaggCollection);
}