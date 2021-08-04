package com.coelacanth_soft.minecraft.mods;

import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.world.explosion.Explosion;
import net.minecraft.block.CraftingTableBlock;

import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.fabricmc.api.ModInitializer;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.google.gson.*;

public class CraftingTableExplosionMod implements ModInitializer {

	private static File ConfigFile = new File(FabricLoader.getInstance().getConfigDir().toFile(), "crafting-table-explosion.json");

	@Override
	public void onInitialize() {

		// Register a callback for using block.
		UseBlockCallback.EVENT.register((player, world, hand, hitResult) -> {

			// The event must be processed when a player right-click a block in logical server side, and must not spectator mode.
			if (world.isClient() || player.isSpectator() || hitResult.getType() != HitResult.Type.BLOCK) {
				return ActionResult.PASS;
			}

			var blockPos = hitResult.getBlockPos();
			var block = world.getBlockState(blockPos).getBlock();

			// Explode a block if the block is a crafting table:
			if (block instanceof CraftingTableBlock) {

				var config = loadConfiguration();

				if (config.forceRemove) {
					// Force to remove the crafting table before it is exploded.
					world.removeBlock(blockPos, false);
				}

				// Get destruction type
				var destructionType = Explosion.DestructionType.DESTROY;
				if (config.destructionType.equalsIgnoreCase("break"))
					destructionType = Explosion.DestructionType.BREAK; // drop neighboring blocks.
				else if (config.destructionType.equalsIgnoreCase("destroy"))
					destructionType = Explosion.DestructionType.DESTROY; // lost some neighboring blocks.
				else if (config.destructionType.equalsIgnoreCase("none"))
					destructionType = Explosion.DestructionType.NONE; // no effects neighboring blocks.

				// Get explosion power
				var explosionPower = (float)Math.max(0.0, config.power);

				// Explooooosion!
				var pos = hitResult.getPos();
				world.createExplosion(null, pos.x, pos.y, pos.z, explosionPower, destructionType);

				return ActionResult.SUCCESS;
			}
			// otherwise, this event is ignored.
			else {
				return ActionResult.PASS;
			}
		});
	}

	private CraftingTableExplosionModConfig loadConfiguration()
	{
		var config = new CraftingTableExplosionModConfig();

		// Load configuration.
		try {
			var file = Paths.get(ConfigFile.getPath());
			var json = Files.readString(file);

			var gson = new Gson();
			config = gson.fromJson(json, CraftingTableExplosionModConfig.class);
		}
		catch (IOException e) {
			// ignore
			System.out.println("Ignored exception:");
			System.out.println(e.getMessage());
		}
		catch (JsonSyntaxException e) {
			// ignore
			System.out.println("Ignored exception:");
			System.out.println(e.getMessage());
		}

		return config;
	}
}
