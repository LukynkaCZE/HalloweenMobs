package cz.lukynka.halloweenmobs;

import cz.lukynka.halloweenmobs.Sounds.Damage;
import cz.lukynka.halloweenmobs.Utils.chat;
import cz.lukynka.halloweenmobs.mobs.Ghost;
import cz.lukynka.halloweenmobs.mobs.HeadlessHorseman;
import cz.lukynka.halloweenmobs.mobs.Reaper;
import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public final class Main extends JavaPlugin {

    public final String pluginVer = this.getDescription().getVersion();
    public final String system = Bukkit.getServer().getName();
    public final String author = "LukynkaCZE";
    public final String prefix = chat.translated("&8[&6Halloween Mobs&8] ");

    @Override
    public void onEnable() {
        // Plugin startup logic
        createFiles();
        chat.sendToConsole("&r ");
        chat.sendToConsole(prefix +"&aPlugin Loaded!");
        chat.sendToConsole(prefix +"&7Running version &b " +pluginVer +"&7 on &e" +system);
        chat.sendToConsole(prefix +"&7Author: &dLukynkaCZE &7| &bwww.lukynka.cz");
        chat.sendToConsole("&r ");
        if(isDev) {
            chat.sendToConsole(prefix +"&7You are running &4Developer Version&7!");
            chat.sendToConsole(prefix +"&7Please switch to &bStable Release&7 as soon as possible!");
            chat.sendToConsole("&r ");
        }



        Bukkit.getServer().getPluginManager().registerEvents(new Ghost(this), this);
        Bukkit.getServer().getPluginManager().registerEvents(new Reaper(this), this);
        Bukkit.getServer().getPluginManager().registerEvents(new HeadlessHorseman(this), this);

        Bukkit.getServer().getPluginManager().registerEvents(new Damage(this), this);

    }


    //CONFIG SHIT IDK ITS 3AM
    public final boolean isDev = getConfig().getBoolean("Developer Mode");
    public final int GhostSpawnChance = getConfig().getInt("Ghost Spawn Chance");
    public final int ReaperSpawnChance = getConfig().getInt("Reaper Spawn Chance");
    public final int HorsemanSpawnChance = getConfig().getInt("Headless Horseman Spawn Chance");

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        chat.sendToConsole("&r ");
        chat.sendToConsole(prefix +"&7See you next time!");
        chat.sendToConsole("&r ");
    }




    private File configf;
    private FileConfiguration config;

    private void createFiles() {
        configf = new File(getDataFolder(), "config.yml");

        if(!(configf.exists())) {
            configf.getParentFile().mkdir();
            saveResource("config.yml", false);
        }
        config = new YamlConfiguration();
        try {
            config.load(configf);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

}
