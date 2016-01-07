package com.epam.ag.model.user;

/**
 * @author Govorov Andrey
 */
public class Passport extends IDDocument {

    public Passport() {
        super();
    }

    public Passport(Long passportId) {
        super(passportId);
    }

    @Override
    public String toString() {
        return "Passport{" +
                "id=" + getId() +
                "passportSeries='" + passportSeries + '\'' +
//                ", ownerID=" + owner.getId() +
                ", documentNumber=" + documentNumber +
                ", dateOfIssue=" + dateOfIssue +
                ", expirationDate=" + expirationDate +
                ", placeOfIssue='" + placeOfIssue + '\'' +
                ", livingAddress=" + livingAddress +
                '}';
    }
}
