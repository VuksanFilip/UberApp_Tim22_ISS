package rs.ac.uns.ftn.informatika.jpa.dto.request;

import rs.ac.uns.ftn.informatika.jpa.dto.response.ResponseDriverDocumentDTO;
import rs.ac.uns.ftn.informatika.jpa.model.Document;
import rs.ac.uns.ftn.informatika.jpa.model.Driver;

public class RequestDriverDocumentDTO {

    private Long id;
    private String name;
    private String documentImage;

    public RequestDriverDocumentDTO() {
    }

    public RequestDriverDocumentDTO(Long id, String name, String documentImage) {
        this.id = id;
        this.name = name;
        this.documentImage = documentImage;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDocumentImage() {
        return documentImage;
    }

    public void setDocumentImage(String documentImage) {
        this.documentImage = documentImage;
    }

    public ResponseDriverDocumentDTO parseToResponse(Long id, Long driverId){
        ResponseDriverDocumentDTO driverDocumentResponse = new ResponseDriverDocumentDTO(id, this.name, this.documentImage, driverId);
        return driverDocumentResponse;
    }

    public Document parseToDocument(Driver driver){
        return new Document(this.name, this.documentImage, driver);
    }
}
