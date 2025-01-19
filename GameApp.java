package PhonePe;

public class GameApp {
    public static void main(String[] args) {
        GameService gameService = new GameService();
        gameService.initGame(8);

        gameService.addShip("SH1", 3, 0, 0, 4, 4);
        gameService.addShip("SH2", 2, 1, 1, 5, 5);

        gameService.displayBattlefields();
        gameService.startGame();
        gameService.displayBattlefields();

    }
}
