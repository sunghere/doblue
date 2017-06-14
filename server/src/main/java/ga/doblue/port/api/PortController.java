package ga.doblue.port.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ga.doblue.port.Service.PortService;
import ga.doblue.port.help.AjaxResult;
import ga.doblue.port.model.Port;

import java.util.List;

/**
 * Created by SungHere on 2017-06-02.
 */
@RestController
@RequestMapping("/api/todos")
public class PortController { /* Rest 컨트롤러 */
    private final PortService service;

    @Autowired
    public PortController(PortService service) {
        this.service = service;
    }

    @GetMapping
    List<Port> todoList() {
        return service.listAll();
    }


    @PostMapping/* Insert (할일 추가 ) */
    ResponseEntity<?> create(@RequestBody Port todo) {
        AjaxResult ajaxResult = new AjaxResult();

        if (service.create(todo)) {
            ajaxResult.setResult("SUCS");

        } else {
            ajaxResult.setResult("FAIL");

        }
        return new ResponseEntity<AjaxResult>(ajaxResult, HttpStatus.OK);

    }

    @PutMapping(value = "/{id}", consumes = "application/json") /* 할일 완료 여부 */
    ResponseEntity<?> update(@PathVariable Integer id, @RequestBody Port todo) {
        todo.setId(id);
        AjaxResult ajaxResult = new AjaxResult();
        if (service.complete(todo)) {
            ajaxResult.setResult("SUCS");

        } else {
            ajaxResult.setResult("FAIL");

        }
        return new ResponseEntity<AjaxResult>(ajaxResult, HttpStatus.OK);
    }

    @DeleteMapping("/{id}") /* 할일 삭제 */
    ResponseEntity<?> delete(@PathVariable Integer id) {
        AjaxResult ajaxResult = new AjaxResult();

        if (service.delete(id)) {
            ajaxResult.setResult("SUCS");

        } else {
            ajaxResult.setResult("FAIL");

        }
        return new ResponseEntity<AjaxResult>(ajaxResult, HttpStatus.OK);

    }
}
