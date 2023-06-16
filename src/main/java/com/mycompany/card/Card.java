/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.card;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class Card {
    private String rank;
    private String suit;

    public Card(String rank, String suit) {
        this.rank = rank;
        this.suit = suit;
    }

    public String getRank() {
        return rank;
    }

    public String getSuit() {
        return suit;
    }

    @Override
    public String toString() {
        return rank + " of " + suit;
    }
}

class Deck {
    private List<Card> cards;

    public Deck() {
        cards = new ArrayList<>();
        String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};
        String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"};

        for (String suit : suits) {
            for (String rank : ranks) {
                Card card = new Card(rank, suit);
                cards.add(card);
            }
        }
    }

    public void shuffle() {
        Random random = new Random();
        for (int i = 0; i < cards.size(); i++) {
            int j = random.nextInt(cards.size());
            Card temp = cards.get(i);
            cards.set(i, cards.get(j));
            cards.set(j, temp);
        }
    }

    public Card dealCard() {
        if (cards.size() > 0) {
            return cards.remove(0);
        } else {
            return null;
        }
    }
}

class Player {
    private String name;
    private List<Card> hand;

    public Player(String name) {
        this.name = name;
        hand = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void addCard(Card card) {
        hand.add(card);
    }

    public Card playCard() {
        if (hand.size() > 0) {
            return hand.remove(0);
        } else {
            return null;
        }
    }

    public int getHandSize() {
        return hand.size();
    }
}

public class WarCardgame {
    public static void main(String[] args) {
        Deck deck = new Deck();
        deck.shuffle();

        Player player1 = new Player("Player 1");
        Player player2 = new Player("Player 2");

        // Deal cards to players
        while (deck.dealCard() != null) {
            player1.addCard(deck.dealCard());
            player2.addCard(deck.dealCard());
        }

        // Start the game
        int roundCount = 1;
        while (true) {
            System.out.println("Round " + roundCount + ":");
            System.out.println(player1.getName() + " has " + player1.getHandSize() + " cards.");
            System.out.println(player2.getName() + " has " + player2.getHandSize() + " cards.");

            Card card1 = player1.playCard();
            Card card2 = player2.playCard();

            if (card1 == null || card2 == null) {
                break;
            }

            System.out.println(player1.getName() + " plays " + card1);
            System.out.println(player2.getName() + " plays " + card2);

            if (card1.getRank().compareTo(card2.getRank()) > 0) {
                System.out.println(player1.getName() + " wins the round!");
                player1.addCard(card1);
                player1.addCard(card2);
            } else if (card2.getRank().compareTo(card1.getRank()) > 0) {
                System.out.println(player2.getName() + " wins the round!");
                player2.addCard(card1);
                player2.addCard(card2);
            } else {
                System.out.println("It's a tie!");
            }

            System.out.println();

            roundCount++;
        }

        if (player1.getHandSize() > player2.getHandSize()) {
            System.out.println(player1.getName() + " wins the game!");
        } else if (player2.getHandSize() > player1.getHandSize()) {
            System.out.println(player2.getName() + " wins the game!");
        } else {
            System.out.println("The game ends in a tie!");
        }
    }
}