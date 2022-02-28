package com.kamarkaka.stripe;

/***
 * At Stripe we keep track of where the money is and move money between bank accounts to make sure their balances are not below some threshold.
 * This is for operational and regulatory reasons, e.g. we should have enough funds to pay out to our users, and we are legally required to separate our users' funds from our own.
 * This interview question is a simplified version of a real-world problem we have here.
 * Let's say there are at most 500 bank accounts, some of their balances are above 100 and some are below.
 * How do you move money between them so that they all have at least 100?
 * Just to be clear we are not looking for the optimal solution, but a working one.
 *
 * Example input:
 * - AU: 80
 * - US: 140
 * - MX: 110
 * - SG: 120
 * - FR: 70
 * Output:
 * - from: US, to: AU, amount: 20
 * - from: US, to: FR, amount: 20
 * - from: MX, to: FR, amount: 10
 *
 * followup1：反过来问，假设给你一系列transfer，问最后account balance是否满足条件。假设所给account balance无论如何也无法做到每个account>=100，问所给的transfer是不是best effort？
 * followup2：如何得到最优解？这里你需要问面试官如何定义最优解。面试官说转账次数越少越好。这样和LC0465就很像了
 */
public class MoneyTransfer {
}
