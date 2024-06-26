package land.development.agency.myboard.WebUser;

import land.development.agency.myboard.mailservice.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class WebUserService implements UserDetailsService {
    private final static String USER_NOT_FOUND_MSG = "user with email %s not found";
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private WebUserRepository webUserRepository;
    @Autowired
    private MailService mailService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return webUserRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, email)));
    }

    public ResponseEntity createNewUser(WebUser webUser) {
        boolean isExist = webUserRepository.findByEmail(webUser.getEmail()).isPresent();
        if (isExist) {
            return ResponseEntity.badRequest().body("User is already exist");
        }
        String encodedPassword = bCryptPasswordEncoder.encode(webUser.getPassword());
        webUser.setPassword(encodedPassword);

        webUserRepository.save(webUser);
        // mailService.sendConfirmMail(webUser.getEmail());
        return ResponseEntity.ok().body("Create successfully need to enable account");
    }

    public void enableUser(String id) {
        WebUser user = webUserRepository.findById(id).get();
        user.setEnabled(true);
        webUserRepository.save(user);
    }
}
