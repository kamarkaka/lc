package com.kamarkaka.cloudkitchen;

import java.math.BigDecimal;
import java.util.*;

/**
 * You have been tasked with parsing menus from a large restaurant group. Each menu is streamed to clients via a
 * provided interface. You must design object(s) that represents a menu and can be instantiated with data from the
 * provided interface. Your design should contain appropriate class structure to contain the parsed data, as well as a
 * function or set of functions to perform the parsing.
 * Consumers will use your object(s) to access a complete representation of the data sent by the menu stream after it
 * has finished loading. Your objects should provide easy access to the full representation of the menu. It should be
 * possible to reconstruct the menu stream from your object.
 * The menu stream represents a list of menu items. Each item in the stream is a menu item, and each item will be
 * separated by an empty string. The attributes of each item are as follows:
 *   Line 0: The ID of the item
 *   Line 1: The item type, either CATEGORY, ENTREE, or OPTION
 *   Line 2: The name of the item
 *   Line 3: The price of the item for ENTREE and OPTION. Not present for CATEGORY items.
 *   Any other line: A list of item IDs that are linked to the current item. OPTIONs do not have any linked items.
 * Stream Example:
 *   4, ENTREE, Spaghetti, 10.95, [2,3]
 *   1, CATEGORY, Pasta, [4,5]
 *   2, OPTION, Meatballs, 1.00
 *   3, OPTION, Chicken, 2.00
 *   5, ENTREE, Lasagna, 12.00
 *   6, ENTREE, Caesar Salad, 9.75, [3]
 *
 * Stream Interface Example:
 * interface MenuStream {
 *     String nextLine(); // Returns null when stream is empty
 * }
 */

interface MenuStream {
    String nextLine(); // Returns null when stream is empty
}

class DummyMenuStream implements MenuStream {
    private final List<String> contents;
    private int counter;

    public DummyMenuStream() {
        this.contents = new ArrayList<>();
        this.contents.add("4");
        this.contents.add("ENTREE");
        this.contents.add("Spaghetti");
        this.contents.add("10.95");
        this.contents.add("2");
        this.contents.add("3");
        this.contents.add("");
        this.contents.add("1");
        this.contents.add("CATEGORY");
        this.contents.add("Pasta");
        this.contents.add("4");
        this.contents.add("5");
        this.contents.add("");
        this.contents.add("2");
        this.contents.add("OPTION");
        this.contents.add("Meatballs");
        this.contents.add("1.00");
        this.contents.add("");
        this.contents.add("3");
        this.contents.add("OPTION");
        this.contents.add("Chicken");
        this.contents.add("2.00");
        this.contents.add("");
        this.contents.add("5");
        this.contents.add("ENTREE");
        this.contents.add("Lasagna");
        this.contents.add("12.00");
        this.contents.add("");
        this.contents.add("6");
        this.contents.add("ENTREE");
        this.contents.add("Caesar Salad");
        this.contents.add("9.75");
        this.contents.add("3");
        this.contents.add("");

        this.counter = 0;
    }

    public String nextLine() {
        if (this.counter == this.contents.size()) return null;
        return this.contents.get(this.counter++);
    }
}

enum MenuItemType {
    CATEGORY,
    ENTREE,
    OPTION
}

abstract class MenuItem {
    private final int id;
    private final MenuItemType type;
    private final String name;

    MenuItem(int id, MenuItemType type, String name) {
        this.id = id;
        this.type = type;
        this.name = name;
    }

    public int getId() { return this.id; }
    public MenuItemType getType() { return this.type; }
    public String getName() { return this.name; }

    @Override
    public String toString() {
        return "id: " + this.id + ", " +
               "type: " + this.type.toString() + ", " +
               "name: " + this.name;
    }
}

class EntreeMenuItem extends MenuItem {
    private final BigDecimal price;
    private final List<Integer> linkedItemIds;

    EntreeMenuItem(int id, String name, BigDecimal price, List<Integer> linkedItemIds) {
        super(id, MenuItemType.ENTREE, name);
        this.price = price;
        this.linkedItemIds = linkedItemIds;
    }

    public BigDecimal getPrice() { return price; }
    public List<Integer> getLinkedItemIds() { return linkedItemIds; }

    @Override
    public String toString() {
        return super.toString() +
               "price: " + this.getPrice() + ", " +
               "linked items: " + this.getLinkedItemIds();
    }
}

class CategoryMenuItem extends MenuItem {
    private final List<Integer> linkedItemIds;

    public CategoryMenuItem(int id, String name, List<Integer> linkedItemIds) {
        super(id, MenuItemType.CATEGORY, name);
        this.linkedItemIds = linkedItemIds;
    }

    public List<Integer> getLinkedItemIds() { return linkedItemIds; }

    @Override
    public String toString() {
        return super.toString() + ", " +
               "linked items: " + this.getLinkedItemIds();
    }
}

class OptionMenuItem extends MenuItem {
    private final BigDecimal price;

    public OptionMenuItem(int id, String name, BigDecimal price) {
        super(id, MenuItemType.OPTION, name);
        this.price = price;
    }

    public BigDecimal getPrice() { return price; }

    @Override
    public String toString() {
        return super.toString() + ", " +
               "price: " + this.getPrice();
    }
}

class MenuItemFactory {
    public static LinkedHashMap<Integer, MenuItem> parseMenuStream(MenuStream menuStream) {
        LinkedHashMap<Integer, MenuItem> menuItemMap = new LinkedHashMap<>();

        List<String> lines = new ArrayList<>();
        String line;
        while ((line = menuStream.nextLine()) != null) {
            if (line.isEmpty()) {
                MenuItem menuItem = parseMenuItem(lines);
                menuItemMap.put(menuItem.getId(), menuItem);
                lines.clear();
            } else {
                lines.add(line);
            }
        }
        return menuItemMap;
    }

    private static MenuItem parseMenuItem(List<String> lines) {
        int id = Integer.parseInt(lines.get(0));
        MenuItemType type = MenuItemType.valueOf(lines.get(1));
        String name = lines.get(2);
        BigDecimal price = BigDecimal.ZERO;
        List<Integer> linkedItemIds = new ArrayList<>();
        int startIndex = 3;

        if (type == MenuItemType.ENTREE || type == MenuItemType.OPTION) {
            price = new BigDecimal(lines.get(3));
            startIndex = 4;
        }

        if (type == MenuItemType.CATEGORY || type == MenuItemType.ENTREE) {
            for (int i = startIndex; i < lines.size(); i++) {
                linkedItemIds.add(Integer.parseInt(lines.get(i)));
            }
        }

        return switch (type) {
            case MenuItemType.CATEGORY -> new CategoryMenuItem(id, name, linkedItemIds);
            case MenuItemType.ENTREE -> new EntreeMenuItem(id, name, price, linkedItemIds);
            case MenuItemType.OPTION -> new OptionMenuItem(id, name, price);
        };
    }
}

public class Menu {
    public static void main(String[] args) {
        MenuStream menuStream = new DummyMenuStream();
        LinkedHashMap<Integer, MenuItem> result = MenuItemFactory.parseMenuStream(menuStream);
        for (int id : result.sequencedKeySet()) {
            MenuItem item = result.get(id);
            System.out.println(item);
        }
        System.exit(0);
    }
}
