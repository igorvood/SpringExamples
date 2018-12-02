package ru.vood.spring.restFullStack.wrapRequest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Page {

    public static Page NULL_PAGE = new Page();

    private int totalRecords;

    /*
     */
    /*Номер страницы.*//*

    private int numPage;
    */
    /*Размер страницы.*//*

    private int count;
    */
    /*Количество страниц в результате.*//*

    private int total;
    */
    /*Признак последней страницы.*//*

    private int isLast;
*/

}
