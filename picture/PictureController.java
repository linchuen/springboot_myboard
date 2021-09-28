package Land.Development.Agency.myboard.picture;

import Land.Development.Agency.myboard.uploadfile.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
public class PictureController {
    @Autowired
    private PictureService pictureService;
    @Autowired
    private UploadService uploadService;

    @PostMapping(value = "/picture")
    public void uploadPicture(@RequestParam("image") MultipartFile multipartFile){
        if(uploadService.uploadPicture(multipartFile)){
            Picture newPicture=new Picture(multipartFile.getOriginalFilename());
            pictureService.createNewPicture(newPicture);
        }
    }

    @GetMapping("picture/all")
    public List<Picture> getAllPicture(){
        return pictureService.getAllPicture();
    }

    @GetMapping("picture")
    public List<Picture> getEnabledPicture(){
        return pictureService.getEnabledPicture();
    }

    @PutMapping("picture/{id}")
    public Picture updatePicture(@PathVariable String id,@RequestBody Picture picture){
        return pictureService.updatePicture(id,picture);
    }

    @DeleteMapping("picture/{id}")
    public void deletePicture(@PathVariable String id){
        pictureService.deletePicture(id);
    }
}
