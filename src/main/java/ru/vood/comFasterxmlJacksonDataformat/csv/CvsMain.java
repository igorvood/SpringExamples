package ru.vood.comFasterxmlJacksonDataformat.csv;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import java.io.File;
import java.io.IOException;

public class CvsMain {
    public static void main(String[] args) throws IOException {


        // Important: create XmlMapper; it will use proper factories, workarounds
        ObjectMapper csvMapper = new CsvMapper();

        CsvSchema schema = ((CsvMapper) csvMapper).schemaFor(ArticleInfo.class)
                .withHeader()
                .sortedBy(String::compareTo);
//        String csv = xmlMapper.writeValueAsString(new SpanShapeRenderer.Simple());
// or


        final ArticleInfo articleInfo = new ArticleInfo(1, "title obj", "category obj"/*, new Author(2, "Bob")*/);
        final ArticleInfo articleInfo1 = new ArticleInfo(2, "2 title obj", "2 category obj"/*, new Author(2, "Bob")*/);
        //final ArticleInfo articleInfo = new ArticleInfo(1, "title obj", "category obj", new Author(2, "Bob"));
//        final Author author = new Author(4, "4 Bob");
        // /List<Author>

        final File resultFile = new File("test.csv");
        if (resultFile.exists()) {
            resultFile.delete();
            resultFile.createNewFile();
        } else {
            resultFile.createNewFile();
        }


        csvMapper.writerFor(ArticleInfo.class)
                .with(schema)
                .writeValues(resultFile)
                .write(articleInfo)
                .write(articleInfo1)
                .flush();

        final ObjectReader objectReader = csvMapper.readerFor(ArticleInfo.class).readValue(resultFile);
/*
        final Object o = objectReader.getFactory().
        System.out.println(o);
*/


    }
}
