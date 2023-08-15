package net.arcturusphoenix.f3n.appearance;

import org.bukkit.plugin.java.JavaPlugin;

public class ConsoleLogo {
  private JavaPlugin plugin;
  
  private Color color;
  
  private String prefix;
  
  public enum Color {
    AQUA("§b"),
    BLACK("§0"),
    BLUE("§9"),
    DARK_AQUA("§3"),
    DARK_BLUE("§1"),
    DARK_GRAY("§8"),
    DARK_GREEN("§2"),
    DARK_PURPLE("§5"),
    DARK_RED("§4"),
    GOLD("§6"),
    GRAY("§7"),
    GREEN("§a"),
    LIGHT_PURPLE("§d"),
    RED("§c"),
    WHITE("§f"),
    YELLOW("§e");
    
    private String color;
    
    Color(String color) {
      this.color = color;
    }
    
    public String get() {
      return this.color;
    }
  }
  
  public ConsoleLogo(JavaPlugin plugin, Color color) {
    this.plugin = plugin;
    this.color = color;
    this.prefix = String.valueOf(String.valueOf(this.color.get())) + "[" + Color.RED.get() + this.plugin.getName() + this.color.get() + "]";
  }
  
  public void info(String info) {
    this.plugin.getServer().getConsoleSender().sendMessage(String.valueOf(String.valueOf(this.prefix)) + ConsoleColor.colorCodes("&7 " + info));
  }
  
  public void line() {
    this.plugin.getServer().getConsoleSender().sendMessage(String.valueOf(String.valueOf(this.color.get())) + "-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
  }
  
  public void println(String string) {
    System.out.println(string);
  }
}