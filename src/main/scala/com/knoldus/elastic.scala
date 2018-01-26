package com.knoldus

object elastic extends App with EsOperations {
  val user = User(1, "Divya", 21)
  println(s"\n\n insert 1--------${insert(user)}")

  val user2 = User(2, "Shruti", 22)
  println(s"\n\n insert 2--------${insert(user2)}")

  val user4 = User(3, "Neha", 22)
  println(s"\n\n insert 3--------${insert(user4)}")

  println(s"\n\n delete--------- ${delete(1)}")

  val user3 = User(4, "Divya Dua", 24)
  println(s"\n\n insert 4--------${insert(user3)}")

  println(s"\n\n update--------- ${update(2, "name", "shivangi")}")
  println(s"\n\n search all--------- $searchAll")
  println(s"\n\n search by age--------- ${search("age", 24)}")
  println(s"\n\n search by name--------- ${searchByText("name.keyword", "Neha")}")

}

