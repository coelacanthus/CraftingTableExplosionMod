# Crafting Table Explosion Mod

## 説明

Minecraft Java Edition用のModです。このModは作業台を右クリックした際に爆発を起こすModです。つまり3x3のクラフトが出来なくなります。

## 要件

* Minecraft Java Edition 1.17 or lator
* [Fabric loader 0.11.6 or later](https://fabricmc.net/use/).
* [Fabric API 0.36.1+1.17](https://www.curseforge.com/minecraft/mc-mods/fabric-api)

## 導入

1. ランチャーの起動構成で**新しいゲームディレクトリ**で新しい起動構成を作成し、そこにFabricとFabric loaderをインストールして下さい。
2. crafting-table-explosion-\*.jar を {新しいゲームディレクトリ}/mods にコピーして下さい。

## 設定

Path: {GAME DIRECTORY}\Config\crafting-table-explosion.json

```
{
    "power": 2.0,
    "destructionType": "destroy",
    "removeForce": true
}
```

1. power (double) は爆発の規模を設定します。 非負の実数で指定して下さい。
2. destructionType (string) は爆発する作業台の周辺への影響を設定します。
   1. "break": 周辺のブロックはアイテムとしてドロップします。
   2. "destroy": 周辺のブロックが消滅することがあります。
   3. "none": 周辺のブロックへの影響はありません。右クリックした作業台も同様です。
3. removeForce (boolean): 右クリックした作業台を強制的に消滅させるか設定します。
   1. true: destructionTypeが"none"であっても作業台を強制的に消します。
   2. false: 作業台が消滅するかどうかはdestructionTypeに依存します。

