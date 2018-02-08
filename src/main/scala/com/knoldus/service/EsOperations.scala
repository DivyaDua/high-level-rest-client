package com.knoldus.service

import com.knoldus.models.User
import org.elasticsearch.action.delete.{DeleteRequest, DeleteResponse}
import org.elasticsearch.action.index.{IndexRequest, IndexResponse}
import org.elasticsearch.action.search.{SearchRequest, SearchResponse}
import org.elasticsearch.action.update.{UpdateRequest, UpdateResponse}
import org.elasticsearch.client.RestHighLevelClient
import org.elasticsearch.common.xcontent.{XContentFactory, XContentType}
import org.elasticsearch.index.query.QueryBuilders
import org.elasticsearch.search.builder.SearchSourceBuilder
import play.api.libs.json.Json

/**
  * EsOperations trait handles CRUD operations and executes it via RestHighLevelClient.
  */
trait EsOperations {

  val client: RestHighLevelClient = HighLevelRestClient.client
  val index_name = "user-index"
  val type_name = "user"

  def insert(doc: User): IndexResponse = {
    //creates request to insert documents
    val request = new IndexRequest(index_name, type_name, doc.id)
    val jsonString = Json.stringify(Json.toJson(doc))
    request.source(jsonString, XContentType.JSON)
    client.index(request)
  }

  def update(id: String, fieldName: String, value: Any): UpdateResponse = {
    //creates request to update documents having id passed in arguments with the given value
    val updateRequest = new UpdateRequest(index_name, type_name, id)
    val builder = XContentFactory.jsonBuilder
    builder.startObject
    builder.field(fieldName, value)
    builder.endObject
    updateRequest.doc(builder)
    client.update(updateRequest)
  }

  def delete(id: String): DeleteResponse = {
    //creates delete request
    val deleteRequest = new DeleteRequest(index_name, type_name, id)
    client.delete(deleteRequest)
  }

  def searchAll: SearchResponse = {
    //creates request for searching all the documents
    val searchRequest = new SearchRequest(index_name).types(type_name)
    val searchSourceBuilder = new SearchSourceBuilder
    searchSourceBuilder.query(QueryBuilders.matchAllQuery())
    searchRequest.source(searchSourceBuilder)
    println(s"\n\n -- search request -- ${searchRequest.toString}")
    client.search(searchRequest)
  }

  def searchByText(fieldName: String, value: Any): SearchResponse = {
    //creates request for searching documents having field name and value as per the arguments
    val searchRequest = new SearchRequest(index_name).types(type_name)
    val searchSourceBuilder = new SearchSourceBuilder
    searchSourceBuilder.query(QueryBuilders.matchPhraseQuery(fieldName, value))
    searchRequest.source(searchSourceBuilder)
    client.search(searchRequest)
  }

}

