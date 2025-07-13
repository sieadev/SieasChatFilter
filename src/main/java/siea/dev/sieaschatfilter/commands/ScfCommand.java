package siea.dev.sieaschatfilter.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import siea.dev.sieaschatfilter.SieasChatFilter;

public class ScfCommand implements CommandExecutor {
    private final SieasChatFilter sieasChatFilter;

    public ScfCommand(SieasChatFilter sieasChatFilter) {
        this.sieasChatFilter = sieasChatFilter;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender,@NotNull Command cmd,@NotNull String label, String[] args) {
        if (!sender.hasPermission("scf.admin")) {
            sender.sendMessage("§cYou do not have permission to use this command.");
            return true;
        }

        if (args.length == 0) {
            sender.sendMessage("§cUsage: /scf <reload|help|version>");
            return true;
        }

        switch (args[0].toLowerCase()) {
            case "reload":
                sieasChatFilter.reload();
                sender.sendMessage("§aConfiguration reloaded successfully.");
                break;
            case "help":
                sender.sendMessage("§aAvailable commands: /scf reload, /scf help, /scf version");
                break;
            case "version":
                sender.sendMessage("§aSieasChatFilter version: " + sieasChatFilter.getDescription().getVersion());
                break;
            default:
                sender.sendMessage("§cUnknown command. Use /scf help for a list of commands.");
                break;
        }
        return true;
    }
}
