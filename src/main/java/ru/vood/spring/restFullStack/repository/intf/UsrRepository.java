package ru.vood.spring.restFullStack.repository.intf;

import ru.vood.spring.restFullStack.entity.UsrEntity;
import ru.vood.spring.restFullStack.repository.filterData.SearchData;
import ru.vood.spring.restFullStack.rowMappers.mappedObjects.User;

import java.util.List;

public interface UsrRepository extends QueryCommonFunction<UsrEntity> {

    UsrEntity findOne(Long id);

    UsrEntity findByLogin(String id);

    List<UsrEntity> findAllPage(Integer numPage, Integer sizePage);

    List<UsrEntity> findAll();

    Long findAllPageCount();

    List<User> findAllFilteredLimit(SearchData searchData);

    List<UsrEntity> findAllLimit(Integer limit);

    List<UsrEntity> save(UsrEntity entity);

    void delete(UsrEntity entity);

    void delete(Long id);

}
