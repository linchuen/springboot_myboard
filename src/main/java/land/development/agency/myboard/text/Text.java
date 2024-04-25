package land.development.agency.myboard.text;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Document("text")
public class Text {
    @Id
    private String id;
    @NotNull
    @Length(min = 1, max = 200)
    private String filename;
    private String createAt;
    private Date startAt;
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
