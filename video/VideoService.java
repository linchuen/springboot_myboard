package Land.Development.Agency.myboard.video;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

@Service
public class VideoService {
    @Autowired
    private VideoRepository videoRepository;
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.SSS");

    public ResponseEntity createNewVideo(Video video) {
        video.setCreateAt(simpleDateFormat.format(Calendar.getInstance().getTime()));
        video.setStartAt(Calendar.getInstance().getTime());
        video.setExpiredAt(Calendar.getInstance().getTime());
        video.setEnabled(true);
        System.out.println(video.toString());
        videoRepository.insert(video);
        return ResponseEntity.ok().body(video);
    }

    public List<Video> getAllVideo() {
        return videoRepository.findAll();
    }

    public List<Video> getEnabledPicture() {
        return videoRepository.findByEnabled(true);
    }

    public ResponseEntity updateVideo(String id, Video video) {
        if (videoRepository.existsById(id)) {
            video.setId(videoRepository.findById(id).get().getId());
            video.setCreateAt(videoRepository.findById(id).get().getCreateAt());
            videoRepository.save(video);
            return ResponseEntity.ok().body(video);
        }
        return ResponseEntity.notFound().build();
    }

    public ResponseEntity deleteVideo(String id) {
        if (videoRepository.existsById(id)) {
            videoRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
