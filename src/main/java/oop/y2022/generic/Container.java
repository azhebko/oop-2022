package oop.y2022.generic;

/**
 * Контейнер, позволяет сохранить объект и получить его по требованию.
 * Контейнер многоцелевой, можно использовать с объектами любых типов.
 * Container - обобщенный тип.
 * TYPE - параметр типа, имя типа произвольно.
 */
public class Container<TYPE> {

    // деталь реализации, объект хранится в поле класса
    private TYPE value;

    /** Запись объекта. Тип передаваемого объекта согласуется с типом чтения через параметр типа TYPE. */
    void setValue(TYPE value) {
        this.value = value;
    }

    /** Чтение объекта. Тип возвращаемого объекта согласуется с типом записи через параметр типа TYPE. */
    TYPE getValue() {
        return value;
    }
}
