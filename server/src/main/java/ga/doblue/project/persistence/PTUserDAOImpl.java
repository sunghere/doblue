package ga.doblue.project.persistence;

import ga.doblue.project.model.PTUser;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by SungHere on 2017-06-14.
 */
@Repository
public class PTUserDAOImpl implements PTUserDAO {
    private String ns = "PTUser.";

    @Autowired
    SqlSession sqlSession;

    @Override
    public PTUser login(PTUser user) throws Exception {
        return sqlSession.selectOne(ns + "login", user);
    }
    @Override
    public void update(PTUser user) throws Exception {
        sqlSession.update(ns + "update", user);
    }
}
