package features.config;

import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ConfigReader {

    public static final Path configPath = Paths.get(System.getProperty("user.dir") + "/src/test/java/features/config/config.yml");

    private static ConfigReader ConfigReader;

    Config config;

    /**
     * Get instance of ConfigReader
     *
     * @return
     * @throws FileNotFoundException
     */
    public static ConfigReader getInstance() throws FileNotFoundException {
        return getInstance(configPath);
    }

    /**
     * Get instance of ConfigReader
     *
     * @param configPath
     * @return
     * @throws FileNotFoundException
     */
    public static ConfigReader getInstance(Path configPath) throws FileNotFoundException {
        if (ConfigReader == null) {
            ConfigReader = new ConfigReader(configPath);
        }
        return ConfigReader;
    }

    /**
     * Constructor
     *
     * @param configPath
     * @throws FileNotFoundException
     */
    private ConfigReader(Path configPath) throws FileNotFoundException {
        this.config = loadConfig(configPath);
    }

    /**
     * Load config.yml
     *
     * @param configPath
     * @throws FileNotFoundException
     */
    public Config loadConfig(Path configPath) throws FileNotFoundException {
        Constructor constructor = new Constructor(Config.class);
        Yaml yaml = new Yaml(constructor);
        return yaml.load(new FileInputStream(configPath.toFile()));
    }

    /**
     * Dump config to config.yml
     *
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     * @throws IOException
     */
    public void dumpConfig() throws IllegalArgumentException, IllegalAccessException, IOException {
        dumpConfig(this.config, this.configPath);
    }

    /**
     * Dump config to config.yml
     *
     * @param configPath
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     * @throws IOException
     */
    public void dumpConfig(Config config, Path configPath) throws IllegalArgumentException, IllegalAccessException, IOException {
        DumperOptions options = new DumperOptions();
        options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
        options.setPrettyFlow(true);
        Yaml yml = new Yaml(options);
        yml.dump(config, new FileWriter(configPath.toFile()));
    }

    /**
     * Get config object
     *
     * @return
     */
    public Config getConfig() {
        return this.config;
    }

    /**
     * Get session mapping object by session name
     *
     * @param env
     * @return
     */
    public Env getEnv(String env) {
        return this.config.getEnvs().get(env);
    }

}
