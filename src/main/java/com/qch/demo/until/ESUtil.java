package com.qch.demo.until;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.admin.indices.get.GetIndexRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qch.demo.entity.User;

/**
 * ElasticSearch搜索引擎
 * @author 94776
 *
 */
public class ESUtil {
	
	@Autowired
	private RestHighLevelClient restClient;
	
	/**
	 * 添加索引
	 * @throws IOException 
	 */
	public void createIndex(String index, String type) throws IOException {
		CreateIndexRequest request = new CreateIndexRequest(index);	
		
		request.settings(Settings.builder()
				.put("number_of_shards", 5)     //分片数
				.put("number_of_replicas", 1)	//备份数
				);
		request.mapping(type, "  {\n" +
			    "    \"man\": {\n" +
			    "      \"properties\": {\n" +
			    "        \"name\": {\n" +
			    "          \"type\": \"keyword\"\n" +
			    "        },\n" +
			    "        \"age\": {\n" +
			    "          \"type\": \"long\"\n" +
			    "        },\n" +
			    "        \"address\": {\n" +
			    "          \"type\": \"text\"\n" +
			    "        },\n" +
			    "        \"birthday\": {\n" +
			    "          \"type\": \"date\",\n" +
			    "          \"format\": \"yyyy-MM-dd\"\n" +
			    "        }\n" +
			    "      }\n" +
			    "    }\n" +
			    "  }",XContentType.JSON);
		
		CreateIndexResponse res = restClient.indices().create(request, RequestOptions.DEFAULT);
		System.out.println(res.isAcknowledged());
	}
	
	/**
	 * 判断索引是否存在
	 * @throws IOException 
	 */
	public void isExistIndex(String index) throws IOException {
		GetIndexRequest request = new GetIndexRequest().indices(index);
		boolean bool = restClient.indices().exists(request, RequestOptions.DEFAULT);
		System.out.println(bool);
	}
	
	/**
	 * 删除索引
	 * @throws IOException 
	 */
	public void deleteIndex(String index) throws IOException {
		DeleteIndexRequest request = new DeleteIndexRequest().indices(index);
		AcknowledgedResponse res = restClient.indices().delete(request, RequestOptions.DEFAULT);
		System.out.println(res.isAcknowledged());
	}
	
	/**
	 * 添加文档
	 * @throws IOException 
	 */
	public void createDoc(String index,String type,User user) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		String jsonString = mapper.writeValueAsString(user);
		
