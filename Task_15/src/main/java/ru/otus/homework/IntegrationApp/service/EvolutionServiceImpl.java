package ru.otus.homework.IntegrationApp.service;

import ru.otus.homework.IntegrationApp.domain.Butterfly;
import ru.otus.homework.IntegrationApp.domain.Caterpillar;
import ru.otus.homework.IntegrationApp.domain.Cocoon;
import ru.otus.homework.IntegrationApp.domain.Eagg;

public class EvolutionServiceImpl implements EvolutionService{
    @Override
    public Caterpillar transformToCaterpillar(Eagg eagg) {
        return new Caterpillar();
    }

    @Override
    public Cocoon transformToCocoon(Caterpillar caterpillar) {
        return new Cocoon();
    }

    @Override
    public Butterfly transformToButterfly(Cocoon cocoon) {
        return new Butterfly();
    }
}