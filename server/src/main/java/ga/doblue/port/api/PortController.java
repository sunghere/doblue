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
@RequestMapping("/api/port")
public class PortController { /* Rest 컨트롤러 */
    private final PortService service;

    @Autowired
    public PortController(PortService service) {
        this.service = service;
    }

    @GetMapping
    List<Port> portList() {
        return service.listAll();
    }


    @PostMapping/* Insert (포트폴리오 추가 ) */
    ResponseEntity<?> create(@RequestBody Port port) {
        AjaxResult ajaxResult = new AjaxResult();

        if (service.create(port)) {
            ajaxResult.setResult("SUCS");

        } else {
            ajaxResult.setResult("FAIL");

        }
        return new ResponseEntity<AjaxResult>(ajaxResult, HttpStatus.OK);

    }

    @PutMapping(value = "/{seq}", consumes = "application/json") /* 할일 완료 여부 */
    ResponseEntity<?> update(@PathVariable Integer seq, @RequestBody Port port) {
        port.setSeq(seq);
        AjaxResult ajaxResult = new AjaxResult();
        if (service.complete(port)) {
            ajaxResult.setResult("SUCS");

        } else {
            ajaxResult.setResult("FAIL");

        }
        return new ResponseEntity<AjaxResult>(ajaxResult, HttpStatus.OK);
    }

    @DeleteMapping("/{seq}") /* 할일 삭제 */
    ResponseEntity<?> delete(@PathVariable Integer seq) {
        AjaxResult ajaxResult = new AjaxResult();

        if (service.delete(seq)) {
            ajaxResult.setResult("SUCS");

        } else {
            ajaxResult.setResult("FAIL");

        }
        return new ResponseEntity<AjaxResult>(ajaxResult, HttpStatus.OK);

    }
}
