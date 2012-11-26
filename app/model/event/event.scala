package model.event

import org.joda.time.DateTime
import java.util.Locale
import play.api.libs.json._
import play.api.libs.json.Format
import model.event.EventType.Eventbrite
import model.event.Privacy.Private

sealed trait EventType {
  val name: String
}
object EventType{
  object Eventbrite extends EventType {
    val name = "Eventbrite"
  }
  object Other extends EventType {
    val name = "Other"
  }
  implicit object EventTypeFormats extends Format[EventType]{
    def reads(json: JsValue) = json match {
      case JsString("Eventbrite") => JsSuccess(Eventbrite)
      case JsString("Other") => JsSuccess(Other)
      case _ => JsError(""+json.toString + "does not represent a valid EventType")
    }

    def writes(eventType: EventType) = {
      JsString(eventType.name)
    }
  }
}

sealed trait Privacy {
  val name: String
}

object Privacy{
  object Private extends Privacy {
    val name = "private"
  }
  object Public extends Privacy {
    val name = "public"
  }
  implicit object PrivacyFormat extends Format[Privacy]{
    def reads(json: JsValue) = json match {
      case JsString("public") => JsSuccess(Public)
      case JsString("private") => JsSuccess(Private)
      case _ => JsError(""+json.toString + "does not represent a valid Privacy")
    }

    def writes(privacy: Privacy) = {
      JsString(privacy.name)
    }
  }
}
sealed trait Status {
  val name: String
}

object Status{
  object OK extends Status {
    val name = "OK"
  }
  implicit object StatusFormat extends Format[Status]{
    def reads(json: JsValue) = json match {
      case JsString("OK") => JsSuccess(OK)
      case _ => JsError(""+json.toString + "does not represent a valid Privacy")
    }

    def writes(status: Status) = {
      JsString(status.name)
    }
  }
}

case class EventCategory(name: String) extends AnyVal

case class Capacity(capacity: Int) extends AnyVal

case class TimeZoneOffset(offset: Int) extends AnyVal

case class Tag(tag: String) extends AnyVal

case class PostCode(code: String) extends AnyVal

case class Region(code: String) extends AnyVal

case class Country(name: String, locale: Locale)

case class Organizer(url: String, description: String, id: String, name: String)

case class Coordinates(latitude: Double, longitude: Double)

case class Venue(city: String,
                 name: String,
                 country: Country,
                 region: Region,
                 coordinates: Coordinates,
                 postCode: PostCode, address: String,
                 address2: String,
                 id: String)

case class Event(id: String,
                 eventType: EventType,
                 category: EventCategory,
                 capacity: Capacity,
                 title: String,
                 startDate: DateTime,
                 endDate: DateTime,
                 offset: TimeZoneOffset,
                 tags: Seq[Tag],
                 created: DateTime,
                 url: String,
                 privacy: Privacy,
                 status: Status,
                 description: String,
                 description_txt: String,
                 organizer: Organizer,
                 venue: Venue)

object Formats {

}