package crystalcube.buildingvisualize;

import crystalcube.buildingvisualize.Builderr.Builder;
import crystalcube.buildingvisualize.Json.JsonManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class MyCommandExecutor implements CommandExecutor, TabCompleter {
    private final BuildingVisualize plugin;
    private final MyConfig config;

    private final HashMap<String, String> commands = new HashMap<>(){{
        put("help", "Shows plugin commands");
        put("reload", "Reload plugin config");
        put("print", "Test");
        put("build", "Build a house");
        put("getAxe", "Give selector to player");
        put("save", "<name> Saves selected cube");
    }};

    public MyCommandExecutor(BuildingVisualize plugin) {
        this.plugin = plugin;
        config = plugin.getMyConfig();
    }

    public String showHelpCommand(String name){
        return new String(ChatColor.GRAY + "- " + ChatColor.GOLD + "/build " + name + "\n" +
                ChatColor.WHITE + "     " + commands.get(name) + "\n");
    }

    public String showHelp() {
        StringBuilder msg = new StringBuilder(ChatColor.DARK_GRAY + "==============[ " + ChatColor.YELLOW + "Building Visualize" +
                ChatColor.DARK_GRAY + " ]==============\n");
        for (var command : commands.entrySet()) {
            msg.append(showHelpCommand(command.getKey()));
        }
        return msg.toString();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("build")) {
            switch (args[0].toLowerCase()){
                case "help":
                    if (args.length == 2 && commands.containsKey(args[1]))
                        sender.sendMessage(showHelpCommand(args[1]));
                    else
                        sender.sendMessage(showHelp());
                    return true;
                case "reload":
                    config.loadConfig();
                    sender.sendMessage("[BV]Plugin is reload");
                    return true;
                case "print":
                    config.setIsEnabled(!config.isEnabled());
                    sender.sendMessage("[BV]Switch is " + (config.isEnabled()?"enabled": "disabled"));
                    return true;
                case "getaxe":
                    if (!(sender instanceof Player)){
                        sender.sendMessage("[BV]Only player can use this command");
                        return true;
                    }
                    if (plugin.getAxeManagerConfig().give((Player) sender))
                        sender.sendMessage("[BV]Get your axe");
                    else
                        sender.sendMessage("[BV]Your hand must be empty");
                    return true;
                case "build":
                    if (!(sender instanceof Player)){
                        sender.sendMessage("[BV]Only player can use this command");
                        return true;
                    }
                    if (plugin.getAxeManagerConfig().GetLastLeftMouseClick(((Player) sender).getUniqueId()) != null){
                        if (args.length > 1) {
                            var corner = plugin.getAxeManagerConfig().GetLastLeftMouseClick(((Player) sender).getUniqueId());
                            Builder.BuildTileModel(((Player)sender).getWorld(), plugin.getModel(args[1]), corner);
                            return true;
                        }
                        var jsonManager = new JsonManager(Objects.requireNonNull(plugin.getResource("well.json")));
                        var corner = plugin.getAxeManagerConfig().GetLastLeftMouseClick(((Player) sender).getUniqueId());
                        Builder.Build(((Player)sender).getWorld(), jsonManager.TileSet, corner, sender, plugin);
                        sender.sendMessage("[BV]Build is built");
                        return true;
                    }
                    sender.sendMessage("[BV]Select first point");
                    return true;
                case "save":
                    if (!(sender instanceof Player)){
                        sender.sendMessage("[BV]Only player can use this command");
                        return true;
                    }
                    if (args.length == 1){
                        sender.sendMessage("[BV]Enter model name");
                        return true;
                    }
                    if (plugin.getAxeManagerConfig().GetLastRightMouseClick(((Player) sender).getUniqueId()) == null ||
                            plugin.getAxeManagerConfig().GetLastLeftMouseClick(((Player) sender).getUniqueId()) == null){
                        sender.sendMessage("[BV] Select points");
                        return true;
                    }
                    plugin.saveModel(args[1], plugin.getAxeManagerConfig().GetLastRightMouseClick(((Player) sender).getUniqueId()),
                            plugin.getAxeManagerConfig().GetLastLeftMouseClick(((Player) sender).getUniqueId()));
                    sender.sendMessage("[BV]" + args[1] + " saved");
                    return true;
                default:
                    sender.sendMessage(showHelp());
                    return true;
            }
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        //You have to replace the "1" in base of the argument position
        if(args.length==1) {
            List<String> result = new ArrayList<>();
            commands.keySet().forEach(s->{if(s.toLowerCase().startsWith(args[0])) result.add(s);});
            return result;
        }
        return null;
    }
}