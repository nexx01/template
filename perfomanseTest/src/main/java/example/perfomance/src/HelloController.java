package example.perfomance.src;

import static org.springframework.web.reactive.function.BodyInserters.fromObject;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Component
public class HelloController {

    public Mono<ServerResponse> ping(ServerRequest serverRequest) {
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(fromObject("{\n" +
                        "  \"status\": \"ok\"\n" +
                        "}"));
    }

    public Mono<ServerResponse> pingHeavy(ServerRequest serverRequest) {
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(fromObject("{\n" +
                        "  \"status\": \"ok\"\n" +
                        "}")).delayElement(Duration.ofSeconds(29));
    }

}
