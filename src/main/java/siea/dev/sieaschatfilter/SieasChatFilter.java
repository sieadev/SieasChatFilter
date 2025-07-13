package siea.dev.sieaschatfilter;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;
import siea.dev.sieaschatfilter.commands.ScfCommand;
import siea.dev.sieaschatfilter.commands.ScfCommandTabCompleter;
import siea.dev.sieaschatfilter.filter.Filter;
import siea.dev.sieaschatfilter.filter.LinkFilter;
import siea.dev.sieaschatfilter.filter.SpamFilter;
import siea.dev.sieaschatfilter.filter.WordFilter;
import siea.dev.sieaschatfilter.registry.MessageRegistry;
import siea.dev.sieaschatfilter.util.ConfigUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class SieasChatFilter extends JavaPlugin implements Listener {
    private final List<Filter> filters = new ArrayList<>();

    @Override
    public void onEnable() {
        saveDefaultConfig();

        reload();

        Objects.requireNonNull(getCommand("sieaschatfilter")).setExecutor(new ScfCommand(this));
        Objects.requireNonNull(getCommand("sieaschatfilter")).setTabCompleter(new ScfCommandTabCompleter());

        getServer().getPluginManager().registerEvents(this, this);
    }

    @Override
    public void onDisable() {
    }

    public void reload() {
        filters.clear();

        reloadConfig();

        ConfigurationSection config = ConfigUtil.getSectionSafe(getConfig(), "filters");

        MessageRegistry.init(ConfigUtil.getSectionSafe(getConfig(), "messages"));

        if (config.getBoolean("spam-filter")) {
            filters.add(new SpamFilter(this));
        }
        if (config.getBoolean("word-filter")) {
            filters.add(new WordFilter(getConfig()));
        }
        if (config.getBoolean("link-filter")) {
            filters.add(new LinkFilter(getConfig()));
        }
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onPlayerChat(AsyncPlayerChatEvent e) {
        for (Filter filter : filters) {
            filter.onPlayerChat(e);
            if (e.isCancelled()) {
                return;
            }
        }
    }
}
