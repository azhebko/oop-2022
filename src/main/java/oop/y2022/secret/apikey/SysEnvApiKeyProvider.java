package oop.y2022.secret.apikey;

public class SysEnvApiKeyProvider implements IApiKeyProvider {

    private final String apiKeyVarName;

    public SysEnvApiKeyProvider() {
        this("apiKey");
    }

    public SysEnvApiKeyProvider(String apiKeyVarName) {
        this.apiKeyVarName = apiKeyVarName;
    }

    @Override
    public String getApiKey() {
        return System.getenv(apiKeyVarName);
    }
}
