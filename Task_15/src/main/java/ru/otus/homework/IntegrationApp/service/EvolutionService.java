package ru.otus.homework.IntegrationApp.service;

import ru.otus.homework.IntegrationApp.domain.Butterfly;
import ru.otus.homework.IntegrationApp.domain.Caterpillar;
import ru.otus.homework.IntegrationApp.domain.Cocoon;
import ru.otus.homework.IntegrationApp.domain.Eagg;

public interface EvolutionService {

    Caterpillar transformToCaterpillar(Eagg eagg);
    Cocoon transformToCocoon(Caterpillar caterpillar);
    Butterfly transformToButterfly(Cocoon cocoon);
}