package ru.vood.spring.restFullStack.repository.filterData;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.vood.spring.restFullStack.consts.SortingCriteria;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActivitySorting {

    private String field;
    private SortingCriteria criteria;

}
