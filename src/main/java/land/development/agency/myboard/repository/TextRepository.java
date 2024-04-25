package land.development.agency.myboard.repository;

import land.development.agency.myboard.entity.Text;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TextRepository extends MongoRepository<Text,String> {
    List<Text> findByFilename(String filename);
    List<Text> findByEnabled(Boolean enabled);
}
