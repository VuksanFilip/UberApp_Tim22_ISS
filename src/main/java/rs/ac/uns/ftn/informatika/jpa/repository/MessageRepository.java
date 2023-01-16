package rs.ac.uns.ftn.informatika.jpa.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import rs.ac.uns.ftn.informatika.jpa.model.Message;
import rs.ac.uns.ftn.informatika.jpa.model.User;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface MessageRepository extends CrudRepository<Message, Long> {

    List<Message> findAll();
    Optional<Message> findById(String Long);
    Set<Message> findBySender(@Param("sender") User user);

}