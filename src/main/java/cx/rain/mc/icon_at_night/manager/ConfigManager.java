package cx.rain.mc.icon_at_night.manager;

import cx.rain.mc.icon_at_night.IconAtNight;
import org.bukkit.configuration.file.FileConfiguration;

public class ConfigManager {
    private final FileConfiguration config;

    public ConfigManager(IconAtNight plugin) {
        plugin.saveDefaultConfig();

        config = plugin.getConfig();
    }

    public String getWorldName() {
        return config.getString("world-name", "world");
    }

    public String getIconDay() {
        return config.getString("icon-day", "server-icon-day.png");
    }

    public String getIconNight() {
        return config.getString("icon-night", "server-icon-night.png");
    }
}
