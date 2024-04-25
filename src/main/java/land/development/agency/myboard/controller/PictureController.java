package land.development.agency.myboard.controller;

import land.development.agency.myboard.entity.Picture;
import land.development.agency.myboard.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
public class PictureController {
    @Autowired
    private PictureService pictureService;

    @PostMapping(value = "/picture")
    public ResponseEntity<?> uploadPicture(@RequestParam("image") MultipartFile multipartFile,
                                        @RequestParam("startAt") Date startAt,
                                        @RequestParam("expiredAt") Date expiredAt) {
        Optional<Picture> picture = pictureService.createNewPicture(multipartFile, startAt, expiredAt);
        if (picture.isPresent()) {
            return ResponseEntity.ok().body(picture);
        } else {
            return ResponseEntity.badRequest().body("create error");
        }
    }

    @GetMapping("picture/all")
    public ResponseEntity<List<Picture>> getAllPicture() {
        return  ResponseEntity.ok().body(pictureService.getAllPicture());
    }

    @GetMapping("picture")
    public ResponseEntity<List<Picture>> getEnabledPicture() {
        return  ResponseEntity.ok().body(pictureService.getEnabledPicture());
    }

    @PutMapping("picture/{id}")
    public ResponseEntity updatePicture(@PathVariable String id, @RequestBody Picture picture) {
        return pictureService.updatePicture(id, picture);
    }

    @DeleteMapping("picture/{id}")
    public ResponseEntity deletePicture(@PathVariable String id) {
        return pictureService.deletePicture(id);
    }
}
