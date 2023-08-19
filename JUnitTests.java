import static org.junit.Assert.*;
import org.junit.Test;

public class WarCardgameTest {

    @Test
    public void testDeckShuffle() {
        Deck deck = new Deck();
        deck.shuffle();
     
        assertNotNull(deck.dealCard());
    }

    @Test
    public void testPlayerAddCard() {
        Player player = new Player("Test Player");
        Card card = new Card("A", "Spades");
        player.addCard(card);
        assertEquals(1, player.getHandSize());
    }

    @Test
    public void testPlayerPlayCard() {
        Player player = new Player("Test Player");
        Card card = new Card("10", "Hearts");
        player.addCard(card);
        Card playedCard = player.playCard();
        assertNotNull(playedCard);
        assertEquals(0, player.getHandSize());
    }

    @Test
    public void testPlayerPlayCardEmptyHand() {
        Player player = new Player("Test Player");
        Card playedCard = player.playCard();
        assertNull(playedCard);
    }

    @Test
    public void testRoundWinnerPlayer1() {
        Player player1 = new Player("Player 1");
        player1.addCard(new Card("K", "Spades"));

        Player player2 = new Player("Player 2");
        player2.addCard(new Card("Q", "Hearts"));

        Card card1 = player1.playCard();
        Card card2 = player2.playCard();

        assertTrue(card1.getRank().compareTo(card2.getRank()) > 0);
    }

    @Test
    public void testRoundWinnerPlayer2() {
        Player player1 = new Player("Player 1");
        player1.addCard(new Card("9", "Diamonds"));
        Player player2 = new Player("Player 2");
        player2.addCard(new Card("10", "Clubs"));

        Card card1 = player1.playCard();
        Card card2 = player2.playCard();

        assertTrue(card2.getRank().compareTo(card1.getRank()) > 0);
    }

    @Test
    public void testRoundTie() {
        Player player1 = new Player("Player 1");
        player1.addCard(new Card("5", "Clubs"));

        Player player2 = new Player("Player 2");
        player2.addCard(new Card("5", "Hearts"));

        Card card1 = player1.playCard();
        Card card2 = player2.playCard();

        assertEquals(card1.getRank(), card2.getRank());
    }
// Manual Test Script (Requirement not testable at the unit level):
    // Requirement: End Game with Tie
    // Use Case: Determine Winner
    // Steps:
    // 1. Simulate a game where both players have equal cards in their hands.
    // 2. Observe the game's end result and confirm it's a tie.
    // 3. Compare the game's outcome with the expected result.   }
