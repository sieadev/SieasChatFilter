package siea.dev.sieaschatfilter.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class ScfCommandTabCompleter implements org.bukkit.command.TabCompleter {

    @Override
    public java.util.List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, String[] args) {
        if (!sender.hasPermission("scf.admin")) {
            return java.util.Collections.emptyList();
        }
        if (args.length == 1) {
            return java.util.Arrays.asList("reload", "help", "version");
        }
        return java.util.Collections.emptyList();
    }
}
