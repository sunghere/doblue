package ga.doblue.port.Service;

import org.springframework.stereotype.Service;

import ga.doblue.port.model.Port;
import ga.doblue.port.persistence.PortDao;

import java.util.List;

/**
 * Created by SungHere on 2017-06-02.
 */
@Service
public class PortService {
    private PortDao dao;


    public PortService(PortDao dao) {
        this.dao = dao;
    }

    public boolean create(Port todo) {
        int result = dao.create(todo);
        return result > 0;
    }

    public List<Port> listAll() {

        return dao.listAll();
    }

    public boolean complete(Port todo) {
        int result = dao.complete(todo);
        return result == 1;
    }

    public boolean delete(Integer id) {
        int result = dao.delete(id);
        return result == 1;
    }

}
