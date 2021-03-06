package ga.doblue.project.controller;

import ga.doblue.project.service.ProjectService;
import ga.doblue.project.model.PTUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ga.doblue.project.service.ProjectServiceImpl;
import ga.doblue.project.help.AjaxResult;
import ga.doblue.project.model.Project;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by SungHere on 2017-06-02.
 */
@RestController
@RequestMapping("project")
public class ProjectController { /* Rest 컨트롤러 */

    @Autowired
    private ProjectService service;

    @Autowired
    public ProjectController(ProjectServiceImpl service) {
        this.service = service;
    }

    @GetMapping(value = "/{seq}") /* 하나 가져오기 */
    Project projectDetail(@PathVariable Integer seq) throws Exception {
        Project project = new Project();
        project.setSeq(seq);
        return service.detail(project);
    }

    @GetMapping /* 리스트 가져오기*/
    List<Project> projectList() throws Exception {
        return service.listAll();
    }

    @PostMapping/* Insert (프로젝트 추가 ) */
    ResponseEntity<?> create(@RequestBody Project project, HttpServletRequest request) throws Exception {
        AjaxResult ajaxResult = new AjaxResult();
        PTUser user = (PTUser) request.getSession().getAttribute("login");
        if (user != null) {
            try {
                service.insert(project);
                ajaxResult.setResult("SUCS");
            } catch (Exception e) {
                ajaxResult.setResult("FAIL");

            }

        } else {
            ajaxResult.setResult("FAIL");

        }

        return new ResponseEntity<AjaxResult>(ajaxResult, HttpStatus.OK);

    }

    @PutMapping(value = "/{seq}/completed", consumes = "application/json") /* 완료 여부 */
    ResponseEntity<?> completed(@PathVariable Integer seq, @RequestBody Project project) throws Exception {
        project.setSeq(seq);
        AjaxResult ajaxResult = new AjaxResult();
        try {
            service.complete(project);
            ajaxResult.setResult("SUCS");
        } catch (Exception e) {
            ajaxResult.setResult("FAIL");

        }
        return new ResponseEntity<AjaxResult>(ajaxResult, HttpStatus.OK);
    }

    @PutMapping(value = "/{seq}", consumes = "application/json") /* 수정 */
    ResponseEntity<?> update(@PathVariable Integer seq, @RequestBody Project project) {
        project.setSeq(seq);
        AjaxResult ajaxResult = new AjaxResult();
        try {
            service.update(project);
            ajaxResult.setResult("SUCS");
        } catch (Exception e) {
            ajaxResult.setResult("FAIL");

        }
        return new ResponseEntity<AjaxResult>(ajaxResult, HttpStatus.OK);
    }

    @PutMapping("/{seq}/detete") /* 삭제 */
    ResponseEntity<?> delete(@PathVariable Integer seq) {
        AjaxResult ajaxResult = new AjaxResult();

        try {
            service.delete(seq);
            ajaxResult.setResult("SUCS");
        } catch (Exception e) {
            ajaxResult.setResult("FAIL");

        }
        return new ResponseEntity<AjaxResult>(ajaxResult, HttpStatus.OK);

    }
}
