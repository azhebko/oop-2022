package oop.y2022.concurrency.bot;

public class Request {

    private final String botCode;
    private final byte[] payload;

    public Request(String botCode, byte[] payload) {
        this.botCode = botCode;
        this.payload = payload;
    }

    public String getBotCode() {
        return botCode;
    }

    public byte[] getPayload() {
        return payload;
    }
}
