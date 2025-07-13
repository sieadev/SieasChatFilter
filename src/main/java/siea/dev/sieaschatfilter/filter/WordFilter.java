package siea.dev.sieaschatfilter.filter;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.Set;

public class WordFilter extends Filter {
    private final Set<String> words;

    public WordFilter(ConfigurationSection section) {
        Set<String> temp = new HashSet<>(section.getStringList("bad-words"));
        words = new HashSet<>();
        for (String word : temp) {
            words.add(word.toLowerCase());
        }
    }

    @Override
    public boolean filter(String text, Player player) {
        for (String word : words) {
            if (text.toLowerCase().contains(word)) {
                return true;
            }
        }
        return false;
    }
}