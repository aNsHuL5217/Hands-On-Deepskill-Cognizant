package yashkumar;

public class MyService {

    private final ExternalAPI externalApi;

    public MyService(ExternalAPI externalApi) {
        this.externalApi = externalApi;
    }

    public String fetchData() {
        return externalApi.getData();
    }
}
