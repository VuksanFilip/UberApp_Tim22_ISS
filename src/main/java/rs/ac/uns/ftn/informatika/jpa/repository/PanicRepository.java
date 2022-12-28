package rs.ac.uns.ftn.informatika.jpa.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import rs.ac.uns.ftn.informatika.jpa.model.Panic;

import java.util.List;
import java.util.Optional;

@Repository
public interface PanicRepository extends CrudRepository<Panic, Long> {

    List<Panic> findAll();
    Optional<Panic> findById(String Long);

}