package ga.doblue.project.persistence;

import ga.doblue.project.model.Project;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProjectDAOImpl implements ProjectDAO {

    private String ns = "Project.";

    @Autowired
    SqlSession sqlSession;


    @Override
    public void delete(Integer seq) throws Exception {

        sqlSession.update(ns + "delete", seq);
    }

    @Override
    public void update(Project project) throws Exception {
        sqlSession.update(ns + "update", project);

    }

    @Override
    public void complete(Project project) throws Exception {
        sqlSession.update(ns + "complete", project);

    }

    @Override
    public void insert(Project project) throws Exception {
        sqlSession.insert(ns + "insert", project);

    }

    @Override
    public List<Project> listAll() throws Exception {
        return sqlSession.selectList(ns + "listAll");
    }

    @Override
    public Project detail(Project project) throws Exception {
        return sqlSession.selectOne(ns + "detail", project);
    }
}
