package com.mlb.mlb_api.controllers;

import com.mlb.mlb_api.controllers.dto.PlayerDTO;
import com.mlb.mlb_api.repositories.PlayerRepository;
import com.mlb.mlb_api.repositories.entities.Player;
import java.util.List;

import com.mlb.mlb_api.service.PlayerService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
@RequestMapping("/player")
public class PlayerController {

    // needed to be used as a bean
    private final PlayerService playerService;

    // constructor

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    //    Before firing up your server make sure your “spring.jpa.hibernate.ddl-auto=” is set to ”create” so that it can rebuild your tables. Then switch it to “update” once the server is up and running, so that it doesn’t drop your data each time you start the application.

    @GetMapping
    public Iterable getAllPlayers(){
    return this.playerService.findAll();
}
//@GetMapping("/ByNameAsc")
//public Iterable getAllPlayersAscending(){
//       return this.playerRepository.findAllByOrderByNameAsc();
//}
//    @GetMapping("/{id}")
//    public Optional<Player> getPlayerById(@PathVariable("id") Integer id){// use Optional becasue findById return an optional
//        if (this.playerRepository.findById(id) != null){
//            return this.playerRepository.findById(id);
//        } return null;
//}
    @PostMapping("/add")
    public Player addPlayer(@RequestBody PlayerDTO playerDTO){
        return this.playerService.save(playerDTO);
    }
//    @PostMapping("/addMulti")
//    public Iterable addMultiPlayers (@RequestBody Iterable<PlayerDTO> playerDTOS){
//        return this.playerService.saveAll(playerDTOS);
//    }
//
    @PutMapping("/update") // the extra path is not needed here, it can differentiate with the @Getmapping
    public Player updatePlayer(@RequestBody PlayerDTO playerDTO, @RequestParam Integer id){
        return this.playerService.update(playerDTO, id);
    }
//    @DeleteMapping("/{id}")
//    public void removePlayer(@PathVariable Integer id){
//        // can repuropose getplayerbyid
//        Optional<Player> playerToDeleteOptional = this.playerRepository.findById(id); //question if false what returns
//            if (playerToDeleteOptional.isEmpty()) {
//                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No player found with id ");
//            }
//            if(playerToDeleteOptional.isPresent()){
//                this.playerRepository.delete(playerToDeleteOptional.get());
//                throw new ResponseStatusException(HttpStatus.OK, "The player was successfully deleted");
//                // or deletebyId
//            }
////        playerToDeleteOptional.ifPresent(playerToDeleteOptional::)
//
//        }
////        throw new ResponseStatusException(HttpStatus.NOT_FOUND);}
//    //
//    @GetMapping("/find")
//    public List<Player> findPlayerByName(@RequestParam String name){
//        if(this.playerRepository.findByName(name) == null){
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Player with this " +name+ " not found");
//        } return this.playerRepository.findByName(name);
//    }
}
