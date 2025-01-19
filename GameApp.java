package PhonePe;

public class GameApp {
    public static void main(String[] args) {
        GameService gameService = new GameService();
        gameService.initGame(6);

        // gameService.addShip("SH1", 2, 1, 5, 4, 4);
        gameService.addShip("SH1", 2, 5, 1, 4, 4);
        // gameService.addShip("SH2", 2, 1, 1, 5, 5);

        gameService.viewBattleField();
        gameService.startGame();
        gameService.viewBattleField();

    }
}
