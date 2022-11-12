package oop.y2022.secret;

import java.io.IOException;
import oop.y2022.secret.apikey.ArgsApiKeyProvider;
import oop.y2022.secret.apikey.FileApiKeyProvider;
import oop.y2022.secret.apikey.IApiKeyProvider;
import oop.y2022.secret.apikey.StaticApiKeyProvider;
import oop.y2022.secret.apikey.SysEnvApiKeyProvider;

public class Main {

    public static void main(String[] args) throws IOException {

        // ключ доступа размещен в переменных окружения по имени
        // apiKey (можно конфигурировать перегруженным конструктором)
        IApiKeyProvider sysEnvApiKeyProvider = new SysEnvApiKeyProvider();

        // ключ доступа передан в аргументах командной строки.
        // формат аргументов может быть произвольным:
        //  * единственный параметр в списке со значением
        // java oop.y2022.secret.auth.Main 123456
        // * либо набор значений с ключами
        // java oop.y2022.secret.auth.Main -apikey 123456
        //  разбор фактических параметров сокрыт в ArgsApiKeyProvider
        IApiKeyProvider argsApiKeyProvider = new ArgsApiKeyProvider(args);

        // ключ доступа размещен в файле: указываем путь до файла с секретом,
        // формат данных в файле может быть любым
        IApiKeyProvider fileApiKeyProvider = new FileApiKeyProvider("C:\\secret_path\\secret_file.txt");

        // передача статического значения: поддерживает полиморфное поведение
        // AuthService, однако НЕ РЕШАЕТ задачу сокрытия секрета.
        // Задача может быть решена при доставке секрета из стороннего источника и передачи значения
        // в StaticApiKeyProvider, но лучше реализовать соответствующий IApiKeyProvider.
        IApiKeyProvider staticApiKeyProvider = new StaticApiKeyProvider("123456");

        AuthService authService = new AuthService(sysEnvApiKeyProvider);

        // выводим результат проверки, авторизуем ли запрос с ключом 123456? true
        System.out.println(authService.check("123456"));

        // выводим результат проверки, авторизуем ли запрос с ключом 123457? false
        System.out.println(authService.check("123457"));
    }
}
