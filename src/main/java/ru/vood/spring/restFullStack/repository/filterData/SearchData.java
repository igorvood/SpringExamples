package ru.vood.spring.restFullStack.repository.filterData;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchData {

    private Long skip = 0L;
    private Long maxResults = 20L;
    private List<ActivityFilter> filters = new ArrayList<>();
    private ActivitySorting sorting;

}
