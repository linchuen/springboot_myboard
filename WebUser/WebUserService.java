package Land.Development.Agency.myboard.WebUser;

import org.springframework.beans.factory.annotation.Autowired;
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

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return webUserRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, email)));
    }

    public String createNewUser(WebUser webUser) {
        boolean isExist = webUserRepository.findByEmail(webUser.getEmail()).isPresent();
        if (isExist) {
            return "User is already exist";
        }
        String encodedPassword = bCryptPasswordEncoder.encode(webUser.getPassword());
        webUser.setPassword(encodedPassword);

        webUserRepository.save(webUser);
        return "Create successfully";
    }
}
