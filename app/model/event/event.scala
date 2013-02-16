package model.event

import org.joda.time.DateTime
import java.util.Locale
import play.api.libs.json._
import play.api.libs.json.Format

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
}

sealed trait Privacy {
  val name: String
}
object Privacy{
  object Private extends Privacy {
    val name = "Private"
  }
  object Public extends Privacy {
    val name = "Public"
  }
}

sealed trait Status {
  val name: String
}
object Status{
  object Live extends Status {
    val name = "Live"
  }
  object Started extends Status {
    val name = "Started"
  }
}

case class EventCategory(name: String) extends AnyVal
object EventCategory {

}

case class Capacity(underlying: BigDecimal) extends AnyVal
object Capacity {
}

case class TimeZoneOffset(underlying: BigDecimal) extends AnyVal
object TimeZoneOffset {

}

case class Tag(name: String) extends AnyVal
object Tag {
}

case class PostCode(code: String) extends AnyVal
object PostCode {
}

case class Region(code: String) extends AnyVal
object Region {
}


case class Country(name: String, locale: Locale)

case class Organizer(url: String, description: String, id: String, name: String)

case class Coordinate(latitude: Double, longitude: Double)

case class Venue(city: String,
                 name: String,
                 country: Country,
                 region: Region,
                 coordinates: Coordinate,
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

case class Events(events:Seq[Event])


