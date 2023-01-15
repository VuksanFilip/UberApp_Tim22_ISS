package rs.ac.uns.ftn.informatika.jpa.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.informatika.jpa.dto.messages.MessageDTO;
import rs.ac.uns.ftn.informatika.jpa.dto.request.RequestLoginDTO;
import rs.ac.uns.ftn.informatika.jpa.dto.request.RequestNoteDTO;
import rs.ac.uns.ftn.informatika.jpa.dto.request.RequestUserChangePasswordDTO;
import rs.ac.uns.ftn.informatika.jpa.dto.request.RequestUserResetPasswordDTO;
import rs.ac.uns.ftn.informatika.jpa.dto.response.*;
import rs.ac.uns.ftn.informatika.jpa.dummy.UserDummy;
import rs.ac.uns.ftn.informatika.jpa.model.*;
import rs.ac.uns.ftn.informatika.jpa.service.DriverServiceImpl;
import rs.ac.uns.ftn.informatika.jpa.service.NoteServiceImpl;
import rs.ac.uns.ftn.informatika.jpa.service.PassengerServiceImpl;
import rs.ac.uns.ftn.informatika.jpa.service.UserServiceImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private UserDummy userDummy = new UserDummy();
    private UserServiceImpl userService;
    private PassengerServiceImpl passengerService;
    private DriverServiceImpl driverService;
    private NoteServiceImpl noteService;

    public UserController(UserServiceImpl userService, PassengerServiceImpl passengerService, DriverServiceImpl driverService, NoteServiceImpl noteService){
        this.userService = userService;
        this.passengerService = passengerService;
        this.driverService = driverService;
        this.noteService = noteService;
    }

    @PutMapping (value = "/{id}/changePassword", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> changePassword(@PathVariable("id") String id, @RequestBody RequestUserChangePasswordDTO requestUserChangePasswordDTO) {

        if(!userService.existsById(id)){
            return new ResponseEntity<>(new MessageDTO("User does not exist!"), HttpStatus.NOT_FOUND);
        }
        User user = userService.getUser(id).get();
        if(user.getPassword().equals(requestUserChangePasswordDTO.getOldPassword())){
            user.setPassword(requestUserChangePasswordDTO.getNewPassword());
            userService.add(user);
            return new ResponseEntity<>("Password successfully changed!", HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>("Current password is not matching!", HttpStatus.BAD_REQUEST);
    }

    @GetMapping(value = "/{id}/resetPassword", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> resetPassword(@PathVariable("id") String id){
        if(!userService.existsById(id)){
            return new ResponseEntity<>(new MessageDTO("User does not exist!"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("Email with reset code has been sent!", HttpStatus.NO_CONTENT);
    }

    @PutMapping (value = "/{id}/resetPassword", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> changePasswordWithResetCode(@PathVariable("id") String id, @RequestBody RequestUserResetPasswordDTO requestUserResetPasswordDTO) {

        //TODO OVO NE RADI DOBRO, SKONTATI KAKO FUNCIONISE CODE
        if(!userService.existsById(id)){
            return new ResponseEntity<>(new MessageDTO("User does not exist!"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("Code is expired or not correct!", HttpStatus.BAD_REQUEST);
    }

    //TODO NAPRAVITI DA BUDE PAGEBLE (ZAJEBANO)
    @GetMapping(value = "/{id}/ride", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getUserRides(@PathVariable("id") String id, Pageable page) {

        List<ResponseRideNoStatusDTO> responseRides = new ArrayList<>();
        if(passengerService.existsById(id)){
            List<Ride> rides = passengerService.getPassenger(id).get().getRides();
            for(Ride r: rides){
                responseRides.add(r.parseToResponseNoStatusForUser());
            }
            return new ResponseEntity<>(responseRides, HttpStatus.NOT_FOUND);
        }
        else if(driverService.existsById(id)){
            List<Ride> rides = driverService.getDriver(id).get().getRides();
            for(Ride r: rides){
                responseRides.add(r.parseToResponseNoStatusForUser());
            }
            return new ResponseEntity<>(responseRides, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("User does not exist", HttpStatus.NOT_FOUND);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getUser(Pageable page) {

        Page<User> users = userService.findAll(page);
        int size = userService.getAll().size();
        List<ResponseUserWithIdDTO> responseUserDTOS = new ArrayList<>();
        for(User u: users){
            responseUserDTOS.add(u.parseToResponseUserWithId());
        }
        return new ResponseEntity<>(new ResponsePageDTO(size, Arrays.asList(responseUserDTOS.toArray())), HttpStatus.NOT_FOUND);
    }

    //TODO OVDE SE TOKENI RADE
    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> login(@RequestBody RequestLoginDTO requestLoginDTO){
        return null;
    }

    @PutMapping(value = "/{id}/block")
    public ResponseEntity<?> blockUser(@PathVariable("id") String id){

        if(userService.existsById(id) == false){
            return new ResponseEntity<>(new MessageDTO("Message placeholder (User does not exist!)"), HttpStatus.NOT_FOUND);
        }
        User user = userService.getUser(id).get();
        if(user.isBlocked()){
            return new ResponseEntity<>("User already blocked!", HttpStatus.BAD_REQUEST);
        }

        user.setBlocked(true);
        userService.add(user);
        return new ResponseEntity<>("User is successfully blocked", HttpStatus.NO_CONTENT);
    }

    @PutMapping(value = "/{id}/unblock")
    public ResponseEntity<?> ublockUser(@PathVariable("id") String id){

        if(userService.existsById(id) == false){
            return new ResponseEntity<>(new MessageDTO("Message placeholder (User does not exist!)"), HttpStatus.NOT_FOUND);
        }
        User user = userService.getUser(id).get();
        if(!user.isBlocked()){
            return new ResponseEntity<>("User already unblocked!", HttpStatus.BAD_REQUEST);
        }

        user.setBlocked(false);
        userService.add(user);
        return new ResponseEntity<>("User is successfully ublocked", HttpStatus.NO_CONTENT);
    }

    //TODO IMA VEZE SA TOKENIMA
    @GetMapping(value = "/{id}/message", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getUserMessages(@PathVariable("id") String id){
        return null;
    }

    //TODO IMA VEZE SA TOKENIMA
    @PostMapping(value = "/{id}/message", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> sendMessageToUser(@PathVariable("id") String id){
        return null;
    }

    @PostMapping(value = "/{id}/note", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createNote(@PathVariable("id") String id, @RequestBody RequestNoteDTO requestNoteDTO){
        if(userService.existsById(id) == false){
            return new ResponseEntity<>(new MessageDTO("Message placeholder (User does not exist!)"), HttpStatus.NOT_FOUND);
        }
        User user = userService.getUser(id).get();
        Note note = requestNoteDTO.parseToNote(user);
        noteService.add(note);
        return new ResponseEntity<>(note.parseToResponse(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}/note", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getUserNotes(@PathVariable("id") String id, Pageable page){
        if(userService.existsById(id) == false){
            return new ResponseEntity<>(new MessageDTO("Message placeholder (User does not exist!)"), HttpStatus.NOT_FOUND);
        }
        User user = userService.getUser(id).get();
        Page<Note> notes = noteService.findAll(page);
        int size = noteService.getAll().size();
        List<ResponseNoteDTO> responseNoteDTOS = new ArrayList<>();
        for(Note n: notes){
            if(n.getUser().getId().equals(user.getId())){
                responseNoteDTOS.add(n.parseToResponse());
            }
        }
        return new ResponseEntity<>(new ResponsePageDTO(size, Arrays.asList(responseNoteDTOS.toArray())), HttpStatus.OK);
    }
}
