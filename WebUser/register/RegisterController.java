package Land.Development.Agency.myboard.WebUser.register;

import Land.Development.Agency.myboard.WebUser.WebUser;
import Land.Development.Agency.myboard.WebUser.WebUserRole;
import Land.Development.Agency.myboard.WebUser.WebUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegisterController {
    @Autowired
    private WebUserService webUserService;

    @PostMapping(path = "/register",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity registerNewUser(@RequestBody RegisRequest request){
        webUserService.createNewUser(new WebUser(request.getUsername(), request.getEmail(), request.getPassword(), WebUserRole.USER));
        return ResponseEntity.ok().build();
    }
}
