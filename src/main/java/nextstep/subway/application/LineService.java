package nextstep.subway.application;

import nextstep.subway.domain.*;
import nextstep.subway.dto.LineRequest;
import nextstep.subway.dto.LineResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
@Transactional(readOnly = true)
public class LineService {
    private final String NOT_FOUND_ERROR = "지하철역을 찾을 수 없습니다.";

    private final LineRepository lineRepository;
    private final StationRepository stationRepository;

    public LineService(LineRepository lineRepository, StationRepository stationRepository) {
        this.lineRepository = lineRepository;
        this.stationRepository = stationRepository;
    }

    @Transactional
    public LineResponse saveLine(LineRequest lineRequest) {
        Section section = createSection(
                lineRequest.getDistance(),
                lineRequest.getUpStationId(),
                lineRequest.getDownStationId()
        );

        Line response = lineRepository.save(Line.of(lineRequest.getName(), lineRequest.getColor(), section));
        return LineResponse.of(response);
    }

    private Station findStation(Long stationId) {
        return stationRepository.findById(stationId).orElseThrow(() -> new NoSuchElementException(NOT_FOUND_ERROR));
    }

    private Section createSection(int distance, Long upStationId, Long downStationId) {
        return Section.of(distance, findStation(upStationId), findStation(downStationId));
    }
}
