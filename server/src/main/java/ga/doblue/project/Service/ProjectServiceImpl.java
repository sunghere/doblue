package ga.doblue.project.Service;

import ga.doblue.project.model.Project;
import ga.doblue.project.persistence.ProjectDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by SungHere on 2017-06-02.
 */
@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectDAO dao;


    @Override
    @Transactional
    public void delete(Integer seq) throws Exception {
        dao.delete(seq);
    }

    @Override
    @Transactional
    public void update(Project project) throws Exception {
        dao.update(project);
    }

    @Override
    @Transactional
    public void complete(Project project) throws Exception {
        dao.complete(project);
    }

    @Override
    @Transactional
    public void insert(Project project) throws Exception {
        dao.insert(project);
    }

    @Override
    @Transactional
    public List<Project> listAll() throws Exception {
        return dao.listAll();
    }

    @Override
    @Transactional
    public Project detail(Project project) throws Exception {
        return dao.detail(project);
    }
}
