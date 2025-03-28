package siea.dev.sieaschatfilter.filter;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LinkFilter extends Filter {
    private final Pattern LINK_PATTERN;

    public LinkFilter(ConfigurationSection config) {
        String patternString = config.getString("link-pattern", "[A-Za-z0-9-]{2,63}\\.[A-Za-z0-9-]{2,63}");
        LINK_PATTERN = Pattern.compile(patternString);
    }

    @Override
    public boolean filter(String text, Player player) {
        Matcher matcher = LINK_PATTERN.matcher(text);
        return matcher.find();
    }
}
