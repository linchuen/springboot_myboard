package land.development.agency.myboard.security.jwt;

import lombok.Data;

@Data
public class AccessRequest {
    private String username;
    private String password;
}
