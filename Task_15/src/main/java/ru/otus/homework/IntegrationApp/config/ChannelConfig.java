package ru.otus.homework.IntegrationApp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.PublishSubscribeChannel;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.MessageChannels;

@Configuration
public class ChannelConfig {

    @Bean
    public QueueChannel eaggsChannel() {
        return MessageChannels.queue( 10 ).get();
    }

    @Bean
    public PublishSubscribeChannel caterpillarChannel() {
        return MessageChannels.publishSubscribe().get();
    }

    @Bean
    public PublishSubscribeChannel cocoonChannel() {
        return MessageChannels.publishSubscribe().get();
    }

    @Bean
    public PublishSubscribeChannel butterflyChannel() {
        return MessageChannels.publishSubscribe().get();
    }

    @Bean
    public IntegrationFlow eaggsFlow() {
        return IntegrationFlows.from( "eaggsChannel" )
                .split()
                .handle( "EvolutionService", "transformToCaterpillar" )
                .aggregate()
                .channel( "caterpillarChannel" )
                .get();
    }

    @Bean
    public IntegrationFlow caterpillarFlow() {
        return IntegrationFlows.from( "caterpillarChannel" )
                .split()
                .handle( "EvolutionService", "transformToCocoon" )
                .aggregate()
                .channel( "cocoonChannel" )
                .get();
    }

    @Bean
    public IntegrationFlow cocoonFlow() {
        return IntegrationFlows.from( "cocoonChannel" )
                .split()
                .handle( "EvolutionService", "transformToButterfly" )
                .aggregate()
                .channel( "butterflyChannel" )
                .get();
    }
}