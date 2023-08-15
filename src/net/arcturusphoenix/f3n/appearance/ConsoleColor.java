package net.arcturusphoenix.f3n.appearance;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ConsoleColor {
  public static String colorCodes(String nonColoredText) {
    if (nonColoredText == null)
      return nonColoredText; 
    if (nonColoredText.isEmpty())
      return nonColoredText; 
    if (nonColoredText.length() <= 0)
      return nonColoredText; 
    if (nonColoredText == "")
      return nonColoredText; 
    if (nonColoredText == " ")
      return nonColoredText; 
    String coloredText = ChatColor.translateAlternateColorCodes('&', nonColoredText);
    return coloredText;
  }
  
  public static void message(Player player, String paramString) {
    player.sendMessage(colorCodes(paramString));
  }
  
  public static void message(CommandSender player, String paramString) {
    player.sendMessage(colorCodes(paramString));
  }
  
  public static String coloriseTextComponentString(String string) {
    if (string == null || string.length() == 0)
      return " "; 
    String localString = colorCodes(string.trim());
    String newString = "";
    String last = "0";
    if (localString.contains(" ")) {
      String[] frases = localString.split(" ");
      for (int i = 0; i < frases.length; i++) {
        String frase = frases[i].trim();
        if (frase.startsWith("0")) {
          newString = String.valueOf(String.valueOf(newString)) + " " + frase;
        } else {
          newString = String.valueOf(String.valueOf(newString)) + " " + last + frase;
        } 
        for (int j = 0; j < frase.length(); j++) {
          char c = frase.charAt(j);
          char m = (frase.length() > 2) ? frase.charAt(j + 2) : ' ';
          if (c == '0' && m == '0') {
            last = "0" + frase.charAt(j + 1) + "0" + frase.charAt(j + 3);
            j = 3;
          } else if (c == '0') {
            last = "0" + frase.charAt(j + 1);
            j = 1;
          } 
        } 
      } 
      return newString.trim();
    } 
    return localString;
  }
  
  public static List<String> rColorList(List<String> paramList) {
    List<String> s = new ArrayList<>();
    s.addAll(paramList);
    for (int i = 0; i < s.size(); i++) {
      String p = colorCodes(s.get(i));
      s.set(i, p);
    } 
    return s;
  }
  
  public static List<String> getAlternateColorList() {
    List<String> c = new ArrayList<>();
    c.add("&0");
    c.add("&1");
    c.add("&2");
    c.add("&3");
    c.add("&4");
    c.add("&5");
    c.add("&6");
    c.add("&7");
    c.add("&8");
    c.add("&9");
    c.add("&a");
    c.add("&b");
    c.add("&c");
    c.add("&d");
    c.add("&e");
    c.add("&f");
    c.add("&k");
    c.add("&l");
    c.add("&m");
    c.add("&n");
    c.add("&o");
    c.add("&r");
    c.add("&A");
    c.add("&B");
    c.add("&C");
    c.add("&D");
    c.add("&E");
    c.add("&F");
    c.add("&K");
    c.add("&L");
    c.add("&M");
    c.add("&N");
    c.add("&O");
    c.add("&R");
    return c;
  }
  
  public static List<String> getNativeColorList() {
    List<String> c = new ArrayList<>();
    c.add("§0");
    c.add("§1");
    c.add("§2");
    c.add("§3");
    c.add("§4");
    c.add("§5");
    c.add("§6");
    c.add("§7");
    c.add("§8");
    c.add("§9");
    c.add("§a");
    c.add("§b");
    c.add("§c");
    c.add("§d");
    c.add("§e");
    c.add("§f");
    c.add("§k");
    c.add("§l");
    c.add("§m");
    c.add("§n");
    c.add("§o");
    c.add("§r");
    c.add("§A");
    c.add("§B");
    c.add("§C");
    c.add("§D");
    c.add("§E");
    c.add("§F");
    c.add("§K");
    c.add("§L");
    c.add("§M");
    c.add("§N");
    c.add("§O");
    c.add("§R");
    return c;
  }
  
  public static String clearColor(String coloredText) {
    String nonColoredText = coloredText;
    for (String color : getAlternateColorList())
      nonColoredText = nonColoredText.replaceAll(color, ""); 
    for (String color : getNativeColorList())
      nonColoredText = nonColoredText.replaceAll(color, ""); 
    return nonColoredText.trim();
  }
}