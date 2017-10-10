package ga.doblue.project.service;

import ga.doblue.project.model.PTUser;
import ga.doblue.project.persistence.PTUserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by SungHere on 2017-06-14.
 */
@Service
public class PTUserServiceImpl implements PTUserService {
    @Autowired
    PTUserDAO ptUserDAO;

    @Override
    public PTUser login(PTUser user) throws Exception {
        return ptUserDAO.login(user);
    }

    @Override
    public void update(PTUser user) throws Exception {
        ptUserDAO.update(user);
    }
}
