package Land.Development.Agency.myboard.picture;

import Land.Development.Agency.myboard.text.Text;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PictureService {
    @Autowired
    private PictureRepository pictureRepository;
    private SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.SSS");

    public Picture createNewPicture(Picture picture){
        picture.setCreateAt(simpleDateFormat.format(Calendar.getInstance().getTime()));
        picture.setStartAt(Calendar.getInstance().getTime());
        picture.setExpiredAt(Calendar.getInstance().getTime());
        picture.setEnabled(true);
        System.out.println(picture.toString());
        pictureRepository.insert(picture);
        return picture;
    }

    public List<Picture> getAllPicture(){
        return pictureRepository.findAll();
    }
    public List<Picture> getEnabledPicture(){
        return pictureRepository.findByEnabled(true);
    }

    public Picture updatePicture(String id,Picture picture){
        if(pictureRepository.existsById(id)){
            picture.setId(pictureRepository.findById(id).get().getId());
            picture.setCreateAt(pictureRepository.findById(id).get().getCreateAt());
            pictureRepository.save(picture);
        }
        return picture;
    }

    public void deletePicture(String id){
        if(pictureRepository.existsById(id)){
            pictureRepository.deleteById(id);
        }
    }
}
