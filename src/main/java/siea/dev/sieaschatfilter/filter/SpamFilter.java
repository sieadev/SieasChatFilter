package siea.dev.sieaschatfilter.filter;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class SpamFilter extends Filter {
    private final Map<Player, LinkedHashMap<String, MessageInfo>> playerMessages = new HashMap<>();
    private static final long CLEANUP_INTERVAL = 60000;
    private final int spamCooldown;
    private final int maxSimilarMessages;

    public SpamFilter(Plugin plugin) {
        ConfigurationSection configurationSection = plugin.getConfig();
        spamCooldown = configurationSection.getInt("spam-cooldown") * 1000;
        maxSimilarMessages = configurationSection.getInt("max-similar-messages");

        new BukkitRunnable() {
            @Override
            public void run() {
                cleanupOldMessages(System.currentTimeMillis());
            }
        }.runTaskTimer(plugin, CLEANUP_INTERVAL, CLEANUP_INTERVAL);
    }

    @Override
    public boolean filter(String text, Player player) {
        long currentTime = System.currentTimeMillis();
        LinkedHashMap<String, MessageInfo> messages = playerMessages.computeIfAbsent(player, k -> new LinkedHashMap<>());

        MessageInfo messageInfo = messages.getOrDefault(text, new MessageInfo(0, currentTime));
        if (currentTime - messageInfo.timestamp < spamCooldown) {
            messageInfo.count++;
            if (messageInfo.count > maxSimilarMessages) {
                messageInfo.timestamp = currentTime;
                messages.put(text, messageInfo);
                return true;
            }
        } else {
            messageInfo.count = 1;
            messageInfo.timestamp = currentTime;
        }

        messages.put(text, messageInfo);
        return false;
    }

    private void cleanupOldMessages(long currentTime) {
        for (Map.Entry<Player, LinkedHashMap<String, MessageInfo>> playerEntry : playerMessages.entrySet()) {
            LinkedHashMap<String, MessageInfo> messages = playerEntry.getValue();
            messages.entrySet().removeIf(entry -> currentTime - entry.getValue().timestamp > spamCooldown);
        }
        playerMessages.entrySet().removeIf(entry -> entry.getValue().isEmpty());
    }

    private static class MessageInfo {
        int count;
        long timestamp;
        MessageInfo(int count, long timestamp) {
            this.count = count;
            this.timestamp = timestamp;
        }
    }
}