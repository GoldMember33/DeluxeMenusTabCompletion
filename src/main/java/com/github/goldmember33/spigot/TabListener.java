package com.github.goldmember33.spigot;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.TabCompleteEvent;

import java.util.ArrayList;
import java.util.HashMap;

public class TabListener implements Listener {

    private DeluxeMenusTab main;
    public static HashMap<Player, ArrayList<String>> tab = new HashMap<>();

    public TabListener(DeluxeMenusTab main) {
        this.main = main;
    }

    @EventHandler
    public void onTabComplete(TabCompleteEvent e) {
        if (!(e.getSender() instanceof Player player)) {
            return;
        }

        String buffer = e.getBuffer();
        if (!getS(buffer, 0).equals("dm") &&
                !getS(e.getBuffer(), 0).equals("dmenu") &&
                !getS(e.getBuffer(), 0).equals("deluxemenu") &&
                !getS(e.getBuffer(), 0).equals("deluxemenus") &&
                !getS(e.getBuffer(), 0).equals("deluxemenus:dm") &&
                !getS(e.getBuffer(), 0).equals("deluxemenus:dmenu") &&
                !getS(e.getBuffer(), 0).equals("deluxemenus:deluxemenu") &&
                !getS(e.getBuffer(), 0).equals("deluxemenus:deluxemenus")) {
            return;
        }

        if (!isNewArg(buffer)) {
            if (tab.containsKey(player)) {
                e.setCompletions(tab.get(player));
            }

            return;
        }

        ArrayList<String> list = new ArrayList<>();

        // First subCommand
        if (length(buffer) == 2) {
            list.add("help");
            list.add("open");
            list.add("list");
            list.add("execute");
            list.add("dump");
            list.add("reload");
            if (tab.containsKey(player)) {
                tab.replace(player, list);
            } else {
                tab.put(player, list);
            }

            return;
        }

        if (length(buffer) == 3) {
            ArrayList<String> list1 = new ArrayList<>();
            if (compare(buffer, 1, "open", "reload")) {
                list1.addAll(main.retrieveMenuIdsFromConfig());
                list1.add("<menu>");
            }

            else if (compare(buffer, 1, "execute")) {
                Bukkit.getOnlinePlayers().forEach(p -> list1.add(p.getName()));
                list.add("<player>");
            }

            else if (compare(buffer, 1, "dump")) {
                list1.addAll(main.retrieveMenuIdsFromConfig());
                list1.add("config");
                list1.add("<menu-name>");
            }

            if (tab.containsKey(player)) {
                tab.replace(player, list1);
            } else {
                tab.put(player, list1);
            }

            e.setCompletions(list1);
            return;
        }

        if (length(buffer) == 4) {
            ArrayList<String> list1 = new ArrayList<>();
            if (compare(buffer, 1, "open", "reload")) {
                Bukkit.getOnlinePlayers().forEach(p -> list1.add(p.getName()));
                list.add("<player>");
                list1.add("-p:<target>");
                list1.add("<viewer>");
            }

            else if (compare(buffer, 1, "execute")) {
                list1.addAll(getActionsCompletions());
                list1.add("<action>");
            }

            if (tab.containsKey(player)) {
                tab.replace(player, list1);
            } else {
                tab.put(player, list1);
            }

            e.setCompletions(list1);
            return;
        }

        if (length(buffer) == 5) {
            ArrayList<String> list1 = new ArrayList<>();
            if (compare(buffer, 1, "execute")) {

                if (compare(buffer, 3,
                        "[player]",
                        "[console]",
                        "[commandevent]")) {
                    list1.add("<command>");


                } else if (compare(buffer, 3, "[placeholder]")) {
                    list1.add("<papi-placeholders>");
                    
                } else if (compare(buffer, 3,
                        "[message]",
                                "[broadcast]",
                                "[minimessage]",
                                "[minibroadcast]")) {

                    list1.add("<text>");

                } else if (compare(buffer, 3, "[openguimenu]")) {
                    list1.addAll(main.retrieveMenuIdsFromConfig());

                } else if (compare(buffer, 3, "[connect]")) {
                    list1.add("<server-name>");
                    
                } else if (compare(buffer, 3,
                        "[json]",
                                "[jsonbroadcast]")) {

                    list1.add("<JSON-text>");

                } else if (compare(buffer, 3,
                        "[broadcastsound]",
                                "[broadcastsoundworld]",
                                "[sound]")) {

                    list1.add("<sound>");
                    for (Sound sound : Sound.values()) {
                        list1.add(sound.name());
                    }

                } else if (compare(buffer, 3,
                        "[takemoney]", "[givemoney]")) {

                    list1.add("<amount>");

                } else if (compare(buffer, 3,
                        "[takeexp]",
                                "[giveexp]")) {

                    list1.add("#L");

                } else if (compare(buffer, 3,
                        "[givepermission]",
                                "[takepermission]")) {

                    list1.add("<perm.node>");

                } else if (compare(buffer, 3, "[meta]")) {

                    list1.add("set");
                    list1.add("remove");
                    list.add("add");
                    list1.add("subtract");
                    list.add("switch");

                } else if (compare(buffer, 3, "[chat]")) {
                    list1.add("<message>");
                }

                if (tab.containsKey(player)) {
                    tab.replace(player, list1);
                } else {
                    tab.put(player, list1);
                }

                e.setCompletions(list1);
                return;
            }
        }

        if (length(buffer) == 6) {
            ArrayList<String> list1 = new ArrayList<>();
            if (compare(buffer, 3,
                    "[broadcastsound]",
                    "[broadcastsoundworld]",
                    "[sound]")) {

                list1.add("<volume>");

            } else if (compare(buffer, 3, "[meta]")) {
                list1.add("<key>");
            }

            if (tab.containsKey(player)) {
                tab.replace(player, list1);
            } else {
                tab.put(player, list1);
            }

            e.setCompletions(list1);
            return;
        }

        if (length(buffer) == 7) {
            ArrayList<String> list1 = new ArrayList<>();
            if (compare(buffer, 3,
                    "[broadcastsound]",
                    "[broadcastsoundworld]",
                    "[sound]")) {

                list1.add("<pitch>");

            } else if (compare(buffer, 3, "[meta]")) {
                list1.add("<type>");
            }

            if (tab.containsKey(player)) {
                tab.replace(player, list1);
            } else {
                tab.put(player, list1);
            }

            e.setCompletions(list1);
            return;
        }

        if (length(buffer) == 8) {
            ArrayList<String> list1 = new ArrayList<>();
            if (compare(buffer, 3, "[meta]")) {
                list1.add("<value>");
            }

            if (tab.containsKey(player)) {
                tab.replace(player, list1);
            } else {
                tab.put(player, list1);
            }

            e.setCompletions(list1);
        }
    }

