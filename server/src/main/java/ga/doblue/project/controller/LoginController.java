package ga.doblue.project.controller;

import ga.doblue.project.help.AjaxResult;
import ga.doblue.project.model.PTUser;
import ga.doblue.project.persistence.PTUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by SungHere on 2017-06-14.
 */
@RestController
@RequestMapping("login")
public class LoginController {

    @Autowired
    PTUserService service;

    @PostMapping/* 로그인  */
    ResponseEntity<?> login(@RequestBody PTUser ptUser, HttpServletRequest request) throws Exception {
        AjaxResult ajaxResult = new AjaxResult();
        PTUser user = null;
        try {
            user = service.login(ptUser);

            if (user != null) {
                request.getSession().setAttribute("login", user);
                request.getSession().setMaxInactiveInterval(30 * 60);
            }

        } catch (Exception e) {

        }

        return new ResponseEntity<PTUser>(user, HttpStatus.OK);

    }


}
