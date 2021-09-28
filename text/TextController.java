package Land.Development.Agency.myboard.text;

import Land.Development.Agency.myboard.video.Video;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TextController {
    @Autowired
    private TextService textService;

    @PostMapping(value = "/text",consumes = MediaType.APPLICATION_JSON_VALUE)
    public Text uploadText(@RequestBody Text text){
        return textService.createNewText(text);
    }

    @GetMapping("text/all")
    public List<Text> getAllText(){
        return textService.getAllText();
    }

    @GetMapping("text")
    public List<Text> getEnabledText(){
        return textService.getEnabledText();
    }

    @PutMapping("text/{id}")
    public Text updateText(@PathVariable String id,@RequestBody Text text){
        return textService.updateText(id,text);
    }

    @DeleteMapping("text/{id}")
    public void deleteText(@PathVariable String id){
        textService.deleteText(id);
    }
}
