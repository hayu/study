package hello;

import hello.SimpleMove.Direction;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;

@RestController
public class WebSocketController {

	@Autowired
	private SimpMessagingTemplate template;
	private TaskScheduler scheduler = new ConcurrentTaskScheduler();
	private Random random = new Random();
	private Direction[] directions = Direction.values();
	private boolean publish = false;
	static final ObjectMapper MAPPER = new ObjectMapper();
	static final Map<String, Movement> MOVEMENT_DATA = new HashMap<>();

	@MessageMapping("/publish") // incoming message endpoint as "/app/publish"
	public void publish() {
		this.publish = true;
	}

	@PostConstruct
	private void broadcastPeriodically() throws IOException {
		scheduler.scheduleAtFixedRate(new Runnable() {
			@Override
			public void run() {
				int index = random.nextInt(directions.length);
				Direction direction = directions[index];
				template.convertAndSend("/topic/simplemove", new SimpleMove(direction, 2*(index+1)));
			}
		}, 600);

		final List<Map<String, Movement>> gpsData = MAPPER.readValue(
				getClass().getResourceAsStream("yardGpsData.json"),
				TypeFactory.defaultInstance().constructCollectionType(
						List.class, MOVEMENT_DATA.getClass()));
		
		final int size = gpsData.size();
		
		scheduler.scheduleAtFixedRate(new Runnable() {
			private int idx = 0;
			@Override
			public void run() {
				if (publish) {
					template.convertAndSend("/topic/yardmove", gpsData.get(idx%size));
					idx++;
				}
			}
		}, 600);
	}
}
