package land.development.agency.myboard.controller;

import land.development.agency.myboard.entity.WebUser;
import land.development.agency.myboard.enums.WebUserRole;
import land.development.agency.myboard.service.WebUserService;
import land.development.agency.myboard.request.RegisRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegisterController {
    @Autowired
    private WebUserService webUserService;


    @PostMapping(path = "/register", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity registerNewUser(@RequestBody RegisRequest request) {
        return webUserService.createNewUser(new WebUser(request.getUsername(), request.getEmail(), request.getPassword(), WebUserRole.USER));
    }

    @PutMapping(path = "/register/{id}")
    public void enableRegisterUser(@PathVariable String id) {
        webUserService.enableUser(id);
    }
}
