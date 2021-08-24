package com.aarnasolutions.learning.gameoflife.business.service;

import com.aarnasolutions.learning.gameoflife.data.entity.Game;
import com.aarnasolutions.learning.gameoflife.data.entity.Player;
import com.aarnasolutions.learning.gameoflife.data.repository.GameRepository;
import com.aarnasolutions.learning.gameoflife.exception.GameAlreadyFullOfPlayersException;
import com.aarnasolutions.learning.gameoflife.exception.GameIdDoesNotExistException;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Log
public class GameService {

    public static final int INITIAL_CELL_POSITION = 1;
    private final GameRepository gameRepository;
    private final PlayerTurnService playerTurnService;

    public GameService(GameRepository gameRepository, PlayerTurnService playerTurnService) {
        this.gameRepository = gameRepository;
        this.playerTurnService = playerTurnService;
    }

    @Transactional
    public com.aarnasolutions.learning.gameoflife.data.entity.Game initGame(
            String playerName, int numPlayers) {

        var player =
                com.aarnasolutions.learning.gameoflife.data.entity.Player.builder()
                        .playerName(playerName)
                        .cellPosition(INITIAL_CELL_POSITION)
                        .build();

        var game =
                com.aarnasolutions.learning.gameoflife.data.entity.Game.builder()
                        .gameId(UUID.randomUUID().toString())
                        .players(List.of(player))
                        .numPlayers(numPlayers)
                        .build();
        gameRepository.save(game);
        return game;
    }

    public Iterable<Game> getGames() {
        return gameRepository.findAll();
    }

    public List<com.aarnasolutions.learning.gameoflife.business.domain.Game> getDomainGames() {
        var repoGames = gameRepository.findAll();
        var domainGames =
                new ArrayList<com.aarnasolutions.learning.gameoflife.business.domain.Game>();
        repoGames.forEach(
                game -> {
                    var domainGame =
                            com.aarnasolutions.learning.gameoflife.business.domain.Game.builder()
                                    .gameId(game.getGameId())
                                    .numPlayers(game.getNumPlayers())
                                    .players(
                                            game.getPlayers().stream()
                                                    .map(
                                                            player ->
                                                                    com.aarnasolutions.learning
                                                                            .gameoflife.business
                                                                            .domain.Player.builder()
                                                                            .playerName(
                                                                                    player
                                                                                            .getPlayerName())
                                                                            .cellPosition(
                                                                                    player
                                                                                            .getCellPosition())
                                                                            .numberSpun(0)
                                                                            .build())
                                                    .collect(Collectors.toList()))
                                    .build();
                    domainGames.add(domainGame);
                });
        return domainGames;
    }

    public void joinGame(String gameId, String playerName) {
        var game = gameRepository.findById(gameId);
        if (game.isEmpty()) {
            throw new GameIdDoesNotExistException(gameId);
        } else {
            var playerCount = game.get().getNumPlayers();
            if (game.get().getPlayers().size() < playerCount) {
                var player =
                        com.aarnasolutions.learning.gameoflife.data.entity.Player.builder()
                                .playerName(playerName)
                                .cellPosition(INITIAL_CELL_POSITION)
                                .build();
                game.get().getPlayers().add(player);
                gameRepository.save(game.get());
            } else {
                throw new GameAlreadyFullOfPlayersException(playerCount);
            }
        }
    }

    public Optional<com.aarnasolutions.learning.gameoflife.data.entity.Game> movePlayerPeg(
            String gameId, String playerName, int numberSpun) {
        var game = gameRepository.findById(gameId);
        if (game.isEmpty()) throw new GameIdDoesNotExistException(gameId);

        game.ifPresent(
                game1 -> {
                    game1.getPlayers().stream()
                            .filter(player1 -> player1.getPlayerName().equals(playerName))
                            .forEach(
                                    player1 ->
                                            player1.setCellPosition(
                                                    playerTurnService.getNextCellId(
                                                            player1.getCellPosition(),
                                                            numberSpun)));
                    gameRepository.save(game1);
                });
        return game;
    }

    public List<Player> getPlayersByGameId(String gameId) {
        var game = gameRepository.findById(gameId);
        return game.map(Game::getPlayers).orElse(null);
    }
}
