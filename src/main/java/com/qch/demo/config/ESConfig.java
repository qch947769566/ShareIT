package com.qch.demo.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ESConfig {
	
	@Value("springboot.elasticsearch.host")
	private String host;
	
	@Value("springboot.elasticsearch.port")
	private int port;
	
	@Bean	
	public RestHighLevelClient restClient() {
		RestHighLevelClient client = new RestHighLevelClient(
				RestClient.builder(new HttpHost(host,port)));
		return client;
	}
}
