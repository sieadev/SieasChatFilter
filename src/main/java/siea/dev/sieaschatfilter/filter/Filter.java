package siea.dev.sieaschatfilter.filter;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import siea.dev.sieaschatfilter.registry.MessageRegistry;

public abstract class Filter implements Listener {
    @EventHandler (priority = EventPriority.LOWEST)
    public final void onPlayerChat(AsyncPlayerChatEvent e) {
        Player player = e.getPlayer();
        if (e.isCancelled()) return;
        if (player.hasPermission("scf.bypass")) {
            return;
        }
        if (filter(e.getMessage(), player)) {
            e.setCancelled(true);
            player.sendMessage(MessageRegistry.getMessage("chat_message_blocked", player).replace("%message%", e.getMessage()));
        }
    }

    public abstract boolean filter(String text, Player player);
}
