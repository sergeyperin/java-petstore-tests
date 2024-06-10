package features.config;

import java.util.HashMap;
import java.util.Map;

public class Config {
    private String username;
    private String password;
    private Map<String, Env> envs = new HashMap<>();

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Map<String, Env> getEnvs() {
        return envs;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEnvs(Map<String, Env> envs) {
        this.envs = envs;
    }
}
