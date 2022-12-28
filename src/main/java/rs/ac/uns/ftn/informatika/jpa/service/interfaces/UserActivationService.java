package rs.ac.uns.ftn.informatika.jpa.service.interfaces;

import rs.ac.uns.ftn.informatika.jpa.model.UserActivation;

import java.util.List;
import java.util.Optional;

public interface UserActivationService {

    List<UserActivation> getAll();

    Optional<UserActivation> getUserActivation(String id);

    void add(UserActivation userActivation);
}
