package com.mlb.mlb_api.service;
import com.mlb.mlb_api.controllers.dto.PlayerDTO;
import com.mlb.mlb_api.repositories.entities.Player;

import java.util.List;

public interface PlayerService {
    Player save(PlayerDTO playerDTO);
    Player update(PlayerDTO playerDTO, Integer id);
    void delete(Integer playerId);
    Iterable<Player> findAll();
    Player findById(Integer playerId);

    Iterable saveAll(Iterable<PlayerDTO> playerDTOS);
    List<Player> findByName(String name);
    List<Player> findAllByOrderByNameAsc();
}
