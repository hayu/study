package hello;

import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.fasterxml.jackson.databind.type.TypeFactory;

public class WebSocketControllerTest {

	@Test
	public void deserialize() throws IOException {
		final List<Map<String, Movement>> gpsData = WebSocketController.MAPPER.readValue(
				getClass().getResourceAsStream("yardGpsData.json"),
				TypeFactory.defaultInstance().constructCollectionType(
						List.class, WebSocketController.MOVEMENT_DATA.getClass()));
		assertNotNull(gpsData);
	}
}
