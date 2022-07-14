package com.mlb.mlb_api.service;

import com.mlb.mlb_api.controllers.dto.PlayerDTO;
import com.mlb.mlb_api.repositories.PlayerRepository;
import com.mlb.mlb_api.repositories.entities.Player;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

@Service
public class PlayerServiceImpl implements PlayerService{

    private final PlayerRepository playerRepository;

    public PlayerServiceImpl(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @Override
    //@PostMapping("/add")
    public Player save(PlayerDTO playerDTO) {
        if(playerDTO.getName()== null || playerDTO.getName().isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Input name not found");
        } else if (playerDTO.getAge() == null || playerDTO.getAge()<18){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Input Age is null or less than 18");
        }  else if (playerDTO.getRating() == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Input Rating is null");
        } else if (playerDTO.getYearsOfExperience() == null){
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Input Years of Experience is null");}

        Player player = new Player(playerDTO);//Generate an id for the encoming data - before passing it to the db
        return playerRepository.save(player); //playerRepo does not accept playerDTO
    }

    @Override
    //@PutMapping
    public Player update(PlayerDTO playerDTO, Integer encomingId) {

        Optional<Player> playerToUpdateOptional = this.playerRepository.findById(encomingId); //question if false what returns
        if (playerToUpdateOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No player found with id " + encomingId);
        }
        Player playerFromDb = playerToUpdateOptional.get();

//       //You can repurpose using a previously defined method (DRY)
        playerFromDb.setName(playerDTO.getName() ==null || playerDTO.getName().isEmpty() ?  playerFromDb.getName() :playerDTO.getName());

        //another way
        if(playerDTO.getAge() == null){
            playerFromDb.setAge(playerFromDb.getAge());
        } else if(playerDTO.getAge()<18) {
            playerFromDb.setAge(playerFromDb.getAge());
        } else {
            playerFromDb.setAge(playerDTO.getAge());
        }


        if (playerDTO.getRating() != null) {
            playerFromDb.setRating(playerDTO.getRating());
        }

        if (playerDTO.getYearsOfExperience() != null){
            playerFromDb.setYearsOfExperience(playerDTO.getYearsOfExperience());
        }

        return this.playerRepository.save(playerFromDb);
    }


    @Override
    // @DeleteMapping
    public void delete(Integer playerId) {
        // can repuropose findById

        Optional<Player> playerToDeleteOptional = this.playerRepository.findById(playerId); //question if false what returns
        if (playerToDeleteOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No player found with id ");
        }
        if(playerToDeleteOptional.isPresent()){
            this.playerRepository.delete(playerToDeleteOptional.get());
            throw new ResponseStatusException(HttpStatus.OK, "The player was successfully deleted");
            // or deletebyId
        }
        //or use this method of ifPresent
//        playerToDeleteOptional.ifPresent(playerToDeleteOptional::)
//
    }

    @Override
    // @GetMapping
    public Iterable<Player> findAll() {
        return playerRepository.findAll();
    }

    @Override
    //@GetMapping("/{id}")
    public Player findById(Integer playerId) {
        Optional<Player> playerFromDb= this.playerRepository.findById(playerId);
        if (playerFromDb.isPresent()){
            return playerFromDb.get();
        } throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Player with this "+ playerId+ " not found.");
    }

    @Override
//  @PostMapping("/addMulti")
    public Iterable saveAll(Iterable<PlayerDTO> playerDTOS) {
        ArrayList players = new ArrayList<>();
        playerDTOS.forEach(playerDTO -> playerRepository.save(new Player(playerDTO)));
        playerDTOS.forEach(playerDTO -> players.add(playerDTO));
        return players;
}
    @Override
//    @GetMapping("/find")
    public List<Player> findByName(String name){
            if(this.playerRepository.findByName(name) == null){
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Player with this " +name+ " not found");
            } return this.playerRepository.findByName(name);
        }

    @Override
    //@GetMapping("/ByNameAsc")
    public List<Player> findAllByOrderByNameAsc(){
        return playerRepository.findAllByOrderByNameAsc();
    }
}
