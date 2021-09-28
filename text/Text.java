package Land.Development.Agency.myboard.text;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Document("text")
public class Text {
    @Id
    private String id;
    private String filename;
    private String createAt;
    @DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    private Date startAt;
    @DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    private Date expiredAt;
    private Boolean enabled;

    public Text(String filename, Date startAt, Date expiredAt){
        this.filename=filename;
        this.startAt=startAt;
        this.expiredAt=expiredAt;
    }
    public Text(String filename, Date startAt, Date expiredAt,Boolean enabled){
        this.filename=filename;
        this.startAt=startAt;
        this.expiredAt=expiredAt;
        this.enabled=enabled;
    }
}
