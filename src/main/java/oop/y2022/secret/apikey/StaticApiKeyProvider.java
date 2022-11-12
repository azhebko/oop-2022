package oop.y2022.secret.apikey;

public class StaticApiKeyProvider implements IApiKeyProvider {

    private final String apiKey;

    public StaticApiKeyProvider(String apiKey) {
        this.apiKey = apiKey;
    }

    @Override
    public String getApiKey() {
        return apiKey;
    }
}
