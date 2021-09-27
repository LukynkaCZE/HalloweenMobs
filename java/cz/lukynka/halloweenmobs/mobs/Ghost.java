package cz.lukynka.halloweenmobs.mobs;

import cz.lukynka.halloweenmobs.Main;
import org.bukkit.*;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import cz.lukynka.halloweenmobs.Utils.chat;
import cz.lukynka.halloweenmobs.Utils.math;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.SkullMeta;

public class Ghost implements Listener {

    Main main;


    public Ghost(Main plugin) {
        this.main = plugin;
    }





    @EventHandler
    public void onEntitySpawn(EntitySpawnEvent event) {
        final int chance = this.main.GhostSpawnChance;
        Entity entity = event.getEntity();
        World world = event.getEntity().getWorld();
        Location loc = event.getEntity().getLocation();
        int random = math.randomNumberInRange(1, 100);

        if(entity.getType() == EntityType.ZOMBIE) {
            if(random <= chance) {
                if(main.isDev) {
                    chat.broadcast("&a[DEBUG] &e&l[ENTITY_GHOST] &7" +random +"-" +chance +" | " +loc);
                }
                Zombie ghost = (Zombie) entity;

                ItemStack ghostplate = new ItemStack(Material.LEATHER_CHESTPLATE);
                LeatherArmorMeta gp = (LeatherArmorMeta) ghostplate.getItemMeta();
                gp.setColor(Color.fromRGB(227, 227, 227));
                gp.setDisplayName(chat.translated("&f&nGhost's Torso"));
                ghostplate.setItemMeta(gp);

                ItemStack skull = new ItemStack(Material.PLAYER_HEAD);
                SkullMeta sm = (SkullMeta) skull.getItemMeta();
                sm.setOwner("LattyGhost");
                sm.setDisplayName(chat.translated("&f&nGhost's Head"));
                skull.setItemMeta(sm);



                ghost.setInvisible(true);
                ghost.setSilent(true);
                ghost.addScoreboardTag("Ghost");

                ghost.getEquipment().setChestplate(ghostplate);
                ghost.getEquipment().setHelmet(skull);
                if(ghost.isBaby()) {
                    world.playSound(loc, Sound.ENTITY_ELDER_GUARDIAN_HURT, 2, 1);
                } else {
                    world.playSound(loc, Sound.ENTITY_ELDER_GUARDIAN_HURT, 2, 0);
                }




            }
        }

    }




}
