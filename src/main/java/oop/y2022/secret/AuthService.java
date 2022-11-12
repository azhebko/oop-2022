package oop.y2022.secret;

import oop.y2022.secret.apikey.IApiKeyProvider;

public class AuthService implements IAuthService {

    private final IApiKeyProvider apiKeyProvider;

    public AuthService(IApiKeyProvider apiKeyProvider) {
        this.apiKeyProvider = apiKeyProvider;
    }

    @Override
    public boolean check(String token) {
        return apiKeyProvider.getApiKey().equals(token);
    }
}

