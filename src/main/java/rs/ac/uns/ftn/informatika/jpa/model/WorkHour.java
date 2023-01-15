package rs.ac.uns.ftn.informatika.jpa.model;

import rs.ac.uns.ftn.informatika.jpa.dto.request.RequestDriverWorkingHourStartDTO;
import rs.ac.uns.ftn.informatika.jpa.dto.response.ResponseDriverWorkingHourDTO;

import javax.persistence.*;
import java.util.Date;

@Entity
public class WorkHour {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    Long id;
    Date start;
    Date end;

    @OneToOne
    Driver driver;

    public WorkHour() {
    }

    public WorkHour(Date start, Date end, Driver driver) {
        this.start = start;
        this.end = end;
        this.driver = driver;
    }

    public WorkHour(Long id, Date start, Date end) {
        this.id = id;
        this.start = start;
        this.end = end;
        this.driver = new Driver();
    }

    public WorkHour(Driver driver, RequestDriverWorkingHourStartDTO dto){
        this.start = dto.getStart();
        this.end = new Date();
        this.driver = driver;
    }

    public WorkHour(Long id, Date start, Date end, Driver driver) {
        this.id = id;
        this.start = start;
        this.end = end;
        this.driver = driver;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public ResponseDriverWorkingHourDTO parseToResponse() {
        return new ResponseDriverWorkingHourDTO(this.id, this.start, this.end);
    }
}
