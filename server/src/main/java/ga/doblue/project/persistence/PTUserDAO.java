package ga.doblue.project.persistence;

import ga.doblue.project.model.PTUser;

/**
 * Created by SungHere on 2017-06-14.
 */
public interface PTUserDAO {

    PTUser login(PTUser user) throws Exception;

    void update(PTUser user) throws Exception;
}
