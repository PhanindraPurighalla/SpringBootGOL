package com.aarnasolutions.learning.gameoflife.business.service;

import com.aarnasolutions.learning.gameoflife.business.domain.Game;
import com.aarnasolutions.learning.gameoflife.business.domain.Player;
import com.aarnasolutions.learning.gameoflife.data.repository.GameRepository;
import com.aarnasolutions.learning.gameoflife.exception.GameAlreadyFullOfPlayersException;
import com.aarnasolutions.learning.gameoflife.exception.GameIdDoesNotExistException;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Log
public class GameService {

  private final GameRepository gameRepository;

  public GameService(GameRepository gameRepository) {
    this.gameRepository = gameRepository;
  }

  @Transactional
  public com.aarnasolutions.learning.gameoflife.data.entity.Game initGame(Player domainPlayer) {
    var domainGame =
        Game.builder().gameId(UUID.randomUUID().toString()).players(List.of(domainPlayer)).build();
    var player =
        com.aarnasolutions.learning.gameoflife.data.entity.Player.builder()
            .playerName(domainPlayer.getPlayerName())
            .build();

    var game =
        com.aarnasolutions.learning.gameoflife.data.entity.Game.builder()
            .gameId(domainGame.getGameId())
            .players(List.of(player))
            .numPlayers(domainPlayer.getNumPlayers())
            .build();
    gameRepository.save(game);
    return game;
  }

  public List<Game> getGames() {
    var games = gameRepository.findAll();
    List<Game> gameList = new ArrayList<>();
    games.forEach(
        game -> {
          var domainGame = Game.builder().build();
          domainGame.setGameId(game.getGameId());
          domainGame.setNumPlayers(game.getNumPlayers());
          var domainPlayer =
              Player.builder().playerName(game.getPlayers().get(0).getPlayerName()).build();
          var playersInGame =
              game.getPlayers().stream()
                  .map(player -> Player.builder().playerName(player.getPlayerName()).build())
                  .collect(Collectors.toList());
          domainGame.setPlayers(playersInGame);
          gameList.add(domainGame);
        });
    return gameList;
  }

  public void joinGame(String gameId, Player domainPlayer) {
    var game = gameRepository.findById(gameId);
    if (game.isEmpty()) {
      throw new GameIdDoesNotExistException(gameId);
    } else {
      var playerCount = game.get().getNumPlayers();
      if (game.get().getPlayers().size() < playerCount) {
        var player =
            com.aarnasolutions.learning.gameoflife.data.entity.Player.builder()
                .playerName(domainPlayer.getPlayerName())
                .build();
        game.get().getPlayers().add(player);
        gameRepository.save(game.get());
      } else {
        throw new GameAlreadyFullOfPlayersException(playerCount);
      }
    }
  }
}
