package com.kamarkaka.google;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConvertEmailFoldersToGmailLabels {
   private Folder root;
   private final Map<Integer, Folder> folderMap;

   public ConvertEmailFoldersToGmailLabels() {
      this.root = null;
      this.folderMap = new HashMap<>();
   }

   public void addFolder(int id, int parentId, String displayName) {
      Folder folder = new Folder(id, parentId, displayName);
      folderMap.put(id, folder);
      if (folder.parentId == -1) root = folder;
   }

   public List<Label> convert() {
      List<Label> res = new ArrayList<>();

      dfs(root, "", res);

      return res;
   }

   public void buildTree() {
      for (Map.Entry<Integer, Folder> entry : folderMap.entrySet()) {
         int id = entry.getKey();
         Folder folder = entry.getValue();
         if (folder.parentId == -1) continue;

         Folder parent = folderMap.get(folder.parentId);
         parent.subFolderIds.add(id);
      }
   }

   private void dfs(Folder folder, String label, List<Label> res) {
      String displayName = label;
      if (!displayName.isEmpty()) displayName += "/";
      displayName += folder.displayName;

      if (folderMap.containsKey(folder.id)) {
         res.add(new Label(displayName));
      }

      for (int id : folder.subFolderIds) {
         Folder subFolder = folderMap.get(id);
         dfs(subFolder, displayName, res);
      }
   }

   public static void run() {
      ConvertEmailFoldersToGmailLabels sol = new ConvertEmailFoldersToGmailLabels();
      sol.addFolder(15, -1, "subscriptions");
      sol.addFolder(27, 15, "comics");
      sol.addFolder(35, 27, "xkcd");
      sol.addFolder(81, 27, "oatmeal");
      sol.addFolder(89, 35, "test");
      sol.buildTree();
      List<Label> labels = sol.convert();
      for (Label label : labels) {
         System.out.println(label.displayName);
      }
   }
}

class Folder {
   int id;
   int parentId;
   String displayName;
   List<Integer> subFolderIds;

   public Folder(int id, int parentId, String displayName) {
      this.id = id;
      this.parentId = parentId;
      this.displayName = displayName;
      this.subFolderIds = new ArrayList<>();
   }
}

class Label {
   String displayName;

   public Label(String displayName) {
      this.displayName = displayName;
   }
}