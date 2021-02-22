package com.fopzl.chestnoblock;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.Chest;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.block.BlockPistonExtendEvent;
import org.bukkit.event.block.BlockPistonRetractEvent;

import org.bukkit.Material;

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
            event.setCancelled(true);
        }
    }
    
    @EventHandler(ignoreCancelled = true)
    public void onBlockPistonExtend(BlockPistonExtendEvent event){
        int modX = event.getDirection().getModX();
        int modY = event.getDirection().getModY();
        int modZ = event.getDirection().getModZ();
        
        List<Block> blocks = event.getBlocks();
        
        for(Block b : blocks){
            if(isOpaque(b) && b.getRelative(modX, modY - 1, modZ).getBlockData() instanceof Chest){ // stop opaque blocks at new position from being on top
                event.setCancelled(true);
                return;
            }
        }
    }
    
    @EventHandler(ignoreCancelled = true)
    public void onBlockPistonRetract(BlockPistonRetractEvent event){
        int modX = event.getDirection().getModX();
        int modY = event.getDirection().getModY();
        int modZ = event.getDirection().getModZ();
        
        List<Block> blocks = event.getBlocks();
        
        for(Block b : blocks){
            if(isOpaque(b) && b.getRelative(modX, modY - 1, modZ).getBlockData() instanceof Chest){ // stop opaque blocks at new position from being on top
                event.setCancelled(true);
                return;
            }
        }
    }
    
    private boolean isOpaque(Block block){
        return block.getType().isOccluding();
    }
}