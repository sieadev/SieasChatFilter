package siea.dev.sieaschatfilter;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.plugin.java.JavaPlugin;
import siea.dev.sieaschatfilter.filter.LinkFilter;
import siea.dev.sieaschatfilter.filter.SpamFilter;
import siea.dev.sieaschatfilter.filter.WordFilter;
import siea.dev.sieaschatfilter.registry.MessageRegistry;
import siea.dev.sieaschatfilter.util.ConfigUtil;

public final class SieasChatFilter extends JavaPlugin {

    @Override
    public void onEnable() {
        saveDefaultConfig();
        ConfigurationSection config = ConfigUtil.getSectionSafe(getConfig(), "filters");

        MessageRegistry.init(ConfigUtil.getSectionSafe(getConfig(), "messages"));

        if (config.getBoolean("spam-filter")) {
            getServer().getPluginManager().registerEvents(new SpamFilter(this), this);
        }
        if (config.getBoolean("word-filter")) {
            getServer().getPluginManager().registerEvents(new WordFilter(getConfig()), this);
        }
        if (config.getBoolean("link-filter")) {
            getServer().getPluginManager().registerEvents(new LinkFilter(getConfig()), this);
        }
    }

    @Override
    public void onDisable() {

    }
}
