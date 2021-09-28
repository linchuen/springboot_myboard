package Land.Development.Agency.myboard.picture;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Setter
@Getter
@ToString
@NoArgsConstructor
@Document("picture")
public class Picture {
    @Id
    private String id;
    private String filename;
    private String createAt;
    private Date startAt;
    private Date expiredAt;
    private Boolean enabled;

    public Picture(String filename){
        this.filename=filename;
    }
    public Picture(String filename, Date startAt, Date expiredAt, Boolean enabled){
        this.filename=filename;
        this.startAt=startAt;
        this.expiredAt=expiredAt;
        this.enabled=enabled;
    }
}
