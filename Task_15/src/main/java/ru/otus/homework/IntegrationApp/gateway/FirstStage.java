package ru.otus.homework.IntegrationApp.gateway;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import ru.otus.homework.IntegrationApp.domain.Caterpillar;
import ru.otus.homework.IntegrationApp.domain.Eagg;

import java.util.Collection;

@MessagingGateway
public interface FirstStage {

    @Gateway(requestChannel = "eaggsChannel", replyChannel = "caterpillarChannel")
    Collection<Caterpillar> process(Collection<Eagg> eaggCollection);
}