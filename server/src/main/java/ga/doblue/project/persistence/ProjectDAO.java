package ga.doblue.project.persistence;

import ga.doblue.project.model.Project;

import java.util.List;

/**
 * Created by SungHere on 2017-06-14.
 */
public interface ProjectDAO {


    void delete(Integer seq) throws Exception; /* 삭제 */


    void update(Project project) throws Exception; /* 수정 */


    void complete(Project project) throws Exception; /* 완료 */


    void insert(Project project) throws Exception;/*생성*/


    List<Project> listAll() throws Exception;/*모든리스트*/


    Project detail(Project project) throws Exception;/*하나의 리스트 */

}
