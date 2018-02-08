package com.knoldus.service

import com.knoldus.models.User

object ElasticSearchProcessor extends App with EsOperations {
  val user1 = User("1", "Divya", 21)
  println(s"Result of inserting 1st User in ES-----${insert(user1)}")

//  val user2 = User("2", "Shruti", 22)
//  println(s"Result of inserting 2nd User in ES-----${insert(user2)}")

//  val user3 = User("3", "Neha", 24)
//  println(s"Result of inserting 3rd User in ES-----${insert(user3)}")

  //  println(s"Searching documents with matchAllQuery-----$searchAll")

  //  println(s"Result of deleting User with id: 3 in ES-----${delete("3")}")

  //  println(s"Result of updating User name having id: 2 in ES-----${update("2", "name", "shivangi")}")

  //  println(s"Searching documents using match_phrase query for name field-----${searchByText("name.keyword", "Neha")}")

}

