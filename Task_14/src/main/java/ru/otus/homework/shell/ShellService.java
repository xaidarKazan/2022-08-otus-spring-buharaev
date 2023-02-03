package ru.otus.homework.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@ShellComponent
@RequiredArgsConstructor
public class ShellService {

    private final Job jobOnMigrationSqlToNosql;

    private final Job jobOnMigrationNoSqlToSql;

    private final JobLauncher jobLauncher;

    @ShellMethod(value = "Start migration to NoSql", key = "sm-nos")
    public void doMigrateToNoSql() throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {
        String dateTime = DateTimeFormatter.ofPattern("MMM dd yyyy, hh:mm:ss a").format(LocalDateTime.now());
        jobLauncher.run(jobOnMigrationSqlToNosql, new JobParametersBuilder()
                                .addString("Start migration to NoSql = ",dateTime.toString()).toJobParameters());
    }

    @ShellMethod(value = "Start migration to sql", key = "sm-sql")
    public void doMigrateToSql() throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {
        String dateTime = DateTimeFormatter.ofPattern("MMM dd yyyy, hh:mm:ss a").format(LocalDateTime.now());
        jobLauncher.run(jobOnMigrationNoSqlToSql, new JobParametersBuilder()
                .addString("Start migration to Sql = ",dateTime.toString()).toJobParameters());
    }
}