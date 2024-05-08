package com.study.spring_batch.naver;

import com.fasterxml.jackson.databind.util.JSONPObject;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Slf4j(topic = "NAVER API")
@Service
public class NaverApiService {

    private final RestTemplate restTemplate;

    public NaverApiService(RestTemplateBuilder builder) {
        this.restTemplate = new RestTemplate();
    }

    public List<ItemDto> searchItems(String query) {
        // 요청 URL 만들기
        URI uri = UriComponentsBuilder
                .fromUriString("https://openapi.naver.com") // 기본 URI 설정
                .path("/v1/search/shop.json") // UIR에 추가적인 경로 설정. 네이버 쇼핑 검색 api 경로
                .queryParam("display", 15) //  15개씩 결과 보여주기
                .queryParam("query", query)
                .encode() // 꼭 필요. 쿼리 파라미터에 공백이나 특수문자 들어간 경우 처리
                .build() // Uri components 인스턴스 생성
                .toUri(); // 실제 Uri 객체로 변환
        log.info("uri = " + uri);

        // uri + client id + client secret 포함하여 GET 요청 생성
        RequestEntity<Void> requestEntity = RequestEntity
                .get(uri)
                .header("X-Naver-Client-Id", "KyPZKp8CF9XptkHDpgfg")
                .header("X-Naver-Client-Secret", "Re1mRmeOZR")
                .build();

        // 위의 Get 요청을 전송한 후 String 응답을 받아 responseEntity 객체에 저장
        ResponseEntity<String> responseEntity = restTemplate.exchange(requestEntity, String.class);

        log.info("NAVER API Status Code : " + responseEntity.getStatusCode());

        return fromJSONtoItems(responseEntity.getBody());
    }

    private List<ItemDto> fromJSONtoItems(String body) {
        try {
            JSONObject jsonObject = new JSONObject(body);
            JSONArray items = jsonObject.getJSONArray("items");
            List<ItemDto> itemDtoList = new ArrayList<>();

            for (Object item : items) { // JSONArray는 Object 타입이므로, 아래에서 JSONObject로 캐스팅하여 생성자에 전달
                ItemDto itemDto = new ItemDto((JSONObject) item);
                itemDtoList.add(itemDto);
            }
            return itemDtoList;
        } catch (JSONException e) {
            log.error("JSON 파싱 도중 오류 발생");
            return Collections.emptyList();
        }
    }
}
