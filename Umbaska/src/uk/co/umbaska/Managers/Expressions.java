package uk.co.umbaska.Managers;

import ch.njol.skript.Skript;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import ch.njol.skript.lang.ExpressionType;
import com.massivecraft.factions.Rel;
import com.massivecraft.factions.entity.Faction;
import com.palmergames.bukkit.towny.object.Town;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.banner.Pattern;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.scoreboard.Objective;
import org.bukkit.util.Vector;
import uk.co.umbaska.ArmourStands.Arms.ExprsLeftArmDirectionX;
import uk.co.umbaska.ArmourStands.Arms.ExprsLeftArmDirectionY;
import uk.co.umbaska.ArmourStands.Arms.ExprsLeftArmDirectionZ;
import uk.co.umbaska.ArmourStands.Arms.ExprsRightArmDirectionX;
import uk.co.umbaska.ArmourStands.Arms.ExprsRightArmDirectionY;
import uk.co.umbaska.ArmourStands.Arms.ExprsRightArmDirectionZ;
import uk.co.umbaska.ArmourStands.ExprsArms;
import uk.co.umbaska.ArmourStands.ExprsBasePlate;
import uk.co.umbaska.ArmourStands.ExprsBodyDirectionX;
import uk.co.umbaska.ArmourStands.ExprsBodyDirectionY;
import uk.co.umbaska.ArmourStands.ExprsBodyDirectionZ;
import uk.co.umbaska.ArmourStands.ExprsGravity;
import uk.co.umbaska.ArmourStands.ExprsHeadDirectionX;
import uk.co.umbaska.ArmourStands.ExprsHeadDirectionY;
import uk.co.umbaska.ArmourStands.ExprsHeadDirectionZ;
import uk.co.umbaska.ArmourStands.ExprsSmall;
import uk.co.umbaska.ArmourStands.ExprsVisible;
import uk.co.umbaska.ArmourStands.Legs.ExprsLeftLegDirectionX;
import uk.co.umbaska.ArmourStands.Legs.ExprsLeftLegDirectionY;
import uk.co.umbaska.ArmourStands.Legs.ExprsLeftLegDirectionZ;
import uk.co.umbaska.ArmourStands.Legs.ExprsRightLegDirectionX;
import uk.co.umbaska.ArmourStands.Legs.ExprsRightLegDirectionY;
import uk.co.umbaska.ArmourStands.Legs.ExprsRightLegDirectionZ;
import uk.co.umbaska.Bungee.ExprAllServers;
import uk.co.umbaska.Bungee.ExprBungeeAllPlayers;
import uk.co.umbaska.Bungee.ExprBungeePlayersOnServer;
import uk.co.umbaska.Bungee.ExprBungeeServerCount;
import uk.co.umbaska.Bungee.ExprBungeeServerOfPlayer;
import uk.co.umbaska.Bungee.ExprBungeeUUID;
import uk.co.umbaska.Dynmap.ExprVisOfPlayer;
import uk.co.umbaska.Factions.ExprAlliesOfFaction;
import uk.co.umbaska.Factions.ExprDescriptionOfFaction;
import uk.co.umbaska.Factions.ExprEnemiesOfFaction;
import uk.co.umbaska.Factions.ExprFactionAtLocation;
import uk.co.umbaska.Factions.ExprFactionOfPlayer;
import uk.co.umbaska.Factions.ExprFactions;
import uk.co.umbaska.Factions.ExprHomeOfFaction;
import uk.co.umbaska.Factions.ExprMOTDOfFaction;
import uk.co.umbaska.Factions.ExprNameOfFaction;
import uk.co.umbaska.Factions.ExprPlayersOfFaction;
import uk.co.umbaska.Factions.ExprPowerOfFaction;
import uk.co.umbaska.Factions.ExprPowerOfPlayer;
import uk.co.umbaska.Factions.ExprPowerboostOfFaction;
import uk.co.umbaska.Factions.ExprPowerboostOfPlayer;
import uk.co.umbaska.Factions.ExprRankOfPlayer;
import uk.co.umbaska.Factions.ExprRelationshipStatus;
import uk.co.umbaska.Factions.ExprTitleOfPlayer;
import uk.co.umbaska.Factions.ExprTrucesOfFaction;
import uk.co.umbaska.GattSk.Expressions.ExprClickType;
import uk.co.umbaska.GattSk.Expressions.ExprClickedInventory;
import uk.co.umbaska.GattSk.Expressions.ExprClickedItem;
import uk.co.umbaska.GattSk.Expressions.ExprClickedItemLore;
import uk.co.umbaska.GattSk.Expressions.ExprClickedItemName;
import uk.co.umbaska.GattSk.Expressions.ExprClickedSlot;
import uk.co.umbaska.GattSk.Expressions.ExprCursorItem;
import uk.co.umbaska.GattSk.Expressions.ExprGetObjective;
import uk.co.umbaska.GattSk.Expressions.ExprGetObjectiveDisplay;
import uk.co.umbaska.GattSk.Expressions.ExprGetObjectiveType;
import uk.co.umbaska.GattSk.Expressions.ExprGetScore;
import uk.co.umbaska.GattSk.Expressions.ExprMaxPlayers;
import uk.co.umbaska.GattSk.Expressions.ExprSpawnReason;
import uk.co.umbaska.GattSk.Expressions.ExprSpawnReasonOfEntity;
import uk.co.umbaska.JSON.ExprJsonAppend;
import uk.co.umbaska.JSON.ExprJsonMessage;
import uk.co.umbaska.JSON.ExprJsonMessageCommand;
import uk.co.umbaska.JSON.ExprJsonMessageStyle;
import uk.co.umbaska.JSON.ExprJsonMessageTooltip;
import uk.co.umbaska.JSON.ExprJsonMessageURL;
import uk.co.umbaska.JSON.JSONMessage;
import uk.co.umbaska.Main;
import uk.co.umbaska.MathsExpressions.ExprArcCos;
import uk.co.umbaska.MathsExpressions.ExprArcSine;
import uk.co.umbaska.MathsExpressions.ExprArcTan;
import uk.co.umbaska.MathsExpressions.ExprBase10;
import uk.co.umbaska.MathsExpressions.ExprCos;
import uk.co.umbaska.MathsExpressions.ExprFactorial;
import uk.co.umbaska.MathsExpressions.ExprHyperbolicCos;
import uk.co.umbaska.MathsExpressions.ExprHyperbolicSin;
import uk.co.umbaska.MathsExpressions.ExprHyperbolicTan;
import uk.co.umbaska.MathsExpressions.ExprLogarithm;
import uk.co.umbaska.MathsExpressions.ExprSignum;
import uk.co.umbaska.MathsExpressions.ExprSine;
import uk.co.umbaska.MathsExpressions.ExprSqrt;
import uk.co.umbaska.MathsExpressions.ExprTan;
import uk.co.umbaska.Misc.Banners.ExprBannerLayer;
import uk.co.umbaska.Misc.Banners.ExprNewBannerFrom;
import uk.co.umbaska.Misc.Banners.ExprNewLayer;
import uk.co.umbaska.Misc.ExprCommandBlockInfo;
import uk.co.umbaska.Misc.ExprEnchantsOfItem;
import uk.co.umbaska.Misc.Looping.ExprLoopAllBlocks;
import uk.co.umbaska.Misc.Looping.ExprLoopSolidBlocks;
import uk.co.umbaska.Misc.Looping.ExprLoopSpecificBlocks2Point;
import uk.co.umbaska.Misc.Looping.ExprLoopSpecificBlocksCyl;
import uk.co.umbaska.Misc.Looping.ExprLoopSpecificBlocksSphere;
import uk.co.umbaska.Misc.Looping.ExprLoopTransparentBlocks;
import uk.co.umbaska.Misc.NBT.ExprNetworking;
import uk.co.umbaska.Misc.NotVersionAffected.EffCentredText;
import uk.co.umbaska.Misc.NotVersionAffected.EffCentredTextSize;
import uk.co.umbaska.Misc.NotVersionAffected.ExprArmourPoints;
import uk.co.umbaska.Misc.NotVersionAffected.ExprBlankEnderpearl;
import uk.co.umbaska.Misc.NotVersionAffected.ExprBlocksInCylinder;
import uk.co.umbaska.Misc.NotVersionAffected.ExprCanMoveEntities;
import uk.co.umbaska.Misc.NotVersionAffected.ExprDirectionLocation;
import uk.co.umbaska.Misc.NotVersionAffected.ExprDyed;
import uk.co.umbaska.Misc.NotVersionAffected.ExprDyedRGB;
import uk.co.umbaska.Misc.NotVersionAffected.ExprEntityFromVariable;
import uk.co.umbaska.Misc.NotVersionAffected.ExprFallDistance;
import uk.co.umbaska.Misc.NotVersionAffected.ExprForceFly;
import uk.co.umbaska.Misc.NotVersionAffected.ExprFreeze;
import uk.co.umbaska.Misc.NotVersionAffected.ExprGetDigits;
import uk.co.umbaska.Misc.NotVersionAffected.ExprGetJSONString;
import uk.co.umbaska.Misc.NotVersionAffected.ExprItemCountInSlot;
import uk.co.umbaska.Misc.NotVersionAffected.ExprNewLocation;
import uk.co.umbaska.Misc.NotVersionAffected.ExprSplashPotionEntity;
import uk.co.umbaska.Misc.NotVersionAffected.ExprUnbreakable;
import uk.co.umbaska.Misc.NotVersionAffected.ExprWorldOfLocation;
import uk.co.umbaska.Misc.UM2_0.ExprBlockSkullOwner;
import uk.co.umbaska.Misc.UM2_0.ExprClosestEntity;
import uk.co.umbaska.Misc.UM2_0.ExprClosestEntityFromLocation;
import uk.co.umbaska.Misc.UM2_0.ExprEntitiesWithin;
import uk.co.umbaska.Misc.UM2_0.ExprItemStackSkullOwnURL;
import uk.co.umbaska.Misc.UM2_0.ExprItemStackSkullOwner;
import uk.co.umbaska.Misc.UM2_0.ExprOffsetLocation;
import uk.co.umbaska.Misc.UM2_0.ExprSkullOwnerURL;
import uk.co.umbaska.Misc.UM2_0.ExprSplitAtAllCharacters;
import uk.co.umbaska.Misc.UM2_0.ExprZombieVillager;
import uk.co.umbaska.NametagEdit.ExprGetNametag;
import uk.co.umbaska.NametagEdit.ExprGetPrefix;
import uk.co.umbaska.NametagEdit.ExprGetSuffix;
import uk.co.umbaska.ParticleProjectiles.Expressions.ExprSimpleVector;
import uk.co.umbaska.PlaceHolderAPI.EffParse;
import uk.co.umbaska.ProtocolLib.ExprCanSee;
import uk.co.umbaska.Spawner.ExprDelayTime;
import uk.co.umbaska.Spawner.ExprItemName;
import uk.co.umbaska.Spawner.ExprSpawnedType;
import uk.co.umbaska.System.ExprContent;
import uk.co.umbaska.System.ExprFileExists;
import uk.co.umbaska.System.ExprFileInDir;
import uk.co.umbaska.System.ExprFreeMemory;
import uk.co.umbaska.System.ExprGetFile;
import uk.co.umbaska.System.ExprGetLine;
import uk.co.umbaska.System.ExprJavaVersion;
import uk.co.umbaska.System.ExprMaxMemory;
import uk.co.umbaska.System.ExprPing;
import uk.co.umbaska.System.ExprTPS;
import uk.co.umbaska.System.ExprTotalMemory;
import uk.co.umbaska.Towny.ExprPlotOwner;
import uk.co.umbaska.Towny.ExprPlotPrice;
import uk.co.umbaska.Towny.ExprRDChatName;
import uk.co.umbaska.Towny.ExprRDFriends;
import uk.co.umbaska.Towny.ExprRDLastOnline;
import uk.co.umbaska.Towny.ExprRDLastOnlineDate;
import uk.co.umbaska.Towny.ExprRDNationRanks;
import uk.co.umbaska.Towny.ExprRDRegistered;
import uk.co.umbaska.Towny.ExprRDSurname;
import uk.co.umbaska.Towny.ExprRDTitle;
import uk.co.umbaska.Towny.ExprTDBank;
import uk.co.umbaska.Towny.ExprTDPlayerCount;
import uk.co.umbaska.Towny.ExprTDPlayers;
import uk.co.umbaska.Towny.ExprTDTaxes;
import uk.co.umbaska.Towny.ExprTownAtPlayer;
import uk.co.umbaska.Towny.ExprTownOfPlayer;
import uk.co.umbaska.UUID.ExprEntityFromUUID;
import uk.co.umbaska.UUID.ExprNamesOfPlayer;
import uk.co.umbaska.UUID.ExprUUIDOfEntity;
import uk.co.umbaska.Vault.ExprGroupOfPlayer;
import uk.co.umbaska.WorldBorder.ExprWorldBorder;
import uk.co.umbaska.WorldEdit.ExprAllSchematics;
import uk.co.umbaska.mcMMO.ExprPowerLevelOfPlayer;

