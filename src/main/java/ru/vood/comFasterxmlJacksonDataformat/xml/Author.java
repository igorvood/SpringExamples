package ru.vood.comFasterxmlJacksonDataformat.xml;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JacksonXmlRootElement(localName = "author", namespace = "com.concretePage")
public class Author {
    @JacksonXmlProperty(localName = "id")
    private long articleId;

    @JacksonXmlProperty(localName = "name")
    private String name;

}
