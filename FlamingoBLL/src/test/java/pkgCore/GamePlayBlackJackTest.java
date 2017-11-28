package pkgCore;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Iterator;

import org.junit.Test;

import pkgEnum.eBlackJackResult;
import pkgEnum.eRank;
import pkgEnum.eSuit;
import pkgException.HandException;

public class GamePlayBlackJackTest {

	@Test
	// Player wins against dealer who did not bust
	public void TestPlayerWinning() {

		// Create a new table
		Table t = new Table();

		// Create players
		Player p1 = new Player("Ashley Kleinhomer", 26);

		// Add player to the table
		t.AddPlayerToTable(p1);

		// Create a deck
		Deck deck = new Deck();

		// Create a game with player at table and a deck
		GamePlayBlackJack testGame = new GamePlayBlackJack(t.getHmTablePlayer(), deck);

		// Create a hand for the dealer
		HandBlackJack hand1 = new HandBlackJack();
		hand1.AddCard(new Card(eSuit.CLUBS, eRank.TWO));
		hand1.AddCard(new Card(eSuit.CLUBS, eRank.FIVE));
		hand1.AddCard(new Card(eSuit.CLUBS, eRank.THREE));
		hand1.AddCard(new Card(eSuit.HEARTS, eRank.SEVEN));

		// Assign a hand to the dealer
		testGame.sethDealer(hand1);

		// Create a hand for the player
		HandBlackJack hand2 = new HandBlackJack();
		hand2.AddCard(new Card(eSuit.CLUBS, eRank.TEN));
		hand2.AddCard(new Card(eSuit.CLUBS, eRank.TWO));
		hand2.AddCard(new Card(eSuit.CLUBS, eRank.EIGHT));

		GamePlayerHand GPH = new GamePlayerHand(testGame.getGameID(),p1.getPlayerID(),hand2.getHandID());
		testGame.putHandToGame(GPH, hand2);

		testGame.ScoreGame(GPH);
		assertEquals(eBlackJackResult.WIN, hand2.geteBlackJackResult()); 
	}

	@Test
	// Player loses against dealer who did not bust
	public void TestPlayerLosing() {

		// Create a new table
		Table t = new Table();

		// Create players
		Player p1 = new Player("Ashley Kleinhomer", 26);

		// Add player to the table
		t.AddPlayerToTable(p1);

		// Create a deck
		Deck deck = new Deck();

		// Create a game with player at table and a deck
		GamePlayBlackJack testGame = new GamePlayBlackJack(t.getHmTablePlayer(), deck);

		// Create a hand for the dealer
		HandBlackJack hand1 = new HandBlackJack();
		hand1.AddCard(new Card(eSuit.CLUBS, eRank.TWO));
		hand1.AddCard(new Card(eSuit.CLUBS, eRank.FIVE));
		hand1.AddCard(new Card(eSuit.CLUBS, eRank.THREE));
		hand1.AddCard(new Card(eSuit.HEARTS, eRank.FOUR));
		hand1.AddCard(new Card(eSuit.HEARTS, eRank.SEVEN));

		// Assign a hand to the dealer
		testGame.sethDealer(hand1);

		// Create a hand for the player
		HandBlackJack hand2 = new HandBlackJack();
		hand2.AddCard(new Card(eSuit.CLUBS, eRank.TEN));
		hand2.AddCard(new Card(eSuit.CLUBS, eRank.TWO));
		hand2.AddCard(new Card(eSuit.CLUBS, eRank.EIGHT));

		GamePlayerHand GPH = new GamePlayerHand(testGame.getGameID(),p1.getPlayerID(),hand2.getHandID());
		testGame.putHandToGame(GPH, hand2);

		testGame.ScoreGame(GPH);
		assertEquals(eBlackJackResult.LOSE, hand2.geteBlackJackResult());
	}

	@Test
	public void TestPlayerPush() {
		// Create a new table
		Table t = new Table();

		// Create players
		Player p1 = new Player("Ashley Kleinhomer", 26);

		// Add player to the table
		t.AddPlayerToTable(p1);

		// Create a deck
		Deck deck = new Deck();

		// Create a game with player at table and a deck
		GamePlayBlackJack testGame = new GamePlayBlackJack(t.getHmTablePlayer(), deck);

		// Create a hand for the dealer
		HandBlackJack hand1 = new HandBlackJack();
		hand1.AddCard(new Card(eSuit.CLUBS, eRank.TWO));
		hand1.AddCard(new Card(eSuit.CLUBS, eRank.FIVE));
		hand1.AddCard(new Card(eSuit.CLUBS, eRank.THREE));
		hand1.AddCard(new Card(eSuit.HEARTS, eRank.SEVEN));

		// Assign a hand to the dealer
		testGame.sethDealer(hand1);

		// Create a hand for the player
		HandBlackJack hand2 = new HandBlackJack();
		hand2.AddCard(new Card(eSuit.CLUBS, eRank.TEN));
		hand2.AddCard(new Card(eSuit.CLUBS, eRank.SEVEN));

		GamePlayerHand GPH = new GamePlayerHand(testGame.getGameID(),p1.getPlayerID(),hand2.getHandID());
		testGame.putHandToGame(GPH, hand2);

		testGame.ScoreGame(GPH);
		assertEquals(eBlackJackResult.TIE, hand2.geteBlackJackResult());
	}