public class Expressions
{
  public static Boolean use_bungee = Boolean.valueOf(Main.getInstance().getConfig().getBoolean("use_bungee"));
  public static Boolean debugInfo = Boolean.valueOf(Main.getInstance().getConfig().getBoolean("debug_info"));
  public static String registeredPlotSystem = null;
  
  private static void registerNewSimpleExpression(String name, String cls, Class returnType, String syntax1, String syntax2, Boolean multiversion)
  {
    if (Skript.isAcceptRegistrations())
    {
      if (multiversion.booleanValue())
      {
        Class newCls = Register.getClass(cls);
        if (newCls == null)
        {
          Bukkit.getLogger().info("Umbaska »»» Can't Register Expression for " + name + " due to Can't find Class!");
          return;
        }
        if (debugInfo.booleanValue()) {
          Bukkit.getLogger().info("Umbaska »»» Registered Expression for " + name + " with syntax\n set " + syntax1 + " of " + syntax2 + " for Version " + Register.getVersion());
        }
        SimplePropertyExpression.register(newCls, returnType, syntax1, syntax2);
      }
      else
      {
        Bukkit.getLogger().info("Umbaska »»» Can't Register Expression for " + name + " due to Wrong Spigot/Bukkit Version!");
      }
    }
    else {
      Bukkit.getLogger().info("Umbaska »»» Can't Register Expression for " + name + " due to Skript Not Accepting Registrations");
    }
    List<String> expressions = (List)Register.simpleexpressionList.get(name);
    if (expressions == null) {
      expressions = new ArrayList();
    }
    expressions.add(syntax1);
    expressions.add(syntax2);
    Register.simpleexpressionList.put(name, expressions);
  }
  
