package com.kamarkaka.leetcode;

import java.util.*;

/***
 * 2115. Find All Possible Recipes from Given Supplies
 * You have information about n different recipes. You are given a string array recipes and a 2D string array ingredients. The ith recipe has the name recipes[i], and you can create it if you have all the needed ingredients from ingredients[i]. Ingredients to a recipe may need to be created from other recipes, i.e., ingredients[i] may contain a string that is in recipes.
 * You are also given a string array supplies containing all the ingredients that you initially have, and you have an infinite supply of all of them.
 * Return a list of all the recipes that you can create. You may return the answer in any order.
 * Note that two recipes may contain each other in their ingredients.
 *
 * Example 1:
 *    Input: recipes = ["bread"], ingredients = [["yeast","flour"]], supplies = ["yeast","flour","corn"]
 *    Output: ["bread"]
 *    Explanation:
 *       We can create "bread" since we have the ingredients "yeast" and "flour".
 *
 * Example 2:
 *    Input: recipes = ["bread","sandwich"], ingredients = [["yeast","flour"],["bread","meat"]], supplies = ["yeast","flour","meat"]
 *    Output: ["bread","sandwich"]
 *    Explanation:
 *       We can create "bread" since we have the ingredients "yeast" and "flour".
 *       We can create "sandwich" since we have the ingredient "meat" and can create the ingredient "bread".
 *
 * Example 3:
 *    Input: recipes = ["bread","sandwich","burger"], ingredients = [["yeast","flour"],["bread","meat"],["sandwich","meat","bread"]], supplies = ["yeast","flour","meat"]
 *    Output: ["bread","sandwich","burger"]
 *    Explanation:
 *       We can create "bread" since we have the ingredients "yeast" and "flour".
 *       We can create "sandwich" since we have the ingredient "meat" and can create the ingredient "bread".
 *       We can create "burger" since we have the ingredient "meat" and can create the ingredients "bread" and "sandwich".
 *
 * Constraints:
 *    n == recipes.length == ingredients.length
 *    1 <= n <= 100
 *    1 <= ingredients[i].length, supplies.length <= 100
 *    1 <= recipes[i].length, ingredients[i][j].length, supplies[k].length <= 10
 *    recipes[i], ingredients[i][j], and supplies[k] consist only of lowercase English letters.
 *    All the values of recipes and supplies combined are unique.
 *    Each ingredients[i] does not contain any duplicate values.
 */
public class LC2115 {
   Map<String, Integer> recipeMap;
   List<List<String>> ingredients;
   Set<String> supplySet;
   int[] states;

   public List<String> findAllRecipes(String[] recipes, List<List<String>> ingredients, String[] supplies) {
      List<String> res = new ArrayList<>();

      Set<String> supplySet = new HashSet<>(Arrays.asList(supplies));
      Queue<Integer> queue = new LinkedList<>();
      for (int i = 0; i < recipes.length; i++) {
         queue.add(i);
      }

      int size = queue.size();
      Queue<Integer> nextQueue = new LinkedList<>();

      while (!queue.isEmpty()) {
         int idx = queue.poll();
         String receipe = recipes[idx];
         List<String> ingredient = ingredients.get(idx);

         if (supplySet.containsAll(ingredient)) {
            res.add(receipe);
            supplySet.add(receipe);
         } else {
            nextQueue.add(idx);
         }

         if (queue.isEmpty() && nextQueue.size() < size) {
            queue = nextQueue;
            size = queue.size();
            nextQueue = new LinkedList<>();
         }
      }

      return res;
   }

   public List<String> findAllRecipes2(String[] recipes, List<List<String>> ingredients, String[] supplies) {
      this.recipeMap = new HashMap<>();
      for (int i = 0; i < recipes.length; i++) {
         this.recipeMap.put(recipes[i], i);
      }

      this.ingredients = ingredients;
      this.supplySet = new HashSet<>(Arrays.asList(supplies));
      this.states = new int[recipes.length];

      List<String> res = new ArrayList<>();
      for (String recipe : recipes) {
         if (canMake(recipe)) {
            res.add(recipe);
         }
      }
      return res;
   }

   private boolean canMake(String curRecipe) {
      if (!recipeMap.containsKey(curRecipe)) return false;
      int idx = recipeMap.get(curRecipe);
      if (states[idx] == 2) return true;
      if (states[idx] == 1) return false;
      states[idx] = 1;

      List<String> curIngredients = ingredients.get(idx);
      for (String curIngredient : curIngredients) {
         if (!supplySet.contains(curIngredient) &&
                   !canMake(curIngredient)) {
            return false;
         }
      }

      states[idx] = 2;
      return true;
   }
}
