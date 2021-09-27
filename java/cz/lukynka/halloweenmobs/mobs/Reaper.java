package cz.lukynka.halloweenmobs.mobs;

import cz.lukynka.halloweenmobs.Main;
import org.bukkit.*;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import cz.lukynka.halloweenmobs.Utils.chat;
import cz.lukynka.halloweenmobs.Utils.math;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.SkullMeta;

public class Reaper implements Listener {

    Main main;

    public Reaper(Main plugin) {
        this.main = plugin;
    }



    @EventHandler
    public void onEntitySpawn(EntitySpawnEvent event) {
        final int chance = this.main.ReaperSpawnChance;
        Entity entity = event.getEntity();
        World world = event.getEntity().getWorld();
        Location loc = event.getEntity().getLocation();
        int random = math.randomNumberInRange(1, 100);

        if(entity.getType() == EntityType.SKELETON) {
            if(random <= chance) {
                if(main.isDev) {
                    chat.broadcast("&a[DEBUG] &9&l[ENTITY_REAPER] &7" +random +"-" +chance +" | " +loc);
                }
                Skeleton reaper = (Skeleton) entity;

                ItemStack ghostplate = new ItemStack(Material.LEATHER_CHESTPLATE);
                LeatherArmorMeta gp = (LeatherArmorMeta) ghostplate.getItemMeta();
                gp.setColor(Color.fromRGB(18, 18, 18));
                gp.setDisplayName(chat.translated("&9&nReaper's Torso"));
                ghostplate.setItemMeta(gp);

                ItemStack skull = new ItemStack(Material.PLAYER_HEAD);
                SkullMeta sm = (SkullMeta) skull.getItemMeta();
                sm.setOwner("Deathhhh");
                int calliSecret = math.randomNumberInRange(1, 1000);
                if(main.isDev) {
                    calliSecret = math.randomNumberInRange(1, 5);
                }
                if(calliSecret == 1) {
                    sm.setOwner("moricalliopeEN");
                }
                sm.setDisplayName(chat.translated("&9&nReaper's Head"));
                skull.setItemMeta(sm);

                ItemStack scythe = new ItemStack(Material.IRON_HOE);
                ItemMeta scm = scythe.getItemMeta();
                scm.setDisplayName(chat.translated("&9&nReaper's Scythe"));
                scythe.setItemMeta(scm);


                reaper.setInvisible(true);
                reaper.setSilent(true);
                reaper.addScoreboardTag("Reaper");

                reaper.getEquipment().setChestplate(ghostplate);
                reaper.getEquipment().setHelmet(skull);
                reaper.getEquipment().setItemInMainHand(scythe);
                world.playSound(loc, Sound.ENTITY_WITHER_SKELETON_AMBIENT, 2, 0);


            }
        }

    }




}
