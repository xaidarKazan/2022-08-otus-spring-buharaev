package ru.otus.homeWork.service;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

@Service
public class ReaderServiceCsvImpl implements ReaderService {

    private final String csvPath;

    public ReaderServiceCsvImpl(@Value("testContent.csv")String csvPath) {
        this.csvPath = csvPath;
    }

    @Override
    public List<String[]> readData() {
        List<String[]> rawCsvData;
        Resource resource = new ClassPathResource(csvPath);
        if (resource == null) {
            throw new IllegalArgumentException("CSV file not found!");
        } else {
            try(FileReader fr = new FileReader(resource.getFile());
                CSVReader reader = new CSVReader(fr)) {
                rawCsvData = reader.readAll();
            } catch (IOException | CsvException e) {
                throw new RuntimeException(e);
            }
        }
        return rawCsvData;
    }
}