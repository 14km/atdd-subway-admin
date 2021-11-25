# 🚀 4단계 - 구간 제거 기능

# 요구사항

### 지하철 구간 제거 기능을 구현하기

- [X] 노선의 구간을 제거하는 기능을 구현하기
    - [X] 종점이 제거될 경우 다음으로 오던 역이 종점
    - [X] 중간역이 제거될 경우 재배치
- [X] 구간 삭제 시 예외 케이스를 고려하기
    - [X] 노선에 등록되어있지 않은 역을 제거할 때 예외처리
    - [X] 구간이 하나인 노선에서 종점을 제거할 때 예외처리

**지하철 구간 삭제 request**

**`DELETE /lines/1/sections?stationId=2 HTTP/1.1 accept: */*
host: localhost:52165`**

# 요구사항 설명

## 노선의 구간을 제거하는 기능을 구현하기

- 종점이 제거될 경우 다음으로 오던 역이 종점이 됨
- 중간역이 제거될 경우 재배치를 함
    - 노선에 A - B - C 역이 연결되어 있을 때 B역을 제거할 경우 A - C로 재배치 됨
    - 거리는 두 구간의 거리의 합으로 정함

[https://nextstep-storage.s3.ap-northeast-2.amazonaws.com/a8751b272c36421481c779e5a743a928](https://nextstep-storage.s3.ap-northeast-2.amazonaws.com/a8751b272c36421481c779e5a743a928)

## 구간 삭제 시 예외 케이스를 고려하기

- 기능 설명을 참고하여 예외가 발생할 수 있는 경우를 검증할 수 있는 인수 테스트를 만들고 이를 성공 시키세요.

> 예시) 노선에 등록되어있지 않은 역을 제거하려 한다.
>

### 구간이 하나인 노선에서 마지막 구간을 제거할 때

- 제거할 수 없음

<img src=https://nextstep-storage.s3.ap-northeast-2.amazonaws.com/b8db3f754c2c4d3684dafe29eae53270>

# 힌트

## 구간 제거

### 구간 제거 요청 처리

```
@DeleteMapping("/{lineId}/sections")
public ResponseEntity removeLineStation(
        @PathVariable Long lineId,
        @RequestParam Long stationId) {
    lineService.removeSectionByStationId(lineId, stationId);
    return ResponseEntity.ok().build();
}

```

## 프론트엔드

<img src=https://techcourse-storage.s3.ap-northeast-2.amazonaws.com/019e7f82cb5d4b0d833ce41e3c43fd0f>