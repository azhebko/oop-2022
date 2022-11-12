package oop.y2022.mock.contact;

public class ContactInfo {

    private final String email;
    private final ContactType contactType;

    public ContactInfo(String email, ContactType contactType) {
        this.email = email;
        this.contactType = contactType;
    }

    public String getEmail() {
        return email;
    }

    public ContactType getContactType() {
        return contactType;
    }
}
