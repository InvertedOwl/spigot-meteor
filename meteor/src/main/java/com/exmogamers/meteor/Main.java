package com.exmogamers.meteor;

import jdk.nashorn.internal.runtime.linker.JavaAdapterFactory;
import net.minecraft.server.v1_16_R3.EntityTNTPrimed;
import net.minecraft.server.v1_16_R3.Position;
import org.bukkit.*;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.TNT;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

import java.util.Random;

public class Main extends JavaPlugin {
    public static Main mainInstance;
    public static Main getInstance() {
        return mainInstance;
    }


    @Override
    public void onLoad() {
        mainInstance = this;
    }

    @Override
    public void onEnable() {

        this.getCommand("togglemeteors").setExecutor(new Commands());
        this.getCommand("frequency").setExecutor(new Commands());
        this.getCommand("radius").setExecutor(new Commands());
        this.getCommand("impact").setExecutor(new Commands());

        super.saveDefaultConfig();
        Configuration config = Main.getInstance().getConfig();
        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
            public void run() {
                for (Player player : Bukkit.getOnlinePlayers()){
                    if(config.getBoolean("meteors-on")){
                        for(int i = 0; i < config.getInt("frequency"); i++){
                            int Radius = config.getInt("radius");
                            Random random = new Random();
                            int randomIntX = random.nextInt((Radius * 2)) + (Radius * -1);
                            int randomIntZ = random.nextInt((Radius * 2)) + (Radius * -1);
                            Location tntLoc = new Location(player.getWorld(), player.getLocation().getX() + randomIntX, 150, player.getLocation().getZ() + randomIntZ);

                            TNTPrimed tnt = (TNTPrimed) player.getWorld().spawnEntity(tntLoc, EntityType.PRIMED_TNT);
                            tnt.setFuseTicks(10000);

                        }
                    }
                    for(Entity entity : player.getWorld().getEntities()){
                        if(entity.getType() == EntityType.PRIMED_TNT){
                            TNTPrimed tnt = (TNTPrimed) entity;
                            Location loc = new Location(player.getWorld(),  tnt.getLocation().getX(), tnt.getLocation().getY() - 1, tnt.getLocation().getZ());
                            if(tnt.isOnGround()){
                                tnt.getWorld().createExplosion(tnt.getLocation(), config.getInt("impact"));
                                tnt.remove();

                            }
                        }
                    }
                }
            }
        }, 0, 10);

    }
    @Override
    public void onDisable() {

    }
}