  private static void registerNewSimpleExpression(String name, Class cls, Class returnType, String syntax1, String syntax2, Boolean multiversion)
  {
    if (Skript.isAcceptRegistrations())
    {
      if (debugInfo.booleanValue()) {
        Bukkit.getLogger().info("Umbaska »»» Registered Expression for " + name + " with syntax\n set " + syntax1 + " of " + syntax2 + " for Version " + Register.getVersion());
      }
      SimplePropertyExpression.register(cls, returnType, syntax1, syntax2);
    }
    else
    {
      Bukkit.getLogger().info("Umbaska »»» Can't Register Expression for " + name + " due to Skript Not Accepting Registrations");
    }
  }
  
  private static void registerNewExpression(String name, String cls, Class returnType, ExpressionType expressionType, String syntax, Boolean multiversion)
  {
    if (Skript.isAcceptRegistrations())
    {
      if (multiversion.booleanValue())
      {
        Class newCls = Register.getClass(cls);
        if (newCls == null)
        {
          Bukkit.getLogger().info("Umbaska »»» Can't Register Expression for " + name + " due to Can't find Class!");
          return;
        }
        if (debugInfo.booleanValue()) {
          Bukkit.getLogger().info("Umbaska »»» Registered Expression for " + name + " with syntax\n " + syntax + " for Version " + Register.getVersion());
        }
        registerNewExpression(name, newCls, returnType, expressionType, new String[] { syntax });
      }
      else
      {
        try
        {
          registerNewExpression(name, Class.forName(cls), returnType, expressionType, new String[] { syntax });
        }
        catch (ClassNotFoundException e)
        {
          Bukkit.getLogger().info("Umbaska »»» Can't Register Expression for " + name + " due to Wrong Spigot/Bukkit Version!");
        }
      }
    }
    else {
      Bukkit.getLogger().info("Umbaska »»» Can't Register Expression for " + name + " due to Skript Not Accepting Registrations");
    }
  }
  
  private static void registerNewExpression(String name, Class cls, Class returnType, ExpressionType expressionType, String... syntaxes)
  {
    if (Skript.isAcceptRegistrations())
    {
      registerNewExpression(cls, returnType, expressionType, syntaxes);
      if (debugInfo.booleanValue()) {
        for (String syntax : syntaxes) {
          Bukkit.getLogger().info("Umbaska »»» Registered Expression for " + name + " with syntax \n" + syntax);
        }
      }
    }
    else
    {
      Bukkit.getLogger().info("Umbaska »»» Can't Register Expression for " + name + " due to Skript Not Accepting Registrations");
    }
    List<String> expressions = (List)Register.expressionList.get(name);
    if (expressions == null) {
      expressions = new ArrayList();
    }
    for (String s : syntaxes) {
      expressions.add(s);
    }
    Register.expressionList.put(name, expressions);
  }
  
  private static void registerNewExpression(Class cls, Class returnType, ExpressionType expressionType, String... syntaxes)
  {
    if (Skript.isAcceptRegistrations())
    {
      Skript.registerExpression(cls, returnType, expressionType, syntaxes);
      if (debugInfo.booleanValue()) {
        for (String syntax : syntaxes) {
          Bukkit.getLogger().info("Umbaska »»» Registered Expression for " + cls.getName() + " with syntax\n " + syntax);
        }
      }
    }
    else
    {
      Bukkit.getLogger().info("Umbaska »»» Can't Register Expression for " + cls.getName() + " due to Skript Not Accepting Registrations");
    }
  }
  
