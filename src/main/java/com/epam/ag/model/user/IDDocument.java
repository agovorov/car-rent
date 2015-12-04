package com.epam.ag.model.user;

import com.epam.ag.model.BaseEntity;
import com.epam.ag.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Govorov Andrey
 */
public abstract class IDDocument extends BaseEntity {

    private static final Logger log = LoggerFactory.getLogger(IDDocument.class);

    private User owner;
    private String documentNumber;
    private Date dateOfIssue;
    private Date expirationDate;
    private String placeOfIssue;
    private Address livingAddress;

    public long getOwnerId() {
        return owner.getId();
    }

    public Date getIssueDate() {
        return dateOfIssue;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public String getIssuePlace() {
        return placeOfIssue;
    }

    public long getLivingAddressId() {
        return livingAddress.getId();
    }

    public void setOwner(User user) {
        owner = user;
    }

    public void setIssueDate(String date) {
        dateOfIssue = convertDate(date);
    }

    public void setExpirationDate(String date) {
        expirationDate = convertDate(date);
    }

    public void setIssuePlace(String place) {
        placeOfIssue = place;
    }

    public void setAddress(Address address) {
        livingAddress = address;
    }

    @Override
    public String toString() {
        return "IDDocument{" +
                "id=" + getId() +
                ", owner=" + owner +
                ", documentNumber=" + documentNumber +
                ", dateOfIssue=" + dateOfIssue +
                ", expirationDate=" + expirationDate +
                ", placeOfIssue='" + placeOfIssue + '\'' +
                ", livingAddress=" + livingAddress +
                '}';
    }

    // TODO Не место тут этой функции, но пока так...
    private Date convertDate(String stringDate) {
        SimpleDateFormat formater = new SimpleDateFormat("yyyy-mm-dd");
        Date date = null;
        try {
            date =  formater.parse(stringDate);
        } catch (ParseException e) {
            log.trace("Wrong date format: {} {}", date, stringDate);
            throw new RuntimeException("Wrong date to format", e);
        }
        return date;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public String getDocumentNumber() {
        return(documentNumber);
    }
}
