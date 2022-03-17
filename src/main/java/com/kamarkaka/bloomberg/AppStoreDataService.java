package com.kamarkaka.bloomberg;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

class AppStoreEntry {
   String appId;
   String appName;
   String category;
   String publisherId;
   String publisherName;
}

public class AppStoreDataService {
   private Map<String, AppStoreEntry> _data = new HashMap<>();

   public void addNewApp(AppStoreEntry data) {}
   public void removeApp(String appId) {}
   public String getAppName(String appId) { return null; }
   public String getPublisherName(String publisherId) { return null; }
   public List<String> getApps(String publisherId) { return null; }
   public void updatePublisherName(String publisherId, String newName) {}
}
