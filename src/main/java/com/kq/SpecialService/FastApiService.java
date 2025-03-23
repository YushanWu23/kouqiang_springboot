package com.kq.SpecialService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class FastApiService {
    @Value("${fastapi.url}")
    private String fastApiUrl;
    @Autowired
    private RestTemplate restTemplate;
    public String callModel(String prompt, Integer max_length, Double top_p, Double temperature,String userId) {

        String url = fastApiUrl + "/";

        // 构造请求体
        String requestBody = String.format(
                "{\"prompt\": \"%s\", \"max_length\": %d, \"top_p\": %.2f, \"temperature\": %.2f,\"userId\": \"%s\"}",
                prompt, max_length, top_p, temperature,userId);

        // 发送 POST 请求
        String response = restTemplate.postForObject(url, requestBody, String.class);

        return response;
    }
    public void clearHistory(String userId) {
        String url = fastApiUrl + "/clearHistory";

        // 构造请求体
        String requestBody = String.format(
                "{\"userId\": \"%s\"}",
                userId
        );

        // 发送 POST 请求
        restTemplate.postForObject(url, requestBody, String.class);
    }
}