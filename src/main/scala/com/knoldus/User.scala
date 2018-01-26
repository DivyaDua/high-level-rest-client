package com.knoldus

import org.joda.time.DateTime
import play.api.libs.json.{Format, Json}

case class User(id: Int, name: String, age: Int)

object User{
  implicit val format: Format[User] = Json.format[User]
}
