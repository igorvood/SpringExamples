package ru.vood.spring.restFullStack.repository.intf;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.HashMap;
import java.util.List;

public interface QueryCommonFunction<T> {


    default List<T> getResult(EntityManager em, String query, Class<T> aClass, HashMap<String, Object> queryParam) {
        return getQuery(em, query, aClass, queryParam).getResultList();
    }

    default List<T> getResult(EntityManager em, String query, Class<T> aClass, HashMap<String, Object> queryParam, Integer numPage, Integer pageSize) {
        return this.getQuery(em, query, aClass, queryParam)
                .setFirstResult(numPage * pageSize)
                .setMaxResults(pageSize)
                .getResultList();
    }

    default List<T> getResult(EntityManager em, String query, Class<T> aClass) {
        return getQuery(em, query, aClass, null)
                .getResultList();
    }

    default List<T> getResult(EntityManager em, String query, Class<T> aClass, Integer numPage, Integer pageSize) {
        return this.getQuery(em, query, aClass, null)
                .setFirstResult(numPage * pageSize)
                .setMaxResults(pageSize)
                .getResultList();
    }


    default TypedQuery<T> getQuery(EntityManager em, String query, Class<T> aClass, HashMap<String, Object> queryParam) {
        final TypedQuery<T> q = em.createQuery(query, aClass);
        if (queryParam != null && queryParam.size() > 0) {
            queryParam.forEach(q::setParameter);
        }
        return q;
    }

}
