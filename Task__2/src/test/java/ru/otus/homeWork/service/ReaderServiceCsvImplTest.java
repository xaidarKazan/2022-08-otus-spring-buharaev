package ru.otus.homeWork.service;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class ReaderServiceCsvImplTest {

    private final ReaderServiceCsvImpl readerServiceCsv = new ReaderServiceCsvImpl("testData.csv");

    @Test
    void isReadDataWorksCorrect() {
        List<String[]> testData = new ArrayList<String[]>(Arrays.asList(new String[]{"Which season comes after summer?","autumn","winter","spring"}, new String[]{"How much is 1 plus 1?","2","1","0","3"}));

        List<String[]> reaturnTestData = readerServiceCsv.readData();

        Assert.assertArrayEquals(testData.toArray(), reaturnTestData.toArray());
    }
}