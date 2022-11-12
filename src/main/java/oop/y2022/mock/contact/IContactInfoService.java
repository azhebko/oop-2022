package oop.y2022.mock.contact;

import java.util.Optional;
import java.util.UUID;

public interface IContactInfoService {

    Optional<ContactInfo> findContactInfo(UUID userId, ContactType contactType);
}
