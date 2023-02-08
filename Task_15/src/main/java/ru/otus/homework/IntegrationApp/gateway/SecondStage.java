package ru.otus.homework.IntegrationApp.gateway;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import ru.otus.homework.IntegrationApp.domain.Caterpillar;
import ru.otus.homework.IntegrationApp.domain.Cocoon;

import java.util.Collection;

@MessagingGateway
public interface SecondStage {

    @Gateway(requestChannel = "caterpillarChannel", replyChannel = "cocoonChannel")
    Collection<Cocoon> process(Collection<Caterpillar> eaggCollection);
}