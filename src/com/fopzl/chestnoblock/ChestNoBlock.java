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
            // ops can place on chests
            return;
        }
        
        if((isOpaque(b) && b.getRelative(0, -1, 0).getBlockData() instanceof Chest) || // stop opaque blocks from being placed on top
           (b.getBlockData() instanceof Chest && isOpaque(b.getRelative(0, 1, 0)))){ // stop chests from being placed below opaque blocks
            event.setBuild(true);
            event.setCancelled(true);
        }
    }
        
    // TODO: handle pistons
    
    private boolean isOpaque(Block block){
        // TODO
        return !(block.isEmpty() || block.isLiquid());
    }
}