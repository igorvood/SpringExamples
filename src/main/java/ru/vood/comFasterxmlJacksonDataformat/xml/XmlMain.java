package ru.vood.comFasterxmlJacksonDataformat.xml;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonStreamContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlFactory;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;

import java.io.File;
import java.io.IOException;

public class XmlMain {
    public static void main(String[] args) throws IOException {
        final ToXmlGenerator generator = new XmlFactory().createGenerator(new File("xmlTest.csv"), JsonEncoding.UTF8);
        final JsonStreamContext outputContext = generator.getOutputContext();

        // Important: create XmlMapper; it will use proper factories, workarounds
        ObjectMapper xmlMapper = new XmlMapper();

//        String csv = xmlMapper.writeValueAsString(new SpanShapeRenderer.Simple());
// or

        final File resultFile = new File("test.xml");
        if (resultFile.exists()) {
            resultFile.delete();
            resultFile.createNewFile();
        } else {
            resultFile.createNewFile();
        }
        xmlMapper.writeValue(resultFile, new ArticleInfo(1, "title obj", "category obj", new Author(2, "Bob")));
        xmlMapper.writeValue(resultFile, new ArticleInfo(3, "3 title obj", "3 category obj", new Author(4, "4 Bob")));


    }
}
