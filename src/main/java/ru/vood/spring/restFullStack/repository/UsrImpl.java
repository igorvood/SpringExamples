package ru.vood.spring.restFullStack.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ru.vood.spring.restFullStack.entity.UsrEntity;
import ru.vood.spring.restFullStack.repository.filterData.ActivityFilter;
import ru.vood.spring.restFullStack.repository.filterData.SearchData;
import ru.vood.spring.restFullStack.repository.intf.UsrRepository;
import ru.vood.spring.restFullStack.repository.jpaRepository.UsrJpaRepository;
import ru.vood.spring.restFullStack.rowMappers.mappedObjects.User;
import ru.vood.spring.restFullStack.rowMappers.mappedObjects.UserCount;

import javax.persistence.EntityManager;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

@Repository
public class UsrImpl implements UsrRepository {

    private static final Logger log = LoggerFactory.getLogger(UsrImpl.class);

    private final EntityManager em;

    private final UsrJpaRepository usrRepository;

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UsrImpl(UsrJpaRepository usrRepository, EntityManager em, JdbcTemplate jdbcTemplate) {
        this.usrRepository = usrRepository;
        this.em = em;
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public UsrEntity findOne(Long id) {
        final String query = "select a2 from UsrEntity a2 join fetch a2.act a3 join fetch a3.orgUdfCompany where a2.usrKey = :usrKey ";
        final HashMap<String, Object> queryParam = new HashMap<>();
        queryParam.put("usrKey", id);
        return getResult(em, query, UsrEntity.class, queryParam).get(0);
    }

    @Override
    public UsrEntity findByLogin(String login) {
        final String query = "select a2 from UsrEntity a2 join fetch a2.act a3 join fetch a3.orgUdfCompany where a2.usrLogin = :login ";
        final HashMap<String, Object> queryParam = new HashMap<>();
        queryParam.put("login", login);
        return getResult(em, query, UsrEntity.class, queryParam).get(0);
    }

    @Override
    public List<UsrEntity> findAllLimit(Integer limit) {
        final String query = "select a2 from UsrEntity a2 join fetch a2.act a3 join fetch a3.orgUdfCompany where rownum <= :count order by a2.id ";
        final HashMap<String, Object> queryParam = new HashMap<>();
        queryParam.put("count", limit.longValue());
        return getResult(em, query, UsrEntity.class, queryParam);
    }

    @Override
    public List<UsrEntity> findAllPage(Integer numPage, Integer sizePage) {
        final String query = "select a2 from UsrEntity a2 join fetch a2.act a3 join fetch a3.orgUdfCompany order by a2.id ";
        return getResult(em, query, UsrEntity.class, null, numPage, sizePage);
    }

    @Override
    public List<UsrEntity> findAll() {
        return usrRepository.findAll();
    }

    @Override
    public Long findAllPageCount() {
        return usrRepository.count();
    }

    @Override
    public List<User> findAllFilteredLimit(SearchData searchData) {

        log.info("findAllFilteredLimit:start: searchData={}", new Object[]{searchData});

        final String query = "SELECT usr_login, usr_display_name, usr_email, usr_title_hr, act_name "
                + " FROM (SELECT zvul.*, rownum AS rnum "
                + " FROM   (SELECT * "
                + " FROM z_vw_user_list "
                + prepareSqlWhere(searchData)
                + prepareSqlOrderBy(searchData)
                + " WHERE rownum <= ?)"
                + " WHERE  rnum > ?";

        RowMapper<User> userMapper = (rs, rowNum) -> new User(
                rs.getString("usr_login")
                , rs.getString("usr_display_name")
                , rs.getString("usr_email")
                , rs.getString("usr_title_hr")
                , rs.getString("act_name")
        );

        List<User> users = jdbcTemplate.query(query, new Object[]{searchData.getSkip() + searchData.getMaxResults(), searchData.getSkip()}, userMapper);


        final String countQuery = "SELECT count(usr_login) as user_count"
                + " FROM z_vw_user_list "
                + prepareSqlWhere(searchData);

        RowMapper<UserCount> countMapper = (rs, rowNum) -> new UserCount(
                rs.getLong("user_count")
        );

        Long count = jdbcTemplate.query(countQuery, new Object[]{}, countMapper).get(0).getCount();

        //UserSearchResult result = new UserSearchResult(users, count);

/*
        log.info("findAllFilteredLimit:end: searchData={}, Users.size={}, TotalUserCount={}",
                new Object[]{searchData, result.getUsers().size(), result.getTotalUserCount()});
*/

        return users;
    }


    private String prepareSqlOrderBy(SearchData searchData) {

        String result;
        if (searchData.getSorting() == null) {
            result = " ORDER BY usr_login) zvul ";
        } else {
            result = " ORDER BY " + searchData.getSorting().getField() + " " + searchData.getSorting().getCriteria() + ") zvul ";
        }

        return result;
    }

    private String prepareSqlWhere(SearchData searchData) {

        StringBuilder sb = new StringBuilder();
        int i = 0;
        for (ActivityFilter filter : searchData.getFilters()) {
            if (i == 0) {
                sb.append(" where ");
            } else {
                sb.append(" and ");
            }
            sb.append(filter.getFieldParam()).append(" ").append(filter.getCondition()).append(" ").append("'").append(filter.getValue()).append("'").append(" ");
            i++;
        }

        return sb.toString();
    }

    @Override
    public List<UsrEntity> save(UsrEntity entity) {
        final UsrEntity save = usrRepository.save(entity);
        return Collections.singletonList(save);
    }

    @Override
    public void delete(UsrEntity entity) {
        usrRepository.delete(entity);
    }

    @Override
    public void delete(Long id) {
        usrRepository.deleteById(id);
    }
}
