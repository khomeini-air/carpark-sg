package sg.khomeini.carpark.task;

import sg.khomeini.carpark.core.service.CarParkService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class UpdateAvailabilityTask {
    @Autowired
    private CarParkService carParkService;

    @Scheduled(fixedDelayString = "${task.schedule.fix.delay}")
    public void update() {
        carParkService.updateAvailability();
    }
}
