package siea.dev.sieaschatfilter.filter;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.Set;

public class WordFilter extends Filter {
    private final Set<String> words;

    public WordFilter(ConfigurationSection section) {
        words = new HashSet<>(section.getStringList("bad-words"));
    }

    @Override
    public boolean filter(String text, Player player) {
        for (String word : words) {
            if (text.contains(word)) {
                return true;
            }
        }
        return false;
    }
}