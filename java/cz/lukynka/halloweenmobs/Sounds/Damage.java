package cz.lukynka.halloweenmobs.Sounds;

import cz.lukynka.halloweenmobs.Main;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;

public class Damage implements Listener {

    Main main;

    public Damage(Main plugin) {
        main = plugin;
    }

    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {
        Entity entity = event.getEntity();
        Location loc = entity.getLocation();
        World world = entity.getWorld();
        EntityType type = entity.getType();

        if(type == EntityType.ZOMBIE) {
            if(entity.getScoreboardTags().contains("Ghost")) {
                world.playSound(loc, Sound.ENTITY_ELDER_GUARDIAN_HURT, 1, 0);
            }
        }

        if(type == EntityType.SKELETON) {
            if(entity.getScoreboardTags().contains("Reaper")) {
                world.playSound(loc, Sound.ENTITY_WITHER_SKELETON_HURT, 1, 0);
            } else if (entity.getScoreboardTags().contains("Horseman")) {
                if(event.getCause() == EntityDamageEvent.DamageCause.FIRE_TICK) {
                    event.setCancelled(true);
                    entity.setVisualFire(false);
                    entity.setFireTicks(-1);
                } else {
                    world.playSound(loc, Sound.ENTITY_SKELETON_HURT, 1, 0);
                }
            }
        }

    }




    @EventHandler
    public void onEntityDeath(EntityDeathEvent event) {
        Entity entity = event.getEntity();
        Location loc = entity.getLocation();
        World world = entity.getWorld();
        EntityType type = entity.getType();

        if(type == EntityType.ZOMBIE) {
            if(entity.getScoreboardTags().contains("Ghost")) {
                world.playSound(loc, Sound.ENTITY_ELDER_GUARDIAN_DEATH, 1, 0);
            }
        }

        if(type == EntityType.SKELETON) {
            if(entity.getScoreboardTags().contains("Reaper")) {
                world.playSound(loc, Sound.ENTITY_WITHER_SKELETON_DEATH, 1, 0);
            }
            if(entity.getScoreboardTags().contains("Horseman")) {
                world.playSound(loc, Sound.ENTITY_SKELETON_DEATH, 1, 0);
            }
        }
    }


}
