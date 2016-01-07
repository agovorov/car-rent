package com.epam.ag.model.user;

import com.epam.ag.model.BaseEntity;
import com.epam.ag.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * @author Govorov Andrey
 */
public abstract class IDDocument extends BaseEntity {

    private static final Logger log = LoggerFactory.getLogger(IDDocument.class);

    protected User owner;
    protected String documentNumber;
    protected Date dateOfIssue;
    protected Date expirationDate;
    protected String placeOfIssue;
    protected Address livingAddress;
    protected String passportSeries;

    public IDDocument() {
        super();
    }

    public IDDocument(Long documentId) {
        super(documentId);
    }

    public Address getLivingAddress() {
        return livingAddress;
    }

    public Long getOwnerId() {
        return owner == null ? null : owner.getId();
    }

    public Date getIssueDate() {
        return dateOfIssue;
    }

    public void setIssueDate(Date date) {
        dateOfIssue = date;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date date) {
        expirationDate = date;
    }

    public String getIssuePlace() {
        return placeOfIssue;
    }

    public void setIssuePlace(String place) {
        placeOfIssue = place;
    }

    public Long getLivingAddressId() {
        return livingAddress == null ? null : livingAddress.getId();
    }

    public void setOwner(User user) {
        owner = user;
    }

    public void setAddress(Address address) {
        livingAddress = address;
    }

    public void setSeries(String series) {
        passportSeries = series;
    }

    public String getSeries() {
        return passportSeries;
    }

    @Override
    public String toString() {
        return "IDDocument{" +
                "id=" + getId() +
//                ", ownerID=" + owner.getId() +
                ", documentNumber=" + documentNumber +
                ", dateOfIssue=" + dateOfIssue +
                ", expirationDate=" + expirationDate +
                ", placeOfIssue='" + placeOfIssue + '\'' +
                ", livingAddress=" + livingAddress +
                '}';
    }

    public String getDocumentNumber() {
        return (documentNumber);
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

}
