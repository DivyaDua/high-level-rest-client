package com.knoldus

import org.elasticsearch.action.delete.{DeleteRequest, DeleteResponse}
import org.elasticsearch.action.index.{IndexRequest, IndexResponse}
import org.elasticsearch.action.search.SearchRequest
import org.elasticsearch.action.update.{UpdateRequest, UpdateResponse}
import org.elasticsearch.client.RestHighLevelClient
import org.elasticsearch.common.xcontent.{XContentFactory, XContentType}
import org.elasticsearch.index.query.QueryBuilders
import org.elasticsearch.search.builder.SearchSourceBuilder
import play.api.libs.json.Json

trait EsOperations {

  val client: RestHighLevelClient = EsClient.client
  val index_name = "user-index"
  val type_name = "user"

  def insert(doc: User): IndexResponse = {
    val request = new IndexRequest(index_name, type_name, s"${doc.id}")
    val jsonString = Json.stringify(Json.toJson(doc))
    request.source(jsonString, XContentType.JSON)
    client.index(request)
  }

  def update(id: Int, fieldName: String, value: Any): UpdateResponse = {
    val updateRequest = new UpdateRequest(index_name, type_name, s"$id")
    val builder = XContentFactory.jsonBuilder
    builder.startObject
    builder.field(fieldName, value)
    builder.endObject
    updateRequest.doc(builder)
    client.update(updateRequest)
  }

  def delete(id: Int): DeleteResponse = {
    val deleteRequest = new DeleteRequest(index_name, type_name, s"$id")
    client.delete(deleteRequest)
  }

  def searchAll = {
    val searchRequest = new SearchRequest(index_name)
    val searchSourceBuilder = new SearchSourceBuilder
    searchSourceBuilder.query(QueryBuilders.matchAllQuery())
    searchRequest.source(searchSourceBuilder)
    client.search(searchRequest)
  }

  def search(fieldName: String, value: Any) = {
    val searchRequest = new SearchRequest(index_name).types(type_name)
    val searchSourceBuilder = new SearchSourceBuilder
    searchSourceBuilder.query(QueryBuilders.termQuery(fieldName, value))
    searchRequest.source(searchSourceBuilder)
    client.search(searchRequest)
  }

  def searchByText(fieldName: String, value: Any) = {
    val searchRequest = new SearchRequest(index_name).types(type_name)
    val searchSourceBuilder = new SearchSourceBuilder
    searchSourceBuilder.query(QueryBuilders.matchPhraseQuery(fieldName, value))
    searchRequest.source(searchSourceBuilder)
    client.search(searchRequest)
  }

}

