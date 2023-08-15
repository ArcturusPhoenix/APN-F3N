package net.arcturusphoenix.f3n.main;

import net.arcturusphoenix.f3n.appearance.ConsoleLogo;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public final class Main extends JavaPlugin implements Listener {
	  private Reflector reflector;
	  
	  private Logger logger;
	  
	  private boolean bypassPermissionChecking;
	  
	  public ConsoleLogo log;
	  
	  public void onLoad() {
	    this.logger = getLogger();
	    this.bypassPermissionChecking = (new File(".bypass_permission_checking")).exists(); 
	    try {
	      this.reflector = new Reflector_1_17();      
	    } catch (Exception e) {
	      try {
	        this.reflector = new Reflector_1_8();
	      } catch (Exception e2) {
			this.logger.log(Level.SEVERE, "ArcturusPhoenixF3N konnte nicht geladen werden. Bitte überprüfe die Version des Servers.", e);
	      }
	    }
	  }

public void onEnable() {
    if (this.reflector != null) {
      Bukkit.getPluginManager().registerEvents(this, (Plugin)this);
      System.out.println("ArcturusPhoenixF3N startet...");
      this.log = new ConsoleLogo(this, ConsoleLogo.Color.DARK_RED);
      this.log.line();
      this.log.println("___  ______      ______ _____ _   _ ");
      this.log.println("/ _ \\ | ___ \\     |  ___|____ | \\ | |");
      this.log.println("/ /_\\ \\| |_/ /_____| |_      / /  \\| |");
      this.log.println("|  _  ||  __/______|  _|     \\ \\ . ` |");
      this.log.println("| | | || |         | |   .___/ / |\\  |");
      this.log.println("\\_| |_/\\_|         \\_|   \\____/\\_| \\_/");
      this.log.println("                                    ");
      this.log.line();
      this.log.info("Plugin Version: v" + getDescription().getVersion());
    } 
  }

public void onDisable() {
    this.logger.info("ArcturusPhoenixF3N wird gestoppt...");
  }
  
  @EventHandler
  public void onJoin(PlayerJoinEvent event) {
    checkF3NPermUpdate(event.getPlayer());
  }
  
  @EventHandler
  public void onWorldChange(PlayerChangedWorldEvent event) {
    checkF3NPermUpdate(event.getPlayer());
  }
  
  @EventHandler
  public void onRespawn(PlayerRespawnEvent event) {
    checkF3NPermUpdate(event.getPlayer());
  }
  
  private void checkF3NPermUpdate(final Player player) {
    if (this.bypassPermissionChecking || player.hasPermission("APNF3N.use"))
      (new BukkitRunnable() {
          public void run() {
            Main.this.reflector.sendEntityStatus(player);
          }
        }).runTaskLater((Plugin)this, 10L); 
  }
  
  private static abstract class Reflector {
	private static final byte STATUS_BYTE = 28;
	
    protected Class<?> entityStatusPacketClass;
    
    protected Class<?> playerConnectionClass;
    
    protected Class<?> entityClass;
    
    protected Class<?> packetClass;
    
    protected String playerConnectionField;
    
    private Reflector() {}
    
    public void sendEntityStatus(Player p) {
      try {
        Object entityPlayer = p.getClass().getDeclaredMethod("getHandle", new Class[0]).invoke(p, new Object[0]);
        Object playerConnection = entityPlayer.getClass().getDeclaredField(this.playerConnectionField).get(entityPlayer);
        Object packet = this.entityStatusPacketClass.getConstructor(new Class[] { this.entityClass, byte.class }).newInstance(new Object[] { entityPlayer, Byte.valueOf((byte)28) });
        this.playerConnectionClass.getDeclaredMethod("sendPacket", new Class[] { this.packetClass }).invoke(playerConnection, new Object[] { packet });
      } catch (Throwable e) {
        throw new RuntimeException("Error while sending entity status 28", e);
      } 
    }
  }
  
  private static class Reflector_1_8 extends Reflector {
    private Reflector_1_8() throws ClassNotFoundException {
      //super(null);
      String namespace = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];
      this.entityStatusPacketClass = Class.forName("net.minecraft.server." + namespace + ".PacketPlayOutEntityStatus");
      this.playerConnectionClass = Class.forName("net.minecraft.server." + namespace + ".PlayerConnection");
      this.entityClass = Class.forName("net.minecraft.server." + namespace + ".Entity");
      this.packetClass = Class.forName("net.minecraft.server." + namespace + ".Packet");
      this.playerConnectionField = "playerConnection";
    }
  }
  
  private static class Reflector_1_17 extends Reflector {
    private Reflector_1_17() throws ClassNotFoundException {
      //super(null);
      this.entityStatusPacketClass = Class.forName("net.minecraft.network.protocol.game.PacketPlayOutEntityStatus");
      this.playerConnectionClass = Class.forName("net.minecraft.server.network.PlayerConnection");
      this.entityClass = Class.forName("net.minecraft.world.entity.Entity");
      this.packetClass = Class.forName("net.minecraft.network.protocol.Packet");
      this.playerConnectionField = "b";
    }
  }
}
