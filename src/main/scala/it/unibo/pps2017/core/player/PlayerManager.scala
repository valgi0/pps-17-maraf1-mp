package it.unibo.pps2017.core.player


import it.unibo.pps2017.core.deck.cards.Card
import it.unibo.pps2017.core.game.Match
import scala.collection.mutable.{ListBuffer,Seq}


abstract class PlayerManager(model:Match) extends Controller{

  var allCardsInHand = Map[Player, ListBuffer[Card]]()
  var playerTurn : Player
  var totHandsSet : Int = 0
  var players : Seq[Player]
  /**
    * Get all the cards that each player actually has
    *
    * @return cards that players have
    */
  override def getAllHands: Map[Player,ListBuffer[Card]] =  allCardsInHand

  override def incSetHands(): Unit = {
    totHandsSet = totHandsSet + 1
    if(totHandsSet==4){
      var allCardsPath : ListBuffer[String] = ListBuffer[String]()
      players.foreach(p => p.getHand().foreach(c =>
        allCardsPath += "src/main/java/it/unibo/pps2017/core/gui/cards/" + c.cardValue + c.cardSeed +".png"
      ))
      totHandsSet = 0
      //gui.setCardsPath(allCardsPath)
    }
  }
    /**
    * Check if the selected and played card can be played
    *
    * @param cardIndex  index of the card.
    * @return true if the card can be played, otherwise false
    */
  override def isCardOk(cardIndex: Int): Boolean = {
    var playedCard: Card = playerTurn.getCardAtIndex(cardIndex)
    if(model.isCardOk(playedCard)){
      val cardPath = "src/main/java/it/unibo/pps2017/core/gui/cards/" + playedCard.cardValue + playedCard.cardSeed +".png"
      //gui.showOtherPlayersPlayedCard(playerTurn,cardPath)
      playerTurn.getFuture().failed
      true
    }
    false
  }

  /**
    * Called by model to set the actual turn
    *
    * @param player  the player who has the turn
    */
  override def setTurn(player: Player): Unit = {
    playerTurn = player
    //gui.setCurrentPlayer(playerTurn, model.isPartialTurnFinished())
  }

  /**
    * Called initially when the player enter the game
    *
    * @param player  the player to be added to the game
    */
  override def addPlayer(player: Player): Unit = {
    allCardsInHand += (player -> ListBuffer[Card]())
    players :+ player
    //model.addPlayer(player,"User1")
  }

  /**
    * To be called when each player has played one card which
    * means the round is finished, so it's necessary to update
    * the view
    */
  override def cleanField(): Unit = {
    //gui.cleanField(playerTurn)
  }


  /**
    * To be called when the time of the turn of one player has
    * expired so he will play a random card from his hand
    * considering the game rules
    *
    * @return the 'almost' random card
    */
  override def getRandCard: Card = {
    //Card card = model.forcePlay(playerTurn)
    //gui.playedCard(card)
    null
  }

  /**
    * Useful to notify all the players when one of them choose a
    * command which can be: busso, striscio, volo
    *
    * @param command  the command choosen from the player
    */
  override def setCommand(command: String): Unit = {
    //model.setCommand(command)
  }

  /**
    * Method to be called when the game is finished, so the view
    * can show up the points of each tem
    *
    * @param pointsTeam1  the points of the first team
    * @param pointsTeam2  the points of the second team
    */
  override def totalPoints(pointsTeam1: Integer, pointsTeam2: Integer): Unit = {
    //gui.showAnimationEndMatch(pointsTeam1,pointsTeam2)
  }

  /**
    * To check if it's player's turn or not
    * @param player the player
    */
  override def isPlayerTurn(player: Player): Unit = playerTurn.equals(player)

  //override def timeExpired(): Boolean = ???
}
