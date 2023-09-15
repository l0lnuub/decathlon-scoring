package decathlon.controller;


import decathlon.domain.FinalScore;
import decathlon.domain.Performances;
import decathlon.domain.SingleEvent;
import decathlon.service.ScoringService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api")
public class ScoringController {

    private final ScoringService scoringService;

    @GetMapping("scores")
    public FinalScore getScores(@RequestBody Performances performances) {
        return scoringService.calculateScores(performances);
    }

    @GetMapping("scoreByEvent")
    public int getSingleEventScore(@RequestBody SingleEvent event) {
        return scoringService.calculateSingleEventScore(event);
    }


}
