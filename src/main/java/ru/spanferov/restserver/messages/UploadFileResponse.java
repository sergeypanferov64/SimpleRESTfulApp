package ru.spanferov.restserver.messages;

public class UploadFileResponse {
    
    private String innerUri;
    
    public UploadFileResponse(String innerUri){
        this.innerUri = innerUri;
    }

    public String getUri() {
        return innerUri;
    }
    
}
