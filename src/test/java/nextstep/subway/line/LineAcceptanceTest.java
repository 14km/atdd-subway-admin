package nextstep.subway.line;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.util.HashMap;
import java.util.Map;

import static nextstep.subway.utils.AcceptanceApiFactory.지하철역_생성;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("지하철 노션 관련 기능")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LineAcceptanceTest {

    @LocalServerPort
    int port;
    private int 강남역_ID;
    private int 교대역_ID;

    @BeforeEach
    public void setUp() {
        if (RestAssured.port == RestAssured.UNDEFINED_PORT) {
            RestAssured.port = port;
        }

        강남역_ID = 지하철역_생성("강남역").jsonPath().getInt("id");
        교대역_ID = 지하철역_생성("교대역").jsonPath().getInt("id");
    }

    @Test
    @DisplayName("지하철 노선을 생성 후 지하철 노선 조회 테스트")
    void aa() {
        ExtractableResponse<Response> response = 지하철노선_생성("2호선");

        // then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());


    }


    private ExtractableResponse<Response> 지하철노선_생성(String name) {
        // when
        Map<String, Object> params = new HashMap<>();
        params.put("name", name);
        params.put("color", "bg-green-600");
        params.put("upStationId", 강남역_ID);
        params.put("downStationId", 교대역_ID);
        params.put("distance", 10);

        return RestAssured.given().log().all()
                .body(params)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when().post("/line")
                .then().log().all()
                .extract();
    }
}
