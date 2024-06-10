package features.config;

public class Env {
    private String url;
    private String connectionTimeout;
    private String remoteHosts;
    private String retry;

    public Env() {
    }

    public String getUrl() {
        return url;
    }

    public String getConnectionTimeout() {
        return connectionTimeout;
    }

    public String getRemoteHosts() {
        return remoteHosts;
    }

    public String getRetry() {
        return retry;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setConnectionTimeout(String connectionTimeout) {
        this.connectionTimeout = connectionTimeout;
    }

    public void setRemoteHosts(String remoteHosts) {
        this.remoteHosts = remoteHosts;
    }

    public void setRetry(String retry) {
        this.retry = retry;
    }
}
