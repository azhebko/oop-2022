package oop.y2022.secret.apikey;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileApiKeyProvider implements IApiKeyProvider {

    private final String apiKey;

    public FileApiKeyProvider(String fileName) throws IOException {
        apiKey = new String(Files.readAllBytes(Path.of(fileName)));
    }

    @Override
    public String getApiKey() {
        return apiKey;
    }
}