		IndexRequest request = new IndexRequest(index,type,user.getUsername());
		request.source(jsonString);
		IndexResponse res = restClient.index(request, RequestOptions.DEFAULT);
		System.out.println(res.getResult());
	}
	
	/**
	 * 修改文档
	 * @throws IOException 
	 */
	public void updateDoc(String index,String type,User user) throws IOException {
		Map<String,String> map = new HashMap<String,String>();
		UpdateRequest request = new UpdateRequest(index,type,user.getUsername());
		request.doc(map);
		UpdateResponse res = restClient.update(request, RequestOptions.DEFAULT);
		System.out.println(res.getResult());
	}
	
	/**
	 * 删除文档
	 * @throws IOException 
	 */
	public void deleteDoc(String index,String type,User user) throws IOException {
		DeleteRequest request = new DeleteRequest(index,type,user.getUsername());
		DeleteResponse res = restClient.delete(request, RequestOptions.DEFAULT);
		System.out.println(res.getResult());
	}
	
	/**
	 * 批量操作，如批量删除文档
	 * @throws IOException 
	 */
	public void bulkDeleteDoc(String index,String type) throws IOException {
		BulkRequest request = new BulkRequest();
		request.add(new DeleteRequest(index,type,"1"));
		request.add(new DeleteRequest(index,type,"2"));
		request.add(new DeleteRequest(index,type,"3"));
		BulkResponse res = restClient.bulk(request, RequestOptions.DEFAULT);
		System.out.println(res.toString());
	}
	
	/**
	 * term查询，terms查询，全值匹配，不分词
	 * @throws IOException 
	 */
	public void term(String index,String type) throws IOException {
		SearchRequest request = new SearchRequest(index);
		request.types(type);
		
		SearchSourceBuilder builder = new SearchSourceBuilder();
		builder.from(0);
		builder.size(5);
		builder.query(QueryBuilders.termQuery("name", "邱朝海"));
		
		request.source(builder);
		
		SearchResponse res = restClient.search(request, RequestOptions.DEFAULT);
		for(SearchHit hit : res.getHits().getHits()) {
			System.out.println(hit.getSourceAsString());
		}
	}
	
	/**
	 * match查询
	 * @throws IOException 
	 */
	public void matchSearch(String index,String type) throws IOException {
		SearchRequest request = new SearchRequest(index);
		request.types(type);
		
		SearchSourceBuilder builder = new SearchSourceBuilder();
		builder.from(0);
		builder.size(5);
		builder.query(QueryBuilders.matchQuery("name", "qch"));
		
		request.source(builder);
		
		SearchResponse res = restClient.search(request, RequestOptions.DEFAULT);
		
		for(SearchHit hit:res.getHits().getHits()) {
			System.out.println(hit.getSourceAsString());
		}
	}
	
	/**
	 * id查询
	 * @throws IOException 
	 */
	public void idSearch(String index,String type,String id) throws IOException {
		GetRequest request = new GetRequest(index,type,id);
		GetResponse res = restClient.get(request, RequestOptions.DEFAULT);
		System.out.println(res.getSourceAsString());
	}
	
	/**
	 * ids查询
	 * @throws IOException 
	 */
	public void idsSearch(String index,String type) throws IOException {
		SearchRequest request = new SearchRequest(index);
		request.types(type);
		
		SearchSourceBuilder builder = new SearchSourceBuilder();
		builder.from(0);
		builder.size(5);
		builder.query(QueryBuilders.idsQuery().addIds("1","2","3"));
		
		request.source(builder);
		
		SearchResponse res = restClient.search(request, RequestOptions.DEFAULT);
		for(SearchHit hit: res.getHits().getHits()) {
			System.out.println(hit.getSourceAsString());
		}
	}
	
	/**
	 * bool查询
	 */
	public void boolSearch(String index,String type) throws IOException {
		SearchRequest request = new SearchRequest(index);
		request.types(type);
		
		SearchSourceBuilder builder = new SearchSourceBuilder();
		builder.from(0);
		builder.size(5);
		
		BoolQueryBuilder query = new BoolQueryBuilder();
		//查询地址为浙江省或湖北省
		query.should(QueryBuilders.termQuery("address", "浙江省"));
		query.should(QueryBuilders.termQuery("address", "湖北省"));
		//名字不是张三的
		query.mustNot(QueryBuilders.termQuery("name", "张三"));
		//年龄必须为25
		query.must(QueryBuilders.matchQuery("age", 25));
		
		builder.query(query);
		
		request.source(builder);
		
		SearchResponse res = restClient.search(request, RequestOptions.DEFAULT);
		for(SearchHit hit: res.getHits().getHits()) {
			System.out.println(hit.getSourceAsString());
		}
	}
	
	/**
	 * 高亮查询
	 * @throws IOException 
	 */
	public void hightLightSearch(String index,String type) throws IOException {
		SearchRequest request = new SearchRequest(index);
		request.types(type);
		
		SearchSourceBuilder builder = new SearchSourceBuilder();
		builder.from(0);
		builder.size(20);
		builder.query(QueryBuilders.matchQuery("name","邱"));
		
		HighlightBuilder hBuilder = new HighlightBuilder();
		hBuilder.field("name", 10)
				.preTags("<font color='red'>")
				.postTags("</font>");
		
		builder.highlighter(hBuilder);
		
		request.source(builder);
		

		SearchResponse res = restClient.search(request, RequestOptions.DEFAULT);
		
		for(SearchHit hit: res.getHits().getHits()) {
			System.out.println(hit.getHighlightFields().get("name"));
		}
	}
}
