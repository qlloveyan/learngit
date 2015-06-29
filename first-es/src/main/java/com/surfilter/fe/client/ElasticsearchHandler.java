package com.surfilter.fe.client;

import java.util.ArrayList;
import java.util.List;

import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;

import com.surfilter.fe.model.Medic;
import com.surfilter.fe.utils.DataFactory;

public class ElasticsearchHandler {

	private Client client;
	
	public ElasticsearchHandler(String ipAddress){
		client = new TransportClient().addTransportAddress( new InetSocketTransportAddress(ipAddress, 9300));
	}
	
	/**
	 * 创建索引
	 * @param indexName 为索引库名,一个es集群中可以有多个索引库。名称必须小写 
	 * @param type 索引类型,是用来区分同索引库下不同类型的数据的,一个索引库下可以有多个索引类型
	 * @param jsonData json格式的数据集合
	 */
	public void createIndexResponse(String indexName , String type , List<String> jsonData){
		//创建索引库 需要注意的是.setRefresh(true)这里一定要设置,否则第一次建立索引查找不到数据
		IndexRequestBuilder requestBuilder = client.prepareIndex(indexName,type).setRefresh(true);
		for( int i = 0 ; i < jsonData.size() ; i++ ){
			requestBuilder.setSource( jsonData.get(i) ).execute().actionGet();
		}
	}
	
	  /**
     * 创建索引
     * @param client
     * @param jsondata
     * @return
     */
    public IndexResponse createIndexResponse(String indexname, String type,String jsondata){
        IndexResponse response = client.prepareIndex(indexname, type)
            .setSource(jsondata)
            .execute()
            .actionGet();
        return response;
    }
    
    public List<Medic> searcher(QueryBuilder queryBuilder , String indexName , String type){
    	List<Medic> midics = new ArrayList<Medic>();
    	SearchResponse searchResponse = client.prepareSearch(indexName).setTypes(type)
    			.setQuery(queryBuilder)
//    			.setFrom(start) //分页展示：开始
//    			.setSize(end) //分页展示： 结束
    			.execute()
    			.actionGet();
    	SearchHits hits = searchResponse.getHits();
    	SearchHit[] hitResult = hits.getHits();
    	if( hitResult.length > 0 ){
    		for( SearchHit temp : hitResult ){
    			Integer id = (Integer) temp.getSource().get("id");
    			String name = (String) temp.getSource().get("name");
    			String function = (String) temp.getSource().get("function");
    			
    			midics.add( new Medic(id, name, function) );
    		}
    	}
    	return midics;
    }
    
    public static void main(String[] args) {
		
    	ElasticsearchHandler handler = new ElasticsearchHandler("127.0.0.1");
    	List<String> data = DataFactory.getData();
    	String indexName = "indexdemo";
    	String type = "typedemo";
//    	handler.createIndexResponse(indexName, type, data);
    	//查询条件
    	//必须包含
//    	QueryBuilder queryBuilder = QueryBuilders.boolQuery().must( QueryBuilders.termQuery("id", 1) );
//    	List<Medic> result = handler.searcher(queryBuilder, indexName, type);
//    	for(Medic temp : result){
//    		System.out.println("id:"+temp.getId()+";name:"+temp.getName()+";function:"+temp.getFunction());
//    	}
    	//根据字段查找
    	BoolQueryBuilder qb = QueryBuilders.boolQuery().must(new QueryStringQueryBuilder("感冒").field("name"))
    												.must( new QueryStringQueryBuilder("止咳化痰").field("function") ) ;
    	List<Medic> result2 = handler.searcher(qb, indexName, type);
    	for(Medic temp : result2){
    		System.out.println("id:"+temp.getId()+";name:"+temp.getName()+";function:"+temp.getFunction());
    	}
	}
}
