package siea.dev.sieaschatfilter.util;

import org.bukkit.configuration.ConfigurationSection;

public class ConfigUtil {
    public static ConfigurationSection getSectionSafe(ConfigurationSection section, String path) {
        if (section.isConfigurationSection(path)) {
            return section.getConfigurationSection(path);
        }
        return section.createSection(path);
    }
}