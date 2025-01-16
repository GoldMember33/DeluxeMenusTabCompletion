package com.github.goldmember33.spigot;

import com.extendedclip.deluxemenus.DeluxeMenus;
import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.*;

public final class DeluxeMenusTab extends JavaPlugin {

    private static DeluxeMenusTab instance;
    private DeluxeMenus deluxeMenus;

    private ConsoleCommandSender cs;

    private ArrayList<String> menuIds; // From DeluxeMenus plugin

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;
        cs = Bukkit.getConsoleSender();

        Plugin deluxeMenusPlugin = getServer().getPluginManager().getPlugin("DeluxeMenus");
        if (deluxeMenusPlugin != null) {
            deluxeMenus = (DeluxeMenus) deluxeMenusPlugin;
            cs.sendMessage("DeluxeMenus plugin detected!");
        }

        menuIds = retrieveMenuIdsFromConfig();

        Bukkit.getPluginManager().registerEvents(new TabListener(this), this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static DeluxeMenusTab getInstance() {
        return instance;
    }

    public DeluxeMenus getDeluxeMenus() {
        return deluxeMenus;
    }

    public ArrayList<String> retrieveMenuIdsFromConfig() {
        File deluxeMenusConfigFile = new File(deluxeMenus.getDataFolder(), "config.yml");
        if (!deluxeMenusConfigFile.exists()) {
            return new ArrayList<>();
        }

        YamlConfiguration config = YamlConfiguration.loadConfiguration(deluxeMenusConfigFile);
        Set<String> keySet = Objects.requireNonNull(config.getConfigurationSection("gui_menus")).getKeys(false);
        return new ArrayList<>(keySet);
    }
}
