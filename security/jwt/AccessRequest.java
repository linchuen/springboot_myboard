package Land.Development.Agency.myboard.security.jwt;

import lombok.Data;

@Data
public class AccessRequest {
    private String username;
    private String password;
}
