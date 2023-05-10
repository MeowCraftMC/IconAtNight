package cx.rain.mc.icon_at_night.listener;

import cx.rain.mc.icon_at_night.IconAtNight;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;
import org.bukkit.util.CachedServerIcon;

import java.io.File;

public class ListenerServerListPing implements Listener {
    public final CachedServerIcon iconDay;
    public final CachedServerIcon iconNight;

    public ListenerServerListPing(IconAtNight plugin) {
        var dataFolder = plugin.getDataFolder();
        var iconFileDay = new File(dataFolder, "server-icon-day.png");
        var iconFileNight = new File(dataFolder, "server-icon-night.png");
        try {
            iconDay = Bukkit.loadServerIcon(iconFileDay);
            iconNight = Bukkit.loadServerIcon(iconFileNight);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @EventHandler
    public void onServerListPing(ServerListPingEvent event) {
        var world = Bukkit.getWorld("world");
        if (world != null && world.getTime() > 13000) {
            event.setServerIcon(iconNight);
        } else {
            event.setServerIcon(iconDay);
        }
    }
}