  public static void runRegister()
  {
    Boolean registeredPlotSquared = Boolean.valueOf(false);
    Plugin pl = Bukkit.getServer().getPluginManager().getPlugin("PlotSquared");
    if (pl != null)
    {
      registeredPlotSquared = Boolean.valueOf(true);
      registerNewExpression("PlotSquared - Plot at Player", uk.co.umbaska.PlotSquared.ExprPlotAtPlayer.class, String.class, ExpressionType.PROPERTY, new String[] { "plot at %player%" });
      registerNewExpression("PlotSquared - Plot at Location", uk.co.umbaska.PlotSquared.ExprPlotAtLoc.class, String.class, ExpressionType.PROPERTY, new String[] { "plot at location %location%" });
      registerNewExpression("PlotSquared - Owner of Plot", uk.co.umbaska.PlotSquared.ExprGetOwner.class, String.class, ExpressionType.PROPERTY, new String[] { "[get ]owner of %string%" });
      registerNewExpression("PlotSquared - Plots of Player", uk.co.umbaska.PlotSquared.ExprGetPlayerPlots.class, String.class, ExpressionType.PROPERTY, new String[] { "plots of %player%" });
      registerNewExpression("PlotSquared - Top Corner of Plot", uk.co.umbaska.PlotSquared.ExprTopCorner.class, Location.class, ExpressionType.PROPERTY, new String[] { "(top|upper) corner of %string% in %world%" });
      registerNewExpression("PlotSquared - Bottom Corner of Plot", uk.co.umbaska.PlotSquared.ExprBottomCorner.class, Location.class, ExpressionType.PROPERTY, new String[] { "(bottom|lower) corner of %string% in %world%" });
    }
    if (!registeredPlotSquared.booleanValue())
    {
      pl = Bukkit.getServer().getPluginManager().getPlugin("PlotMe");
      if (pl != null)
      {
        registerNewExpression("PlotMe - Plot at Player", uk.co.umbaska.PlotMe.ExprPlotAtPlayer.class, String.class, ExpressionType.PROPERTY, new String[] { "plot at %player%" });
        registerNewExpression("PlotMe - Plot at Location", uk.co.umbaska.PlotMe.ExprPlotAtLoc.class, String.class, ExpressionType.PROPERTY, new String[] { "plot at location %location%" });
        registerNewExpression("PlotMe - Owner of Plot", uk.co.umbaska.PlotMe.ExprGetOwner.class, String.class, ExpressionType.PROPERTY, new String[] { "[get ]owner of %string%" });
        registerNewExpression("PlotMe - Plots of Player", uk.co.umbaska.PlotMe.ExprGetPlayerPlots.class, String.class, ExpressionType.PROPERTY, new String[] { "plots of %player%" });
        registerNewExpression("PlotMe - Top Corner of Plot", uk.co.umbaska.PlotMe.ExprTopCorner.class, Location.class, ExpressionType.PROPERTY, new String[] { "(top|upper) corner of %string% in %world%" });
        registerNewExpression("PlotMe - Bottom Corner of Plot", uk.co.umbaska.PlotMe.ExprBottomCorner.class, Location.class, ExpressionType.PROPERTY, new String[] { "(bottom|lower) corner of %string% in %world%" });
      }
      registerNewExpression(uk.co.umbaska.PlotSquared.ExprPlotAtPlayer.class, String.class, ExpressionType.PROPERTY, new String[] { "plot at %player%" });
      registerNewExpression(uk.co.umbaska.PlotSquared.ExprPlotAtLoc.class, String.class, ExpressionType.PROPERTY, new String[] { "plot at location %location%" });
      registerNewExpression(uk.co.umbaska.PlotSquared.ExprGetOwner.class, String.class, ExpressionType.PROPERTY, new String[] { "[get ]owner of %string%" });
      registerNewExpression(uk.co.umbaska.PlotSquared.ExprGetPlayerPlots.class, String.class, ExpressionType.PROPERTY, new String[] { "plots of %player%" });
      registerNewExpression(uk.co.umbaska.PlotSquared.ExprTopCorner.class, Location.class, ExpressionType.PROPERTY, new String[] { "(top|upper) corner of %string% in %world%" });
      registerNewExpression(uk.co.umbaska.PlotSquared.ExprBottomCorner.class, Location.class, ExpressionType.PROPERTY, new String[] { "(bottom|lower) corner of %string% in %world%" });
    }
    else if (Bukkit.getServer().getPluginManager().getPlugin("PlotMe") != null)
    {
      pl = Bukkit.getServer().getPluginManager().getPlugin("PlotMe");
      registerNewExpression(uk.co.umbaska.PlotMe.ExprPlotAtPlayer.class, String.class, ExpressionType.PROPERTY, new String[] { "plot at %player%" });
      registerNewExpression(uk.co.umbaska.PlotMe.ExprPlotAtLoc.class, String.class, ExpressionType.PROPERTY, new String[] { "plot at location %location%" });
      registerNewExpression(uk.co.umbaska.PlotMe.ExprGetOwner.class, String.class, ExpressionType.PROPERTY, new String[] { "[get ]owner of %string%" });
      registerNewExpression(uk.co.umbaska.PlotMe.ExprGetPlayerPlots.class, String.class, ExpressionType.PROPERTY, new String[] { "plots of %player%" });
      registerNewExpression(uk.co.umbaska.PlotMe.ExprGetPlayerPlots.class, String.class, ExpressionType.PROPERTY, new String[] { "plots of %player%" });
      registerNewExpression(uk.co.umbaska.PlotMe.ExprTopCorner.class, Location.class, ExpressionType.PROPERTY, new String[] { "(top|upper) corner of %string% in %world%" });
      registerNewExpression(uk.co.umbaska.PlotMe.ExprBottomCorner.class, Location.class, ExpressionType.PROPERTY, new String[] { "(bottom|lower) corner of %string% in %world%" });
    }
    registeredPlotSystem = "No plot system hooked";
    if (pl != null) {
      registeredPlotSystem = pl.getName() + " - " + pl.getDescription().getVersion();
    }
    pl = Bukkit.getServer().getPluginManager().getPlugin("Vault");
    if (pl != null) {
      registerNewExpression(ExprGroupOfPlayer.class, String.class, ExpressionType.PROPERTY, new String[] { "primary group of %player%" });
    }
    registerNewExpression("World Border Expressions", ExprWorldBorder.class, Object.class, ExpressionType.PROPERTY, new String[] { "worldborder size of %world%", "worldborder [damage] amount of %world%", "worldborder [damage] buffer of %world%", "worldborder [warning] distance of %world%", "worldborder [warning] time of %world%" });
    
    registerNewExpression("Network", ExprNetworking.class, Object.class, ExpressionType.PROPERTY, new String[] { "ip [of server]", "port [of server]", "connection throttle [of server]", "online mode [of server]", "version [of server]", "motd [of server]", "name [of server]", "idle timeout [of server]", "[server] icon [of server]", "max players [of server]", "host of %player%", "port of %player%", "full host of %player%", "host[ ]name of %player%", "address of %player%", "connection address of %player%", "full ip [of server]" });
    
    pl = Bukkit.getServer().getPluginManager().getPlugin("Towny");
    if (pl != null)
    {
      registerNewExpression("Towny - Town at Player", ExprTownAtPlayer.class, String.class, ExpressionType.PROPERTY, new String[] { "town at %player%" });
      registerNewExpression("Towny - Town of Player", ExprTownOfPlayer.class, Town.class, ExpressionType.PROPERTY, new String[] { "town of %player%" });
      registerNewExpression("Towny - Town Balance", ExprTDBank.class, Double.class, ExpressionType.PROPERTY, new String[] { "town balance of %string%" });
      registerNewExpression("Towny - Town Player Count", ExprTDPlayerCount.class, Integer.class, ExpressionType.PROPERTY, new String[] { "player[ ]count of %string%" });
      registerNewExpression("Towny - Town Players", ExprTDPlayers.class, String.class, ExpressionType.PROPERTY, new String[] { "players of %string%" });
      registerNewExpression("Towny - Town Taxes", ExprTDTaxes.class, Double.class, ExpressionType.PROPERTY, new String[] { "town taxes of %string%" });
      registerNewExpression("Towny - Plot Owner", ExprPlotOwner.class, String.class, ExpressionType.PROPERTY, new String[] { "owner of plot at %location%" });
      registerNewExpression("Towny - Plot Price", ExprPlotPrice.class, Double.class, ExpressionType.PROPERTY, new String[] { "price of plot at %location%" });
      registerNewExpression("Towny - RD Last Online", ExprRDLastOnline.class, String.class, ExpressionType.PROPERTY, new String[] { "resident data last online of %player%" });
      registerNewExpression("Towny - RD Last Online Date", ExprRDLastOnlineDate.class, String.class, ExpressionType.PROPERTY, new String[] { "resident data last online date of %player%" });
      registerNewExpression("Towny - RD Chat Name", ExprRDChatName.class, String.class, ExpressionType.PROPERTY, new String[] { "resident data chat name of %player%" });
      registerNewExpression("Towny - RD Friends", ExprRDFriends.class, String.class, ExpressionType.PROPERTY, new String[] { "resident data friends of %player%" });
      registerNewExpression("Towny - RD Nation Ranks--", ExprRDNationRanks.class, String.class, ExpressionType.PROPERTY, new String[] { "resident data nation ranks of %player%" });
      registerNewExpression("Towny - Resident Data Registered", ExprRDRegistered.class, Long.class, ExpressionType.PROPERTY, new String[] { "resident data registered of %player%" });
      registerNewExpression("Towny - Resident Data Surname", ExprRDSurname.class, String.class, ExpressionType.PROPERTY, new String[] { "resident data surname of %player%" });
      registerNewExpression("Towny - Resident Data Title", ExprRDTitle.class, String.class, ExpressionType.PROPERTY, new String[] { "resident data title of %player%" });
    }
    registerNewExpression("Names of Player", ExprNamesOfPlayer.class, String.class, ExpressionType.COMBINED, new String[] { "names of %string%" });
    if (!Main.getInstance().getConfig().isSet("entity_uuid_fix")) {
      Main.getInstance().getConfig().set("entity_uuid_fix", Boolean.valueOf(false));
    }
    registerNewExpression("Entity UUID", ExprUUIDOfEntity.class, String.class, ExpressionType.SIMPLE, new String[] { "entity uuid of %entity%" });
    
    pl = Bukkit.getServer().getPluginManager().getPlugin("UmbaskaAPI");
    if (pl != null) {
      registerNewExpression("Factions - Faction of Player ", ExprFactionOfPlayer.class, String.class, ExpressionType.PROPERTY, new String[] { "faction of %player%" });
    }
    registerNewExpression("Spawner - Delay Time", ExprDelayTime.class, Integer.class, ExpressionType.PROPERTY, new String[] { "delay time of %location%" });
    registerNewExpression("Spawner - Entity Type", ExprSpawnedType.class, String.class, ExpressionType.PROPERTY, new String[] { "entity type of %location%" });
    
    registerNewExpression("Item Name", ExprItemName.class, String.class, ExpressionType.SIMPLE, new String[] { "item name" });
    registerNewExpression("Centred Text", EffCentredText.class, String.class, ExpressionType.SIMPLE, new String[] { "cent(er|re)d %string%" });
    registerNewExpression("Centred Text", EffCentredTextSize.class, String.class, ExpressionType.SIMPLE, new String[] { "cent(er|re)d %string% [with] [max] [length] [of] %-integer%" });
    registerNewExpression("Armor Points", ExprArmourPoints.class, Double.class, ExpressionType.PROPERTY, new String[] { "(armour|armor) points of %player%" });
    registerNewExpression("Amount of Items", ExprItemCountInSlot.class, Integer.class, ExpressionType.SIMPLE, new String[] { "amount of items in %itemstack%" });
    registerNewExpression("Get JSON String", ExprGetJSONString.class, String.class, ExpressionType.SIMPLE, new String[] { "JSON string %string% from %string%" });
    registerNewExpression("Get Digits from String", ExprGetDigits.class, String.class, ExpressionType.SIMPLE, new String[] { "get (digits|numbers|nums|num) of %string%" });
    
    registerNewExpression("New Location", ExprNewLocation.class, Location.class, ExpressionType.SIMPLE, new String[] { "new location %number%, %number%, %number% in world %string%" });
    registerNewExpression("File Exists", ExprFileExists.class, Boolean.class, ExpressionType.PROPERTY, new String[] { "exist(e|a)nce of %string%" });
    registerNewExpression("Get File", ExprGetFile.class, String.class, ExpressionType.PROPERTY, new String[] { "file from %string%" });
    registerNewExpression("Conents of file", ExprContent.class, String.class, ExpressionType.SIMPLE, new String[] { "content[s] (from|of) file %string%" });
    registerNewExpression("Get line in file", ExprGetLine.class, String.class, ExpressionType.SIMPLE, new String[] { "line %integer% in file %string%" });
    registerNewExpression("Files in Folder", ExprFileInDir.class, String.class, ExpressionType.SIMPLE, new String[] { "files in %string% (recursive|r) %boolean%" });
    registerNewExpression("Enchants of Item", ExprEnchantsOfItem.class, String.class, ExpressionType.PROPERTY, new String[] { "enchants of %itemstack%" });
    pl = Bukkit.getServer().getPluginManager().getPlugin("NametagEdit");
    if (pl != null)
    {
      registerNewExpression("NametagEdit - Prefix of Player", ExprGetPrefix.class, String.class, ExpressionType.PROPERTY, new String[] { "prefix of %player%" });
      registerNewExpression("NametagEdit - Suffix of Player", ExprGetSuffix.class, String.class, ExpressionType.PROPERTY, new String[] { "suffix of %player%" });
      registerNewExpression("NametagEdit - Name Tag of Player", ExprGetNametag.class, String.class, ExpressionType.PROPERTY, new String[] { "name tag of %player%" });
    }
    registerNewExpression("All Blocks", ExprLoopAllBlocks.class, Block.class, ExpressionType.SIMPLE, new String[] { "[all] blocks (from|within) %location% to %location%" });
    registerNewExpression("Solid Blocks", ExprLoopSolidBlocks.class, Block.class, ExpressionType.SIMPLE, new String[] { "[all] solid blocks (from|within) %location% to %location%" });
    registerNewExpression("Transparent Blocks", ExprLoopTransparentBlocks.class, Block.class, ExpressionType.SIMPLE, new String[] { "[all] (transparent|trans|t|non-solid|see through|other) blocks (from|within) %location% to %location%" });
    registerNewExpression("Blocks in Cylinder", ExprBlocksInCylinder.class, Block.class, ExpressionType.COMBINED, new String[] { "blocks in cylindrical radius of %number%( [with] height|,) %number% (around|from[ block[ at]]) %location%" });
    
    registerNewExpression("Specific Blocks - Cylinder", ExprLoopSpecificBlocksCyl.class, Block.class, ExpressionType.COMBINED, new String[] { "[all] [blocks of type] %materials% in cylindrical radius of %number%( [with] height|,) %number% (around|from[ block[ at]]) %location%" });
    registerNewExpression("Specific Blocks - 2 Points", ExprLoopSpecificBlocks2Point.class, Block.class, ExpressionType.COMBINED, new String[] { "[all] [blocks of type] %materials% (from|within) %location% to %location%" });
    registerNewExpression("Specific Blocks - Sphere", ExprLoopSpecificBlocksSphere.class, Block.class, ExpressionType.COMBINED, new String[] { "[all] [blocks of type] %materials% (from|around) %location% [with] [radius] %number%" });
    
    registerNewExpression("Command Block Data", ExprCommandBlockInfo.class, String.class, ExpressionType.SIMPLE, new String[] { "command of %block%", "name of %block%" });
    
    pl = Bukkit.getServer().getPluginManager().getPlugin("PlaceholderAPI");
    if (pl != null) {
      registerNewExpression("PlaceholderAPI - Parse", EffParse.class, String.class, ExpressionType.PROPERTY, new String[] { "placeholder parse %string% as %player%", "placeholder parse %string%" });
    }
    pl = Bukkit.getServer().getPluginManager().getPlugin("Dynmap");
    if (pl != null) {
      registerNewExpression("Dynmap - Visibility of Player", ExprVisOfPlayer.class, Boolean.class, ExpressionType.PROPERTY, new String[] { "Dynmap visibility of %player%" });
    }
    pl = Bukkit.getServer().getPluginManager().getPlugin("MassiveCore");
    if (pl != null)
    {
      pl = Bukkit.getServer().getPluginManager().getPlugin("Factions");
      if (pl != null)
      {
        registerNewSimpleExpression("Factions - Name of Faction", ExprNameOfFaction.class, String.class, "name", "faction", Boolean.valueOf(false));
        registerNewSimpleExpression("Factions - Faction of Player", ExprFactionOfPlayer.class, Faction.class, "faction", "player", Boolean.valueOf(false));
        registerNewSimpleExpression("Factions - Description of Faction", ExprDescriptionOfFaction.class, String.class, "description", "faction", Boolean.valueOf(false));
        registerNewSimpleExpression("Factions - Power of Player", ExprPowerOfPlayer.class, Double.class, "power", "player", Boolean.valueOf(false));
        registerNewSimpleExpression("Factions - Powerboost of Player", ExprPowerboostOfPlayer.class, Double.class, "powerboost", "player", Boolean.valueOf(false));
        registerNewSimpleExpression("Factions - MOTD of Faction", ExprMOTDOfFaction.class, String.class, "motd", "faction", Boolean.valueOf(false));
        registerNewSimpleExpression("Factions - Home of Faction", ExprHomeOfFaction.class, Location.class, "home", "faction", Boolean.valueOf(false));
        registerNewSimpleExpression("Factions - Title of Player", ExprTitleOfPlayer.class, String.class, "title", "player", Boolean.valueOf(false));
        registerNewSimpleExpression("Factions - Power of Faction", ExprPowerOfFaction.class, Double.class, "power", "faction", Boolean.valueOf(false));
        registerNewSimpleExpression("Factions - Powerboost of Faction", ExprPowerboostOfFaction.class, Double.class, "powerboost", "faction", Boolean.valueOf(false));
        registerNewExpression("Factions - Faction at Location", ExprFactionAtLocation.class, Faction.class, ExpressionType.SIMPLE, new String[] { "[the] faction at %location%" });
        registerNewExpression("Factions - Factions", ExprFactions.class, String.class, ExpressionType.SIMPLE, new String[] { "list of [all] Factions", "Factions list", "all Factions" });
        registerNewExpression("Factions - Allies of Faction", ExprAlliesOfFaction.class, String.class, ExpressionType.SIMPLE, new String[] { "list of [all] allies of [the] [faction] %faction%", "[all] faction allies [list] of [the] [faction] %faction%" });
        registerNewExpression("Factions - Players of Faction", ExprPlayersOfFaction.class, Player.class, ExpressionType.SIMPLE, new String[] { "list of [all] players of [the faction] %faction%", "[all] players['] [list] of [the faction] %faction%" });
        registerNewExpression("Factions - Relationship Status", ExprRelationshipStatus.class, Rel.class, ExpressionType.SIMPLE, new String[] { "relation[ship] [status] between [the] [faction] %faction% (and|with) [the] [faction] %faction%" });
        registerNewExpression("Factions - Rank of Player", ExprRankOfPlayer.class, Rel.class, ExpressionType.SIMPLE, new String[] { "role of [the] [player] %player%" });
        registerNewExpression("Factions - Enemies of Faction", ExprEnemiesOfFaction.class, String.class, ExpressionType.SIMPLE, new String[] { "list of [all] enemies of [the] [faction] %faction%", "[all] faction enemies [list] of %faction%" });
        registerNewExpression("Factions - Truces of Faction", ExprTrucesOfFaction.class, String.class, ExpressionType.SIMPLE, new String[] { "list of [all] truces of [the] [faction] %faction%", "[all] faction truces [list] of %faction%" });
      }
    }
    pl = Bukkit.getServer().getPluginManager().getPlugin("mcMMO");
    if (pl != null) {
      registerNewSimpleExpression("mcMMO - Power Level of Player", ExprPowerLevelOfPlayer.class, Integer.class, "power(level| level)", "player", Boolean.valueOf(false));
    }
    registerNewExpression("Free Memory", ExprFreeMemory.class, Integer.class, ExpressionType.PROPERTY, new String[] { "free memory" });
    registerNewExpression("Java Version", ExprJavaVersion.class, String.class, ExpressionType.PROPERTY, new String[] { "java version" });
    registerNewExpression("Max Memory", ExprMaxMemory.class, Integer.class, ExpressionType.PROPERTY, new String[] { "max memory" });
    registerNewExpression("Total Memory", ExprTotalMemory.class, Integer.class, ExpressionType.PROPERTY, new String[] { "total memory" });
    registerNewExpression("TPS", ExprTPS.class, Double.class, ExpressionType.PROPERTY, new String[] { "tps" });
    registerNewExpression("Ping", ExprPing.class, Integer.class, ExpressionType.PROPERTY, new String[] { "%player%[[']s] ping" });
    registerNewExpression("Ping", ExprPing.class, Integer.class, ExpressionType.PROPERTY, new String[] { "ping of %player%" });
    
    pl = Bukkit.getServer().getPluginManager().getPlugin("ProtocolLib");
    if (pl != null) {
      registerNewExpression("ProtocolLib - Can See", ExprCanSee.class, Boolean.class, ExpressionType.PROPERTY, new String[] { "visibility of %entities% for %player%" });
    }
    pl = Bukkit.getServer().getPluginManager().getPlugin("WorldEdit");
    if (pl != null) {
      registerNewExpression("All Schematics", ExprAllSchematics.class, String.class, ExpressionType.COMBINED, new String[] { "all schematics" });
    }
    registerNewExpression("Armor Dyed Color", ExprDyed.class, ItemStack.class, ExpressionType.SIMPLE, new String[] { "%itemstack% (colo[u]red|dyed) %color%" });
    registerNewExpression("Armor Dyed RGB", ExprDyedRGB.class, ItemStack.class, ExpressionType.SIMPLE, new String[] { "%itemstack% (colo[u]red|dyed) %number%, %number%(,| and) %number%" });
    registerNewExpression("Clicked Item", ExprClickedItem.class, ItemStack.class, ExpressionType.SIMPLE, new String[] { "clicked item" });
    registerNewExpression("Clicked Inventory", ExprClickedInventory.class, ItemStack.class, ExpressionType.SIMPLE, new String[] { "clicked inventory" });
    registerNewExpression("Clicked Cursor Item", ExprCursorItem.class, ItemStack.class, ExpressionType.SIMPLE, new String[] { "cursor item" });
    registerNewExpression("Clicked Slot", ExprClickedSlot.class, Integer.class, ExpressionType.SIMPLE, new String[] { "clicked slot" });
    registerNewExpression("Clicked Type", ExprClickType.class, String.class, ExpressionType.SIMPLE, new String[] { "click type" });
    registerNewExpression("Clicked Item Name", ExprClickedItemName.class, String.class, ExpressionType.SIMPLE, new String[] { "clicked item name" });
    registerNewExpression("Clicked Item Lore", ExprClickedItemLore.class, String.class, ExpressionType.SIMPLE, new String[] { "clicked item lore" });
    
    registerNewExpression("Max Players", ExprMaxPlayers.class, Integer.class, ExpressionType.SIMPLE, new String[] { "max players" });
    
    registerNewExpression("Spawn Reason of Entity from Event", ExprSpawnReason.class, String.class, ExpressionType.SIMPLE, new String[] { "spawn reason" });
    registerNewExpression("Spawn Reason of Entity", ExprSpawnReasonOfEntity.class, String.class, ExpressionType.SIMPLE, new String[] { "spawn reason (of|for) %entity%" });
    registerNewExpression("Scoreboard - Get Score", ExprGetScore.class, Integer.class, ExpressionType.PROPERTY, new String[] { "value [of] %string% objective %string% for [score] %string%" });
    
    registerNewExpression("Scoreboard - Get Objective Type", ExprGetObjectiveType.class, String.class, ExpressionType.PROPERTY, new String[] { "objective type of %string% (from|in) [score][board] %scoreboard%" });
    registerNewExpression("Scoreboard - Get Objective from Display Slot", ExprGetObjectiveDisplay.class, Objective.class, ExpressionType.PROPERTY, new String[] { "objective in [[display]slot] %displayslot% from [score][board] %string%" });
    registerNewExpression("Scoreboard - Get Objective", ExprGetObjective.class, String.class, ExpressionType.PROPERTY, new String[] { "objective %string% from [score][board] %string%" });
    
    registerNewExpression("Location based on Direction", ExprDirectionLocation.class, Location.class, ExpressionType.COMBINED, new String[] { "[the] (location|position) %number% (block|meter)[s] in [the] direction %direction% of %location%" });
    registerNewExpression("Location based on Direction", ExprDirectionLocation.class, Location.class, ExpressionType.COMBINED, new String[] { "(location|position) [of] direction %direction% (*|times|multiplied by length) %number% (from|with) [origin] %location%" });
    Main.getInstance().getLogger().info("When Gatt and BaeFell work together, amazing things happen! \nGO! SUPER GATTFELL REGISTER SEQUENCE!\nAchievement Get! Used the new Umbaska Version");
    registerNewSimpleExpression("Freeze", ExprFreeze.class, Boolean.class, "freeze state", "player", Boolean.valueOf(false));
    registerNewSimpleExpression("Can Collide", ExprCanMoveEntities.class, Boolean.class, "[can] collide [with entities]", "player", Boolean.valueOf(false));
    registerNewExpression("Entity from Variable", ExprEntityFromVariable.class, Entity.class, ExpressionType.COMBINED, new String[] { "[umbaska] entity from [variable] %entity%" });
    registerNewExpression("Entity UUID", ExprEntityFromUUID.class, Entity.class, ExpressionType.COMBINED, new String[] { "[umbaska] entity from uuid %string%" });
    
    registerNewExpression("Fall Distance", ExprFallDistance.class, Number.class, ExpressionType.SIMPLE, new String[] { "fall distance of %entity%" });
    registerNewSimpleExpression("Is Flying State", ExprForceFly.class, Boolean.class, "(is flying|force fly)", "player", Boolean.valueOf(false));
    registerNewExpression("Unbreakable Itemstack", ExprUnbreakable.class, ItemStack.class, ExpressionType.PROPERTY, new String[] { "[a[n]] unbreakable %itemstacks%" });
    
    registerNewExpression("Entity Within Two Points", ExprEntitiesWithin.class, Entity.class, ExpressionType.COMBINED, new String[] { "[all] entities (from|within|in) %location% (and|to) %location%" });
    registerNewExpression("Offset Location", ExprOffsetLocation.class, Location.class, ExpressionType.SIMPLE, new String[] { "%location% offset by %number%, %number%(,| and) %number%" });
    registerNewSimpleExpression("World of Location", ExprWorldOfLocation.class, World.class, "[umbaska] world", "location", Boolean.valueOf(false));
    registerNewSimpleExpression("Skull Block Set Owner", ExprBlockSkullOwner.class, String.class, "[block ]skull owner", "block", Boolean.valueOf(false));
    registerNewSimpleExpression("Skull Item Set Owner", ExprItemStackSkullOwner.class, String.class, "[itemstack ]skull owner", "itemstack", Boolean.valueOf(false));
    registerNewSimpleExpression("Skull Item Set URL", ExprItemStackSkullOwnURL.class, String.class, "skull url", "itemstack", Boolean.valueOf(false));
    
    registerNewExpression("Skull from URL", ExprSkullOwnerURL.class, ItemStack.class, ExpressionType.SIMPLE, new String[] { "%itemstack% [with] [skull] url %string%" });
    
    registerNewExpression("Splash Potion Entity", ExprSplashPotionEntity.class, Entity.class, ExpressionType.SIMPLE, new String[] { "(thrown|splash) potion (from|using) [item] %itemstack%" });
    registerNewExpression("Blank Enderpearl*", ExprBlankEnderpearl.class, Entity.class, ExpressionType.SIMPLE, new String[] { "blank %entity%" });
    
    registerNewExpression("Closest Entity to Entity", ExprClosestEntity.class, Entity.class, ExpressionType.SIMPLE, new String[] { "closest entity from [entity] %entity%" });
    
    registerNewExpression("Arc Tan", ExprArcTan.class, Number.class, ExpressionType.SIMPLE, new String[] { "[umbaska] arc tan[gent] %number%" });
    registerNewExpression("Arc Sine", ExprArcSine.class, Number.class, ExpressionType.SIMPLE, new String[] { "[umbaska] arc sin[e] %number%" });
    registerNewExpression("Arc Cos", ExprArcCos.class, Number.class, ExpressionType.SIMPLE, new String[] { "[umbaska] arc cos[ine] %number%" });
    registerNewExpression("Tan", ExprTan.class, Number.class, ExpressionType.SIMPLE, new String[] { "[umbaska] tan[gent] %number%" });
    registerNewExpression("Sine", ExprSine.class, Number.class, ExpressionType.SIMPLE, new String[] { "[umbaska] sin[e] %number%" });
    registerNewExpression("Cos", ExprCos.class, Number.class, ExpressionType.SIMPLE, new String[] { "[umbaska] cos[ine] %number%" });
    registerNewExpression("Hyperbolic Tan", ExprHyperbolicTan.class, Number.class, ExpressionType.SIMPLE, new String[] { "[umbaska] hyperbolic tan[gent] %number%" });
    registerNewExpression("Hyperbolic Sine", ExprHyperbolicSin.class, Number.class, ExpressionType.SIMPLE, new String[] { "[umbaska] hyperbolic sin[e] %number%" });
    registerNewExpression("Hyperbolic Cos", ExprHyperbolicCos.class, Number.class, ExpressionType.SIMPLE, new String[] { "[umbaska] hyperbolic cos[ine] %number%" });
    
    registerNewExpression("Logarithm", ExprLogarithm.class, Number.class, ExpressionType.SIMPLE, new String[] { "[umbaska] [natural ]log[arithm] %number%" });
    registerNewExpression("Base10", ExprBase10.class, Number.class, ExpressionType.SIMPLE, new String[] { "[umbaska] base(-| )10 [log[arithm]] %number%" });
    registerNewExpression("Signum", ExprSignum.class, Number.class, ExpressionType.SIMPLE, new String[] { "[umbaska] signum %number%" });
    registerNewExpression("Square Root", ExprSqrt.class, Number.class, ExpressionType.SIMPLE, new String[] { "[umbaska] (sqrt|square root) [of] %number%" });
    registerNewExpression("Fectorial", ExprFactorial.class, Number.class, ExpressionType.SIMPLE, new String[] { "[umbaska] %number% factorial" });
    registerNewExpression("Fectorial", ExprFactorial.class, Number.class, ExpressionType.SIMPLE, new String[] { "[umbaska] %number%!" });
    registerNewExpression("Simple Vector", ExprSimpleVector.class, Vector.class, ExpressionType.SIMPLE, new String[] { "[umbaska] (vector from|new vector [from]) %number%, %number%, %number%" });
    
    Bukkit.getLogger().info("[Umbaska] Registering Armor Stand related expressions");
    registerNewExpression("Closest Entity from Location", ExprClosestEntityFromLocation.class, Entity.class, ExpressionType.SIMPLE, new String[] { "closest entity from [location] %location%" });
    registerNewSimpleExpression("Armor Stand - Is Marker", "ArmourStands.ExprMarker", Boolean.class, "[has] marker", "entity", Boolean.valueOf(true));
    registerNewSimpleExpression("Has NoAI", "ArmourStands.ExprNoAI", Boolean.class, "no[ ]ai[ state]", "entity", Boolean.valueOf(true));
    registerNewSimpleExpression("Is Silent", "ArmourStands.ExprSilent", Boolean.class, "silent[ state]", "entity", Boolean.valueOf(true));
    
    registerNewExpression("Item with Attribute", "Attributes.ExprGenericItemAttribute", ItemStack.class, ExpressionType.SIMPLE, "%itemstack% with generic [item] attribute %string% (with value of|set to|value) %number%", Boolean.valueOf(true));
    registerNewExpression("Item with Pre-Defined Attributes", "Attributes.ExprStatItemAttribute", ItemStack.class, ExpressionType.SIMPLE, "%itemstack% with [item] attribute %entityattribute% (with value of|set to|value) %number% (using operation|operation|with operation) %nbtoperation%", Boolean.valueOf(true));
    
    registerNewSimpleExpression("Armor Stand - Show Arms", ExprsArms.class, Boolean.class, "[show] arms", "entity", Boolean.valueOf(false));
    registerNewSimpleExpression("Armor Stand - Show Base", ExprsBasePlate.class, Boolean.class, "[show] base plate", "entity", Boolean.valueOf(false));
    registerNewSimpleExpression("Armor Stand - Has Gravity", ExprsGravity.class, Boolean.class, "[has] gravity", "entity", Boolean.valueOf(false));
    registerNewSimpleExpression("Armor Stand - Is Small", ExprsSmall.class, Boolean.class, "[is] small", "entity", Boolean.valueOf(false));
    registerNewSimpleExpression("Armor Stand - Is Visible", ExprsVisible.class, Boolean.class, "[is] visible", "entity", Boolean.valueOf(false));
    registerNewSimpleExpression("Armor Stand - Right Leg X", ExprsRightLegDirectionX.class, Number.class, "right leg (x angle|angle x)", "entity", Boolean.valueOf(false));
    registerNewSimpleExpression("Armor Stand - Right Leg Y", ExprsRightLegDirectionY.class, Number.class, "right leg (y angle|angle y)", "entity", Boolean.valueOf(false));
    registerNewSimpleExpression("Armor Stand - Right Leg Z", ExprsRightLegDirectionZ.class, Number.class, "right leg (z angle|angle z)", "entity", Boolean.valueOf(false));
    registerNewSimpleExpression("Armor Stand - Left Leg X", ExprsLeftLegDirectionX.class, Number.class, "left leg (x angle|angle x)", "entity", Boolean.valueOf(false));
    registerNewSimpleExpression("Armor Stand - Left Leg Y", ExprsLeftLegDirectionY.class, Number.class, "left leg (y angle|angle y)", "entity", Boolean.valueOf(false));
    registerNewSimpleExpression("Armor Stand - Left Leg Z", ExprsLeftLegDirectionZ.class, Number.class, "left leg (z angle|angle z)", "entity", Boolean.valueOf(false));
    registerNewSimpleExpression("Armor Stand - Right Arm X", ExprsRightArmDirectionX.class, Number.class, "right arm (x angle|angle x)", "entity", Boolean.valueOf(false));
    registerNewSimpleExpression("Armor Stand - Right Arm Y", ExprsRightArmDirectionY.class, Number.class, "right arm (y angle|angle y)", "entity", Boolean.valueOf(false));
    registerNewSimpleExpression("Armor Stand - Right Arm Z", ExprsRightArmDirectionZ.class, Number.class, "right arm (z angle|angle z)", "entity", Boolean.valueOf(false));
    registerNewSimpleExpression("Armor Stand - Left Arm X", ExprsLeftArmDirectionX.class, Number.class, "left arm (x angle|angle x)", "entity", Boolean.valueOf(false));
    registerNewSimpleExpression("Armor Stand - Left Arm Y", ExprsLeftArmDirectionY.class, Number.class, "left arm (y angle|angle y)", "entity", Boolean.valueOf(false));
    registerNewSimpleExpression("Armor Stand - Left Arm Z", ExprsLeftArmDirectionZ.class, Number.class, "left arm (z angle|angle z)", "entity", Boolean.valueOf(false));
    registerNewSimpleExpression("Armor Stand - Head X", ExprsHeadDirectionX.class, Number.class, "head (x angle|angle x)", "entity", Boolean.valueOf(false));
    registerNewSimpleExpression("Armor Stand - Head Y", ExprsHeadDirectionY.class, Number.class, "head (y angle|angle y)", "entity", Boolean.valueOf(false));
    registerNewSimpleExpression("Armor Stand - Head Z", ExprsHeadDirectionZ.class, Number.class, "head (z angle|angle z)", "entity", Boolean.valueOf(false));
    registerNewSimpleExpression("Armor Stand - Body X", ExprsBodyDirectionX.class, Number.class, "body (x angle|angle x)", "entity", Boolean.valueOf(false));
    registerNewSimpleExpression("Armor Stand - Body Y", ExprsBodyDirectionY.class, Number.class, "body (y angle|angle y)", "entity", Boolean.valueOf(false));
    registerNewSimpleExpression("Armor Stand - Body Z", ExprsBodyDirectionZ.class, Number.class, "body (z angle|angle z)", "entity", Boolean.valueOf(false));
    registerNewExpression("ItemAttributeGeneric", "Attributes.ExprGenericItemAttribute", ItemStack.class, ExpressionType.SIMPLE, "%itemstack% with generic [item] attribute %string% (with value of|set to|value) %number%", Boolean.valueOf(true));
    registerNewExpression("ItemAttributePredefined", "Attributes.ExprStatItemAttribute", ItemStack.class, ExpressionType.SIMPLE, "%itemstack% with [item] attribute %entityattribute% (with value of|set to|value) %number% (using operation|operation|with operation) %nbtoperation%", Boolean.valueOf(true));
    
    registerNewSimpleExpression("Zombie Villager State", ExprZombieVillager.class, Boolean.class, "zombie villager state", "entity", Boolean.valueOf(false));
    
    registerNewExpression("Glow", "Misc.ExprBetterGlow", ItemStack.class, ExpressionType.SIMPLE, "[a[n]] [umbaska] glow[ing] %itemstack%", Boolean.valueOf(true));
    registerNewExpression("Absorption Hearts", "Misc.ExprAbsorptionHearts", Number.class, ExpressionType.SIMPLE, "absorption hearts of %player%", Boolean.valueOf(true));
    
    registerNewExpression("No AI Entity", "Misc.ExprNoAIEntity", Entity.class, ExpressionType.PROPERTY, "%entity% with no[( |-)]ai", Boolean.valueOf(true));
    
    registerNewExpression("Split All Characeters", ExprSplitAtAllCharacters.class, String.class, ExpressionType.COMBINED, new String[] { "%string% split at all characters" });
    
    registerNewExpression("Banner - New Banner", ExprNewBannerFrom.class, ItemStack.class, ExpressionType.COMBINED, new String[] { "%color% banner with layers" });
    registerNewExpression("Banner - New Banner", ExprNewBannerFrom.class, ItemStack.class, ExpressionType.COMBINED, new String[] { "banner colo[u]red %color% with layers" });
    registerNewExpression("Banner - New Layer", ExprNewLayer.class, ItemStack.class, ExpressionType.COMBINED, new String[] { "%itemstack% [(and|,)] colo[u]r[ed] %color% [(and|with)] pattern %bannerpattern%" });
    
    registerNewExpression("Banner - Layer of Block", ExprBannerLayer.class, Pattern.class, ExpressionType.SIMPLE, new String[] { "[pattern] layer %integer% of %block%" });
    if (use_bungee.booleanValue() == true)
    {
      registerNewExpression("Bungee - Bungee UUID", ExprBungeeUUID.class, UUID.class, ExpressionType.PROPERTY, new String[] { "Bungee uuid of %player%" });
      registerNewExpression("Bungee - All Servers", ExprAllServers.class, String.class, ExpressionType.SIMPLE, new String[] { "all Bungee[ ][cord] servers" });
      registerNewExpression("Bungee - All Players", ExprBungeeAllPlayers.class, String.class, ExpressionType.SIMPLE, new String[] { "all Bungee[ ][cord] players" });
      registerNewExpression("Bungee - Players on Server", ExprBungeePlayersOnServer.class, Integer.class, ExpressionType.SIMPLE, new String[] { "players on Bungee[ ][cord] server %string%" });
      registerNewExpression("Bungee - Server of Player", ExprBungeeServerOfPlayer.class, String.class, ExpressionType.SIMPLE, new String[] { "Bungee[ ][cord] server of %string%" });
      registerNewExpression("Bungee - Server Count", ExprBungeeServerCount.class, Integer.class, ExpressionType.SIMPLE, new String[] { "players on Bungee[ ][cord] proxy" });
    }
    registerNewExpression("Get Player from Fake Player", "ProtocolLib.FakePlayer.ExprGetPlayer", Player.class, ExpressionType.SIMPLE, "player from fake player %string%", Boolean.valueOf(true));
    
    registerNewExpression("JSON - JSON Message", ExprJsonMessage.class, JSONMessage.class, ExpressionType.SIMPLE, new String[] { "json [of] %string%" });
    registerNewExpression("JSON - Message Append", ExprJsonAppend.class, JSONMessage.class, ExpressionType.SIMPLE, new String[] { "%umbjsonmessage% then %string%" });
    registerNewExpression("JSON - Message Run/Suggest Command", ExprJsonMessageCommand.class, JSONMessage.class, ExpressionType.SIMPLE, new String[] { "%umbjsonmessage% suggest %string%", "%umbjsonmessage% run %string%" });
    registerNewExpression("JSON - Message Style", ExprJsonMessageStyle.class, JSONMessage.class, ExpressionType.SIMPLE, new String[] { "%umbjsonmessage% styled %colors%" });
    registerNewExpression("JSON - Show Tooltip", ExprJsonMessageTooltip.class, JSONMessage.class, ExpressionType.SIMPLE, new String[] { "%umbjsonmessage% tooltip %string%" });
    registerNewExpression("JSON - Open URL", ExprJsonMessageURL.class, JSONMessage.class, ExpressionType.SIMPLE, new String[] { "%umbjsonmessage% open %string%" });
  }
}
