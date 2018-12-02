package ru.vood.spring.restFullStack.repository.filterData;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActivityFilter {

    private String fieldParam;
    private String value;
    private String condition;

}
