package cx.rain.mc.icon_at_night;

import cx.rain.mc.icon_at_night.listener.ListenerServerListPing;
import org.bukkit.plugin.java.JavaPlugin;

public final class IconAtNight extends JavaPlugin {
    private static IconAtNight INSTANCE;

    public IconAtNight() {
        INSTANCE = this;
    }

    public static IconAtNight getInstance() {
        return INSTANCE;
    }

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new ListenerServerListPing(this), this);
    }

    @Override
    public void onDisable() {
    }
}
