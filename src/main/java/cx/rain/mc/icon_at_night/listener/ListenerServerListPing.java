package cx.rain.mc.icon_at_night.listener;

import cx.rain.mc.icon_at_night.IconAtNight;
import cx.rain.mc.icon_at_night.manager.ConfigManager;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;
import org.bukkit.util.CachedServerIcon;

import java.io.File;
import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;

public class ListenerServerListPing implements Listener {
    private CachedServerIcon iconDay;
    private CachedServerIcon iconNight;

    private final ConfigManager configManager;

    private Map<InetAddress, Boolean> lastRequest = new HashMap<>();

    public ListenerServerListPing(IconAtNight plugin) {
        configManager = IconAtNight.getInstance().getConfigManager();

        var dataFolder = plugin.getDataFolder();
        var iconFileDay = new File(dataFolder, configManager.getIconDay());
        var iconFileNight = new File(dataFolder, configManager.getIconNight());
        try {
            iconDay = Bukkit.loadServerIcon(iconFileDay);
            iconNight = Bukkit.loadServerIcon(iconFileNight);
        } catch (Exception ex) {
            ex.printStackTrace();
            iconDay = Bukkit.getServerIcon();
            iconNight = Bukkit.getServerIcon();
        }
    }

    @EventHandler
    public void onServerListPing(ServerListPingEvent event) {
        var world = Bukkit.getWorld(configManager.getWorldName());

        boolean isNight = world != null && world.getTime() > 13000;

        if (configManager.getCacheEnabled() && lastRequest.containsKey(event.getAddress())) {
            var lastReq = lastRequest.get(event.getAddress());
            if (lastReq == isNight) {
                return;
            }
        }

        if (isNight) {
            event.setServerIcon(iconNight);
        } else {
            event.setServerIcon(iconDay);
        }
        lastRequest.put(event.getAddress(), isNight);
    }
}
