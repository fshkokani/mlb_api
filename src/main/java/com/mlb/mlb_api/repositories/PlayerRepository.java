package com.mlb.mlb_api.repositories;

import org.springframework.data.repository.CrudRepository;
import com.mlb.mlb_api.repositories.entities.Player;
import java.util.List;

public interface PlayerRepository extends CrudRepository<Player, Integer>{
    List<Player> findByName(String name);
    List<Player> findAllByOrderByNameAsc();

}
