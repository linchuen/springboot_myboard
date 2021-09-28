package Land.Development.Agency.myboard.text;

import Land.Development.Agency.myboard.picture.Picture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class TextService {
    @Autowired
    private TextRepository textRepository;
    private SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    public Text createNewText(Text text){
        text.setCreateAt(simpleDateFormat.format(Calendar.getInstance().getTime()));
        text.setEnabled(true);
        System.out.println(text.toString());
        textRepository.insert(text);
        return text;
    }
    public List<Text> getAllText(){
        return textRepository.findAll();
    }
    public List<Text> getEnabledText(){
        return textRepository.findByEnabled(true);
    }

    public Text updateText(String id,Text text){
        if(textRepository.existsById(id)){
            text.setId(textRepository.findById(id).get().getId());
            text.setCreateAt(textRepository.findById(id).get().getCreateAt());
            textRepository.save(text);
        }
        return text;
    }

    public void deleteText(String id){
        if(textRepository.existsById(id)){
            textRepository.deleteById(id);
        }
    }
}
