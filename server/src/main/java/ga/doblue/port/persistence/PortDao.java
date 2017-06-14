package ga.doblue.port.persistence;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import ga.doblue.port.model.Port;

import javax.sql.DataSource;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class PortDao {
    private NamedParameterJdbcTemplate jdbc;
    private SimpleJdbcInsert insertAction;
    private RowMapper<Port> rowMapper = BeanPropertyRowMapper.newInstance(Port.class);

    public PortDao(DataSource dataSource) { /* Dao Init*/
        this.jdbc = new NamedParameterJdbcTemplate(dataSource);
        this.insertAction = new SimpleJdbcInsert(dataSource)
                .withTableName("todo")
                .usingGeneratedKeyColumns("id").usingColumns("todo");
    }


    public int delete(Integer id) { /* 삭제 */
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        return jdbc.update(PortSqls.DELETE_BY_ID, params);

    }

    public int complete(Port todo) {/*완료 */
        Map<String, Object> params = new HashMap<>();
        params.put("id", todo.getId());
        params.put("completed", todo.getCompleted());
        return jdbc.update(PortSqls.UPDATE_COMP_BY_ID, params);
    }

    public Integer create(Port todo) {/*생성*/
        SqlParameterSource params = new BeanPropertySqlParameterSource(todo);
        return insertAction.executeAndReturnKey(params).intValue();
    }

    public List<Port> listAll() {/*모든리스트*/
        Map<String, Object> params = Collections.emptyMap();
        return jdbc.query(PortSqls.SELECT_ALL, params, rowMapper);
    }


}