    //Get Command and Subcommand from Buffer
    public String getS(String buffer, int subCommand) {
        String s2 = buffer.replace("/", "");
        String[] s3 = s2.split(" ");
        return s3[subCommand];
    }

    public boolean isNewArg(String buffer) {
        String s2 = buffer.replace("/", "");
        String[] s3 = s2.split("(?= )");
        return s3[s3.length - 1].equals(" ");
    }

    public boolean compare(String buffer, int subCommand, String... strings) {
        String string = getS(buffer, subCommand);
        for (String s : strings) {
            if (s.equalsIgnoreCase(string)) {
                return true;
            }
        }

        return false;
    }

    public int length(String buffer) {
        String s2 = buffer.replace("/", "");
        String[] s3 = s2.split("(?= )");
        return s3.length;
    }

    public ArrayList<String> getActionsCompletions() {
        ArrayList<String> list = new ArrayList<>();
        list.add("<action>");
        list.add("[player]");
        list.add("[console]");
        list.add("[commandevent]");
        list.add("[placeholder]");
        list.add("[message]");
        list.add("[broadcast]");
        list.add("[minimessage]");
        list.add("[minibroadcast]");
        list.add("[openguimenu]");
        list.add("[connect]");
        list.add("[close]");
        list.add("[json]");
        list.add("[jsonbroadcast]");
        list.add("[refresh]");
        list.add("[broadcastsound]");
        list.add("[broadcastsoundworld]");
        list.add("[sound]");
        list.add("[takemoney]");
        list.add("[givemoney]");
        list.add("[takeexp]");
        list.add("[giveexp]");
        list.add("[givepermission]");
        list.add("[takepermission]");
        list.add("[meta]");
        list.add("[chat]");

        return list;
    }
}
