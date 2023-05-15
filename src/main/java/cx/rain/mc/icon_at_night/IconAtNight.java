package cx.rain.mc.icon_at_night;

import cx.rain.mc.icon_at_night.listener.ListenerServerListPing;
import cx.rain.mc.icon_at_night.manager.ConfigManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class IconAtNight extends JavaPlugin {
    private static IconAtNight INSTANCE;

    private ConfigManager configManager;

    public IconAtNight() {
        INSTANCE = this;
    }

    public static IconAtNight getInstance() {
        return INSTANCE;
    }

    public ConfigManager getConfigManager() {
        return configManager;
    }

    @Override
    public void onEnable() {
        configManager = new ConfigManager(this);

        getServer().getPluginManager().registerEvents(new ListenerServerListPing(this), this);
    }

    @Override
    public void onDisable() {
    }
}
