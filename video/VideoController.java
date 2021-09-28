package Land.Development.Agency.myboard.video;

import Land.Development.Agency.myboard.uploadfile.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
public class VideoController {
    @Autowired
    private VideoService videoService;
    @Autowired
    private UploadService uploadService;

    @PostMapping(value = "/video")
    public ResponseEntity uploadVideo(@RequestParam("video") MultipartFile multipartFile) {
        if (uploadService.uploadVideo(multipartFile)) {
            Video newVideo = new Video(multipartFile.getOriginalFilename());
            return videoService.createNewVideo(newVideo);
        }
        return ResponseEntity.badRequest().body("create error");
    }

    @GetMapping("video/all")
    public List<Video> getAllVideo() {
        return videoService.getAllVideo();
    }

    @GetMapping("video")
    public List<Video> getEnabledVideo() {
        return videoService.getEnabledPicture();
    }

    @PutMapping("video/{id}")
    public ResponseEntity updateVideo(@PathVariable String id, @RequestBody Video video) {
        return videoService.updateVideo(id, video);
    }

    @DeleteMapping("video/{id}")
    public ResponseEntity deleteVideo(@PathVariable String id) {
        return videoService.deleteVideo(id);
    }
}
