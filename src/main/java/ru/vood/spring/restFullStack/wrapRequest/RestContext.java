package ru.vood.spring.restFullStack.wrapRequest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RestContext {
    private Date date;
    private Object context;
    private Page page;
}
