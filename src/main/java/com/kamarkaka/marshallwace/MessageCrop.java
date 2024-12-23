package com.kamarkaka.marshallwace;

/***
 * Prepare a notification of the given message which will be displayed on a mobile device. The message is made of words
 * separated by single spaces. The length of the notification is limited to K characters. If the message is too long to
 * be displayed fully, some words from the end of the message should be cropped, keeping in mind that:
 * - the notification should be as long as possible;
 * - only whole words can be cropped;
 * - if any words are cropped, the notification should end with "..."; the dots should be separated from the last word
 * by a single space;
 * - the notification may not exceed the K-character limit, including the dots.
 * If all the words need to be cropped, the notification is "..." (three dots without a space).
 * For example, for message = "And now here is my secret" and K = 15, the notification is "And now ...". Note that:
 * - the notification "And ..." would be incorrect, because there is a longer correct notification;
 * - the notification "And now her ..." would be incorrect, because the original message is cropped through the middle
 * of a word;
 * - the notification "And now ... " would be incorrect, because it ends with a space;
 */
public class MessageCrop {
    public String output(String message, int K) {
        if (message.length() <= K) return message;

        StringBuilder sb = new StringBuilder();
        StringBuilder word = new StringBuilder();
        for (int i = 0; i < message.length(); i++) {
            if (message.charAt(i) != ' ') {
                word.append(message.charAt(i));
            } else {
                int currLen = sb.length();
                int potentialLen = currLen == 0 ? word.length() : currLen + 1 + word.length();
                if (potentialLen + 4 <= K) {
                    if (!sb.isEmpty()) {
                        sb.append(' ');
                    }
                    sb.append(word);
                    word = new StringBuilder();
                } else {
                    break;
                }
            }
        }

        if (!sb.isEmpty()) {
            sb.append(' ');
        }
        sb.append("...");

        return sb.toString();
    }

    public static void main(String[] args) {
        MessageCrop solution = new MessageCrop();
        System.out.println(solution.output("And now here is my secret", 4));
        System.out.println(solution.output("And now here is my secret", 10));
        System.out.println(solution.output("And now here is my secret", 15));
        System.out.println(solution.output("And now here is my secret", 20));
        System.out.println(solution.output("And now here is my secret", 22));
        System.out.println(solution.output("And now here is my secret", 23));
        System.out.println(solution.output("And now here is my secret", 24));
        System.out.println(solution.output("And now here is my secret", 25));
    }
}
