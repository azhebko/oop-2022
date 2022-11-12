package oop.y2022.secret.apikey;

public class ArgsApiKeyProvider implements IApiKeyProvider {

    private final String[] args;

    public ArgsApiKeyProvider(String[] args) {
        this.args = args;
    }

    @Override
    public String getApiKey() {
        throw new UnsupportedOperationException("No getApiKey implementation yet");
    }
}
