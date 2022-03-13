package com.kamarkaka.twosigma;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class RPNCalculator {
   private final TokenFactory tokenFactory;
   private final Stack<Double> stack;

   public RPNCalculator() {
      this.tokenFactory = new TokenFactory();
      this.stack = new Stack<>();
   }

   public Double calculate(List<String> formula) throws IllegalArgumentException {
      if (formula == null || formula.size() == 0) return 0.0;

      for (String s : formula) {
         tokenFactory.getToken(s).process(stack);
      }

      Double result = stack.pop();
      if (!stack.isEmpty()) throw new IllegalArgumentException();
      return result;
   }
}

class TokenFactory {
   private final Map<String, Operator<Double>> operators;

   public TokenFactory() {
      this.operators = new HashMap<>();
      operators.put("+", new Add());
      operators.put("-", new Subtract());
      operators.put("*", new Multiply());
      operators.put("/", new Divide());
   }

   public Token<Double> getToken(String token) throws IllegalArgumentException {
      if (operators.containsKey(token)) {
         return operators.get(token);
      } else {
         try {
            Double operand = Double.parseDouble(token);
            return new Operand<Double>(operand);
         } catch(Exception e) {
            throw new IllegalArgumentException(e.toString());
         }
      }
   }
}

abstract class Token<T> {
   public Token() {}
   abstract boolean isOperator();
   abstract void process(Stack<T> stack) throws IllegalArgumentException;
}

class Operand<T> extends Token<T> {
   private final T value;

   public Operand(final T operand) {
      this.value = operand;
   }

   public T getValue() {
      return this.value;
   }

   public boolean isOperator() {
      return false;
   }

   public void process(final Stack<T> stack) {
      stack.push(this.value);
   }
}

abstract class Operator<T> extends Token<T> {
   private final String type;

   public Operator(final String type) {
      this.type = type;
   }

   public String type() {
      return this.type;
   }

   public boolean isOperator() {
      return true;
   }
}

class Add extends Operator<Double> {
   public Add() {
      super("+");
   }

   @Override
   public void process(final Stack<Double> stack) throws IllegalArgumentException {
      if (stack.isEmpty())
         throw new IllegalArgumentException("Input stack should not be empty.");
      final Double num1 = stack.pop();
      if (stack.isEmpty())
         throw new IllegalArgumentException("Input stack should contain at least 2 numbers.");
      final Double num2 = stack.pop();
      stack.push(num1 + num2);
   }
}

class Subtract extends Operator<Double> {
   public Subtract() {
      super("-");
   }

   @Override
   public void process(final Stack<Double> stack) throws IllegalArgumentException {
      if (stack.isEmpty())
         throw new IllegalArgumentException("Input stack should not be empty.");
      final Double num1 = stack.pop();
      if (stack.isEmpty())
         throw new IllegalArgumentException("Input stack should contain at least 2 numbers.");
      final Double num2 = stack.pop();
      stack.push(num1 - num2);
   }
}

class Multiply extends Operator<Double> {
   public Multiply() {
      super("*");
   }

   @Override
   public void process(final Stack<Double> stack) throws IllegalArgumentException {
      if (stack.isEmpty())
         throw new IllegalArgumentException("Input stack should not be empty.");
      final Double num1 = stack.pop();
      if (stack.isEmpty())
         throw new IllegalArgumentException("Input stack should contain at least 2 numbers.");
      final Double num2 = stack.pop();
      stack.push(num1 * num2);
   }
}

class Divide extends Operator<Double> {
   public Divide() {
      super("/");
   }

   @Override
   public void process(final Stack<Double> stack) throws IllegalArgumentException {
      if (stack.isEmpty())
         throw new IllegalArgumentException("Input stack should not be empty.");
      final Double num1 = stack.pop();
      if (stack.isEmpty())
         throw new IllegalArgumentException("Input stack should contain at least 2 numbers.");
      final Double num2 = stack.pop();
      if (num2 == 0) throw new IllegalArgumentException("Input demoninator should not equal to 0.");
      stack.push(num1 / num2);
   }
}
