package ru.otus.homeWork.actuator;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SingleColumnRowMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DatabaseHealthIndicator implements HealthIndicator {

    private final JdbcTemplate template;

    @Override
    public Health health() {
        if(checkDb() == 1) {
            return Health.up().withDetail("Database", "working successfully").build();
        }
        return Health.up().withDetail("Database", "has problems").build();
    }

    private int checkDb() {
        List<Object> results = template.query("select 1 from dual", new SingleColumnRowMapper<>());
        return results.size();
    }
}