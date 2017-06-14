package ga.doblue.project.persistence;

public class ProjectSqls {
    /*todos 삭제*/
    static final String DELETE_BY_SEQ =
            "UPDATE port SET del= :del WHERE seq= :seq";
    /*todos 완료 업데이트*/
    static final String UPDATE_COMP_BY_SEQ =
            "UPDATE todo SET completed = :completed WHERE seq= :seq";
    /*리스트*/
    static final String SELECT_ALL =
            "SELECT seq,todo,completed,date FROM todo ORDER BY seq DESC";
    
    static final String UPDATE_BY_SEQ =
    		"UPDATE todo SET title= :title,sdate= :sdate,edate= :edate, content = :content,completed = :completed WHERE seq= :seq";

    static final String SELECT_BY_SEQ =
    		"SELECT seq,todo,completed,date FROM todo WHERE seq= :seq ORDER BY seq DESC";

}
