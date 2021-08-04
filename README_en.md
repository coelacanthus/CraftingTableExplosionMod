# Crafting Table Explosion Mod

## Description

This mod is for Minecraft Java Edition. And it explodes a crafting table when a player right-click it. So, a player becomes unable to craft 3x3 recipes.


## Requirements

* Minecraft Java Edition 1.17 or lator
* [Fabric loader 0.11.6 or later](https://fabricmc.net/use/).
* [Fabric API 0.36.1+1.17](https://www.curseforge.com/minecraft/mc-mods/fabric-api)

## Installation

1. Create new installation with **new** GAME DIRECTORY, and install Fabric and Fabric API there.
2. Copy crafting-table-explosion-\*.jar to {**new** GAME DIRECTORY}/mods.

## Configuration

Path: {GAME DIRECTORY}\Config\crafting-table-explosion.json

```
{
    "power": 2.0,
    "destructionType": "destroy",
    "removeForce": true
}
```

1. power (double) is a explosion scale. Must be above zero.
2. destructionType (string) is how blocks around a crafting table are effected.
   1. "break": The blocks drop as items.
   2. "destroy": Some blocks are lost, others drop as items.
   3. "none" is the blocks are not effected, even the crafting table.
3. removeForce (boolean) is removing the crafting table forcely.
   1. true: Remove the crafting table forcely, even if destructionType is "none".
   2. false: Whether the crafting table is removed depends on destructionType.

