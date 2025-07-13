package siea.dev.sieaschatfilter.registry;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class MessageRegistry {
    private static ConfigurationSection config;
    private static final Map<String, String> message = new HashMap<>();

    public static void init(ConfigurationSection config) {
        message.clear();
        MessageRegistry.config = config;
    }

    public static String getMessage(String key, Player player) {
        return message.computeIfAbsent(key, MessageRegistry::retrieveFromConfig).replace("%player%", player.getName());
    }

    private static String retrieveFromConfig(String key) {
        assert config != null;
        String message = config.getString(key);
        if (message == null) {
            message = key;
        }
        return message.replaceAll("&", "§");
    }
}
