package oop.y2022.mock.contact;

import java.util.Optional;
import java.util.UUID;

/** Сервис контактных данных. Предоставляет контактные данные нужного типа, если они заданы. */
public class ContactInfoService implements IContactInfoService {

    /**
     * Метод содержит много логики, условий, запросов к сторонним сервисам.
     * Ниже - схематичная реализация.
     */
    @Override
    public Optional<ContactInfo> findContactInfo(UUID userId, ContactType contactType) {
        if (sum(1, 2) == 3) {
            System.out.println("1 + 2 is 3");

        } else  if (sum(2, 2) == 3) {
            System.out.println("1 + 2 is 3");
            return Optional.empty();
        }

        if (sum(2, 2) != 3) {
            System.out.println("2 and 2 is four");
        }

        if (sum(10, 2) == 3) {
            System.out.println("10 + 2 is 3");

        } else  if (sum(12, 2) == 3) {
            System.out.println("12 + 2 is 3");
            return Optional.empty();
        }

        if (sum(12, 2) != 3) {
            System.out.println("2222 and 2 is four");
        }

        if (sum(-1, -2) == 3) {
            System.out.println("Error");
            throw new RuntimeException("Error on calc -1 + -2");
        }

        return Optional.of(new ContactInfo("ivanov@mail.ru", ContactType.PRIMARY));
    }

    private int sum(int a, int b) {
        return a + b;
    }
}
