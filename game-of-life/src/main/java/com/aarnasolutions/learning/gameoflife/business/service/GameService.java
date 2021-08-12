package com.aarnasolutions.learning.gameoflife.business.service;

import com.aarnasolutions.learning.gameoflife.business.domain.Game;
import com.aarnasolutions.learning.gameoflife.business.domain.Player;
import com.aarnasolutions.learning.gameoflife.data.repository.GameRepository;
import com.aarnasolutions.learning.gameoflife.data.repository.PlayerRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class GameService {

    private final GameRepository gameRepository;
    private final PlayerRepository playerRepository;

    public GameService(GameRepository gameRepository, PlayerRepository playerRepository) {
        this.gameRepository = gameRepository;
        this.playerRepository = playerRepository;
    }

    @Transactional
    public void startGame(Player domainPlayer) {
        var domainGame = Game.builder().gameId(UUID.randomUUID().toString())
                .players(List.of(domainPlayer))
                .build();
        var game = com.aarnasolutions.learning.gameoflife.data.entity.Game.builder()
                .gameId(domainGame.getGameId())
                .playerId(domainPlayer.getPlayerId())
                .build();

        var player = com.aarnasolutions.learning.gameoflife.data.entity.Player.builder()
                .playerId(domainPlayer.getPlayerId())
                .playerName(domainPlayer.getPlayerName())
                .build();
        playerRepository.save(player);
        gameRepository.save(game);
    }

    public List<Game> getStartedGames() {
        var games = gameRepository.findAll();
        List<Game> gameList = new ArrayList<>();
        games.forEach(game -> {
            var domainGame = Game.builder().build();
            domainGame.setGameId(game.getGameId());
            var player = playerRepository.findById(game.getPlayerId());
            var domainPlayer = Player.builder()
                    .playerId(player.get().getPlayerId())
                    .playerName(player.get().getPlayerName())
                    .build();
            domainGame.setPlayers(List.of(domainPlayer));
            gameList.add(domainGame);
        });
        return gameList;
    }

}
