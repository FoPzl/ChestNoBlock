package com.fopzl.chestnoblock;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.block.Block;
import org.bukkit.Location;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.Chest;
import org.bukkit.entity.Player;

public class ChestNoBlock extends JavaPlugin implements Listener {
    public void onEnable(){
        super.onEnable();
        Bukkit.getPluginManager().registerEvents(this, this);
    }
    
    @EventHandler(ignoreCancelled = true)
    public void onBlockPlace(BlockPlaceEvent event){
        Block b = event.getBlockPlaced();
        
        if(event.getPlayer().isOp()){
            return;
        }
        
        if(!(b.getRelative(0, -1, 0).getBlockData() instanceof Chest)){
            return;
        }
        
        event.setBuild(true);
        event.setCancelled(true);
    }
}