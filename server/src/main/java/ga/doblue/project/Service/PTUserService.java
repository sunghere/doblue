package ga.doblue.project.Service;

import ga.doblue.project.model.PTUser;

/**
 * Created by SungHere on 2017-06-14.
 */
public interface PTUserService {

    PTUser login(PTUser user) throws Exception;

    void update(PTUser user) throws Exception;
}
