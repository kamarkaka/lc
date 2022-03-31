package com.kamarkaka.linkedin;

import java.util.*;

public class RetainBestCache<K, T extends Rankable> {
   private Map<K, T> cache;
   private TreeMap<Long, Set<K>> rankingOfObject;
   private DataSource<K, T> dataSource;
   private int maxSizeOfCache;

   /* Constructor with a data source (assumed to be slow) and a cache size */
   public RetainBestCache(DataSource<K, T> ds, int entriesToRetain) {
      //impliment here
      this.cache = new HashMap<>();
      this.rankingOfObject = new TreeMap<>();
      this.dataSource = ds;
      this.maxSizeOfCache = entriesToRetain;
   }

   /* Gets some data. If possible, retrieves it from cache to be fast. If the data is not cached,
    * retrieves it from the data source. If the cache is full, attempt to cache the returned data,
    * evicting the T with lowest rank among the ones that it has available
    * If there is a tie, the cache may choose any T with lowest rank to evict.
    */
   public T get(K key) {
      //impliment here
      if (cache.containsKey(key)) return cache.get(key);

      return fetchDataFromDs(key);
   }

   private T fetchDataFromDs(K key) {
      if (cache.size() >= maxSizeOfCache) evictElement();

      T value = dataSource.get(key);
      cache.put(key, value);
      long rank = value.getRank();
      rankingOfObject.putIfAbsent(rank, new HashSet<>());
      rankingOfObject.get(rank).add(key);
      return value;
   }

   private void evictElement() {
      Map.Entry<Long, Set<K>> entry = rankingOfObject.firstEntry();
      K key = entry.getValue().iterator().next();
      entry.getValue().remove(key);
      cache.remove(key);
      if (entry.getValue().size() == 0) {
         rankingOfObject.remove(entry.getKey());
      }
   }
}

/*
 * For reference, here are the Rankable and DataSource interfaces.
 * You do not need to implement them, and should not make assumptions
 * about their implementations.
 */
interface Rankable {
   /**
    * Returns the Rank of this object, using some algorithm and potentially
    * the internal state of the Rankable.
    */
   long getRank();
}
interface DataSource<K, T extends Rankable> {
   T get(K key);
}
