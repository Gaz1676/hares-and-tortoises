/**
 * This class handles the Lettuce Square on the game board.
 * The Rules with the Lettuce Square are :
 * -- You may only land on a Lettuce square if you hold at least one Lettuce card
 * -- When you do so, turn your token upside-down.
 * -- At the start of your next turn, discard 1 Lettuce card and gain carrots equal to 10x your position in the race
 *    (e.g. 5th place gets 50 carrots).
 * -- On the turn after that, you return to moving as normal
 *
 * @author: Gary Fleming, Paddy Murphy, Paul Harmon, Cathal Gahan 21/04/2017
 * @version: 25.0
 */
public class LettuceSquare extends Square {

    @Override
    public String toString() {
        return "Lettuce";
    }

    /**
     * @param player If a player can land on square
     * @return If player can land on a regular Square here, AND has 1+ lettuce, return true.
     *          Otherwise return false.
     */
    @Override
    public boolean canLandOn(Player player) {

        return (super.canLandOn(player) && player.getLettuce() > 0);
    }

    /**
     * @param player When landing on the square the player has to flip the token face down
     *                Triggering that the player skips next turn
     */
    @Override
    public void onLandOn(Player player) {
        super.onLandOn(player);
        player.flipFaceDown();
    }

    /**
     * @param player If player token is face down then discard 1 lettuce card
     *                 Add carrots to your pile by getting your race position and * 10
     */
    @Override
    public void onTurnStart(Player player) {
        if (player.isFaceDown()) {
            player.takeLettuce();
            int carrotsEarned = 10 * player.getRacePosition();
            player.addCarrots(carrotsEarned);
            // Preferably hand this off to a GameController; it decides how messages are actually displayed
            GameController.println(player.getName() + " spent their turn chewing on lettuce and earned " + carrotsEarned + " carrots.");
        }
        //If the player isn't face-down, do nothing; proceed as a regular turn
    }
}