	@Test
	// Dealer busts
	public void TestDealerBusts() {

		// Create a new table
		Table t = new Table();

		// Create players
		Player p1 = new Player("Ashley Kleinhomer", 26);

		// Add player to the table
		t.AddPlayerToTable(p1);

		// Create a deck
		Deck deck = new Deck();

		// Create a game with player at table and a deck
		GamePlayBlackJack testGame = new GamePlayBlackJack(t.getHmTablePlayer(), deck);

		// Create a hand for the dealer
		HandBlackJack hand1 = new HandBlackJack();
		hand1.AddCard(new Card(eSuit.CLUBS, eRank.TWO));
		hand1.AddCard(new Card(eSuit.CLUBS, eRank.FIVE));
		hand1.AddCard(new Card(eSuit.CLUBS, eRank.THREE));
		hand1.AddCard(new Card(eSuit.HEARTS, eRank.FOUR));
		hand1.AddCard(new Card(eSuit.HEARTS, eRank.TEN));

		// Assign a hand to the dealer
		testGame.sethDealer(hand1);

		// Create a hand for the player
		HandBlackJack hand2 = new HandBlackJack();
		hand2.AddCard(new Card(eSuit.CLUBS, eRank.TEN));
		hand2.AddCard(new Card(eSuit.CLUBS, eRank.TWO));
		hand2.AddCard(new Card(eSuit.CLUBS, eRank.EIGHT));

		GamePlayerHand GPH = new GamePlayerHand(testGame.getGameID(),p1.getPlayerID(),hand2.getHandID());
		testGame.putHandToGame(GPH, hand2);

		testGame.ScoreGame(GPH);
		assertEquals(eBlackJackResult.WIN, hand2.geteBlackJackResult());
	}

	@Test
	// Player busts
	public void TestPlayerBusts() {

		// Create a new table
		Table t = new Table();

		// Create players
		Player p1 = new Player("Ashley Kleinhomer", 26);

		// Add player to the table
		t.AddPlayerToTable(p1);

		// Create a deck
		Deck deck = new Deck();

		// Create a game with player at table and a deck
		GamePlayBlackJack testGame = new GamePlayBlackJack(t.getHmTablePlayer(), deck);

		// Create a hand for the dealer
		HandBlackJack hand1 = new HandBlackJack();
		hand1.AddCard(new Card(eSuit.CLUBS, eRank.TWO));
		hand1.AddCard(new Card(eSuit.CLUBS, eRank.FIVE));
		hand1.AddCard(new Card(eSuit.CLUBS, eRank.THREE));
		hand1.AddCard(new Card(eSuit.HEARTS, eRank.SEVEN));

		// Assign a hand to the dealer
		testGame.sethDealer(hand1);

		// Create a hand for the player
		HandBlackJack hand2 = new HandBlackJack();
		hand2.AddCard(new Card(eSuit.CLUBS, eRank.TEN));
		hand2.AddCard(new Card(eSuit.CLUBS, eRank.TWO));
		hand2.AddCard(new Card(eSuit.CLUBS, eRank.EIGHT));
		hand2.AddCard(new Card(eSuit.CLUBS, eRank.FOUR));

		GamePlayerHand GPH = new GamePlayerHand(testGame.getGameID(),p1.getPlayerID(),hand2.getHandID());
		testGame.putHandToGame(GPH, hand2);

		testGame.ScoreGame(GPH);
		assertEquals(eBlackJackResult.LOSE, hand2.geteBlackJackResult());
	}

	@Test
	public void TestTwoPlayersWinning() {
		// Create a new table
		Table t = new Table();

		// Create players
		Player p1 = new Player("Ashley Kleinhomer", 26);
		Player p2 = new Player("Cathy Dolbow", 32);

		// Add player to the table
		t.AddPlayerToTable(p1);
		t.AddPlayerToTable(p2);

		// Create a deck
		Deck deck = new Deck();

		// Create a game with player at table and a deck
		GamePlayBlackJack testGame = new GamePlayBlackJack(t.getHmTablePlayer(), deck);

		// Create a hand for the dealer
		HandBlackJack hand1 = new HandBlackJack();
		hand1.AddCard(new Card(eSuit.CLUBS, eRank.TWO));
		hand1.AddCard(new Card(eSuit.CLUBS, eRank.FIVE));
		hand1.AddCard(new Card(eSuit.CLUBS, eRank.THREE));
		hand1.AddCard(new Card(eSuit.HEARTS, eRank.SEVEN));

		// Assign a hand to the dealer
		testGame.sethDealer(hand1);

		// Create a hand for player1
		HandBlackJack playerHand1 = new HandBlackJack();
		playerHand1.AddCard(new Card(eSuit.CLUBS, eRank.TEN));
		playerHand1.AddCard(new Card(eSuit.CLUBS, eRank.TWO));
		playerHand1.AddCard(new Card(eSuit.CLUBS, eRank.EIGHT));

		// Create a hand for player2
		HandBlackJack playerHand2 = new HandBlackJack();
		playerHand2.AddCard(new Card(eSuit.CLUBS, eRank.THREE));
		playerHand2.AddCard(new Card(eSuit.CLUBS, eRank.TWO));
		playerHand2.AddCard(new Card(eSuit.CLUBS, eRank.SEVEN));
		playerHand2.AddCard(new Card(eSuit.CLUBS, eRank.FOUR));
		playerHand2.AddCard(new Card(eSuit.CLUBS, eRank.FIVE));

		GamePlayerHand GPH1 = new GamePlayerHand(testGame.getGameID(),p1.getPlayerID(),playerHand1.getHandID());
		testGame.putHandToGame(GPH1, playerHand1);
		
		GamePlayerHand GPH2 = new GamePlayerHand(testGame.getGameID(),p2.getPlayerID(),playerHand2.getHandID());
		testGame.putHandToGame(GPH2, playerHand2);
	
		testGame.ScoreGame(GPH1);

		assertEquals(eBlackJackResult.WIN, playerHand1.geteBlackJackResult());
		assertEquals(eBlackJackResult.WIN, playerHand2.geteBlackJackResult());
	}
	
	}
