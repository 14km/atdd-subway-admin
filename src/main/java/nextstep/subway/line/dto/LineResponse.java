package nextstep.subway.line.dto;

import nextstep.subway.line.domain.Line;
import nextstep.subway.station.domain.Station;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class LineResponse {
  private final Long id;
  private final String name;
  private final String color;
  private final List<Station> stations;
  private final LocalDateTime createdDate;
  private final LocalDateTime modifiedDate;

  public LineResponse(Long id, String name, String color, List<Station> stations, LocalDateTime createdDate, LocalDateTime modifiedDate) {
    this.id = id;
    this.name = name;
    this.color = color;
    this.stations = stations;
    this.createdDate = createdDate;
    this.modifiedDate = modifiedDate;
  }

  public static LineResponse of(Line line) {
    return new LineResponse(line.getId(), line.getName(), line.getColor(), line.getStations().asList(), line.getCreatedDate(), line.getModifiedDate());
  }

  public static List<LineResponse> ofList(List<Line> lines) {
    return lines.stream()
      .map(LineResponse::of)
      .collect(Collectors.toList());
  }

  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getColor() {
    return color;
  }

  public List<Station> getStations() {
    return stations;
  }

  public LocalDateTime getCreatedDate() {
    return createdDate;
  }

  public LocalDateTime getModifiedDate() {
    return modifiedDate;
  }
}
