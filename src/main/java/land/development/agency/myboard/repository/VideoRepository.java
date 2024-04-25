package land.development.agency.myboard.repository;

import land.development.agency.myboard.entity.Video;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VideoRepository extends MongoRepository<Video,String> {
    List<Video> findByFilename(String filename);
    List<Video> findByEnabled(Boolean enabled);
}
