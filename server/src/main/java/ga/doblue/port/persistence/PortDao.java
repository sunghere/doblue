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
                .withTableName("FORT")
                .usingGeneratedKeyColumns("seq").usingColumns("content")
                .usingColumns("url").usingColumns("img")
                .usingColumns("title").usingColumns("sdate").usingColumns("edate");
    }
  

    public int delete(Integer seq) { /* 삭제 */
        Map<String, Object> params = new HashMap<>();
        params.put("seq", seq);
        return jdbc.update(PortSqls.DELETE_BY_SEQ, params);

    }
    public int update(Port port) { /* 수정 */
        Map<String, Object> params = new HashMap<>();
        params.put("seq", port.getSeq());
        params.put("img", port.getImg());
        params.put("url", port.getUrl());
        params.put("title", port.getTitle());
        params.put("content", port.getContent());
        params.put("sdate", port.getSdate());
        params.put("edate", port.getEdate());
        return jdbc.update(PortSqls.UPDATE_BY_SEQ, params);

    }
    public int complete(Port port) {/*완료 */
        Map<String, Object> params = new HashMap<>();
        params.put("seq", port.getSeq());
        params.put("completed", port.getCompleted());
        return jdbc.update(PortSqls.UPDATE_COMP_BY_SEQ, params);
    }

    public Integer create(Port port) {/*생성*/
        SqlParameterSource params = new BeanPropertySqlParameterSource(port);
        return insertAction.executeAndReturnKey(params).intValue();
    }

    public List<Port> listAll() {/*모든리스트*/
        Map<String, Object> params = Collections.emptyMap();
        return jdbc.query(PortSqls.SELECT_ALL, params, rowMapper);
    }
    public Port detail() {/*모든리스트*/
        Map<String, Object> params = Collections.emptyMap();
        return (Port) jdbc.query(PortSqls.SELECT_BY_SEQ, params, rowMapper);
    }

}
