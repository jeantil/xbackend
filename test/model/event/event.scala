package model.event

import org.specs2.mutable.Specification
import java.util.Locale

package formats {
  import play.api.libs.json.Json.{toJson,fromJson}
  import play.api.libs.json._
import org.joda.time.DateTime

class EventTypeFormatsSpec extends Specification {
  import model.event.EventType._
  import libs.eventbrite.formats.EventTypeFormats
  "Eventbrite Event Type" should {
    "be converted to json" in {
      val eventbrite = Eventbrite
      val json=toJson(eventbrite)
      json === JsString("Eventbrite")
    }
    "be converted from json" in {
      val eventbrite = fromJson(JsString("Eventbrite"))
      eventbrite ===  JsSuccess(Eventbrite)
    }
  }
  "Other Event Type" should {
    "be converted to json" in {
      val other = Other
      val json=toJson(other)
      json === JsString("Other")
    }
    "be converted from json" in {
      val eventbrite = fromJson(JsString("Other"))
      eventbrite ===  JsSuccess(Other)
    }
  }
}
class PrivacyFormatsSpec extends Specification {
  import model.event.Privacy._
  import libs.eventbrite.formats.PrivacyFormat
  "A Public privacy" should {
    "be written to json" in {
      val privacy = Public
      val json=toJson(privacy)
      json === JsString("Public")
    }
    "be read from json" in {
      val privacy = fromJson(JsString("Public"))
      privacy ===  JsSuccess(Public)
    }
  }
  "A Private privacy" should {
    "be written to json" in {
      val privacy = Private
      val json=toJson(privacy)
      json === JsString("Private")
    }
    "be read from json" in {
      val privacy = fromJson(JsString("Private"))
      privacy ===  JsSuccess(Private)
    }
  }
}
class StatusFormatsSpec extends Specification {
  import model.event.Status._
  import libs.eventbrite.formats.StatusFormat
  "A Live Status" should {
    "be written to json" in {
      val status = Live
      val json=toJson(status)
      json === JsString("Live")
    }
    "be read from json" in {
      val status = fromJson(JsString("Live"))
      status ===  JsSuccess(Live)
    }
  }
  "A Started Status" should {
    "be written to json" in {
      val status = Started
      val json=toJson(status)
      json === JsString("Started")
    }
    "be read from json" in {
      val status = fromJson(JsString("Started"))
      status ===  JsSuccess(Started)
    }
  }

  "A list of Status" should {
    "be written to json" in {
      val statuses:Seq[Status]=Seq(Started,Live)
      val json=toJson(statuses)
      json === JsArray(Seq(JsString("Started"), JsString("Live")))
    }
    "be read from json" in {
      val json=JsArray(Seq(JsString("Started"), JsString("Live")))
      val statuses =fromJson[Seq[Status]](json)
      statuses === JsSuccess(Seq(Started,Live))
    }
  }
}
class EventCategoryFormatsSpec extends Specification {
  import model.event.EventCategory
  import libs.eventbrite.formats.EventCategoryFormat
  "A Category" should {
    "be read from json" in {
      val json = JsString("category")
      val category:EventCategory = EventCategory("category")
      val jsresult = fromJson[EventCategory](json)
      jsresult === JsSuccess(category)
    }
    "be written to json" in {
      val json = JsString("category")
      val category:EventCategory = EventCategory("category")
      val jscountry = toJson(category)
      jscountry === json
    }
  }
}
class LocaleFormatSpec extends Specification {
  import java.util.Locale
  import libs.eventbrite.formats.LocaleFormat
  "A Locale" should {
    "be read from json" in {
      val json = JsString("fr_FR")
      val locale:Locale = Locale.FRANCE
      val jsresult = fromJson[Locale](json)
      jsresult === JsSuccess(locale)
    }
    "be written to json" in {
      import libs.eventbrite.formats.countryWrites
      val json = JsString("fr_FR")
      val locale:Locale = Locale.FRANCE
      val result = toJson(locale)
      result === json
    }
  }
}
class CountryFormatsSpec extends Specification {
  val jsonCountry = Json.obj("name"->"france", "locale"->"fr_FR")
  val country:Country = Country("france", Locale.FRANCE)
  "A Country" should {
    "be read from json" in {
      import libs.eventbrite.formats.countryReads
      val jsresult = fromJson[Country](jsonCountry)
      jsresult === JsSuccess(country)
    }
    "be written to json" in {
      import libs.eventbrite.formats.countryWrites
      val result = toJson(country)
      result === jsonCountry
    }
  }
}
class OrganizerFormatsSpec extends Specification {
  val jsonOrganizer = Json.obj("url"->"http url", "description"->"description", "id"->"id", "name"->"name")
  val organizer:Organizer = Organizer("http url", "description", "id", "name")
  "An Organizer" should {
    "be read from json" in {
      import libs.eventbrite.formats.organizerReads
      val result = fromJson[Organizer](jsonOrganizer)
      result === JsSuccess(organizer)
    }
    "be written to json" in {
      import libs.eventbrite.formats.organizerWrites
      val result = toJson(organizer)
      result === jsonOrganizer
    }
  }
}
class CoordinateFormatsSpec extends Specification{
  val jsonCoordinate = Json.obj("latitude"->1.0, "longitude"->2.0)
  val coordinate:Coordinate =Coordinate(1.0 ,2.0)

  "A coordinate" should {
    "be read from json" in {
      import libs.eventbrite.formats.coordinateReads
      val result = fromJson[Coordinate](jsonCoordinate)
      result === JsSuccess(coordinate)
    }
    "be written to json" in {
      import libs.eventbrite.formats.coordinateWrites
      val result = toJson(coordinate)
      result === jsonCoordinate
    }
  }
}
class VenueFormatsSpec extends Specification{
  // model
  val coordinate:Coordinate =Coordinate(1.0 ,2.0)
  val country:Country = Country("france", Locale.FRANCE)
  val venue:Venue=Venue("paris","xebia", country, Region(""), coordinate, PostCode("75008"), "157","","idvenue")
  // json
  val jsonCountry = Json.obj("name"->"france", "locale"->"fr_FR")
  val jsonCoordinate = Json.obj("latitude"->1.0, "longitude"->2.0)
  val jsonVenue = Json.obj("city"->"paris",
                      "name"->"xebia",
                      "country"->jsonCountry,
                      "region"->"",
                      "coordinates"->jsonCoordinate,
                      "postCode"->"75008",
                      "address"->"157",
                      "address2"->"",
                      "id"->"idvenue"
                      )


  "A venue" should {
    "be read from json" in {
      import libs.eventbrite.formats.VenueReads
      val result = fromJson[Venue](jsonVenue)
      result === JsSuccess(venue)
    }
    "be written to json" in {
      import libs.eventbrite.formats.VenueWrites
      val result = toJson(venue)
      result === jsonVenue
    }
  }
}

class EventFormatSpec extends Specification {  // model
val coordinate:Coordinate =Coordinate(1.0 ,2.0)
  val organizer:Organizer = Organizer("http url", "description", "idorganizer", "name")
  val country:Country = Country("france", Locale.FRANCE)
  val venue:Venue=Venue("paris","xebia", country, Region(""), coordinate, PostCode("75008"), "157","","idvenue")
  val created:DateTime=DateTime.parse("2012-11-07 01:44:54")
  val startDate:DateTime=DateTime.parse("2012-12-12 19:00:00")
  val endDate:DateTime=DateTime.parse("2012-12-13 24:00:00")

  val event:Event = Event("idevent",EventType.Eventbrite, EventCategory("myCategory"), Capacity(10),
                          "title",startDate, endDate, TimeZoneOffset("GMT+0100"), Seq(Tag("tag1"),Tag("tag2")),
                           created,"url", Privacy.Public,Status.Live,"descripton","description_txt", organizer, venue )
  // json
  val jsonCountry = Json.obj("name"->"france", "locale"->"fr_FR")
  val jsonOrganizer = Json.obj("url"->"http url", "description"->"description", "id"->"idorganizer", "name"->"name")
  val jimport UserBSONReadersonCoordinate = Json.obj("latitude"->1.0, "longitude"->2.0)
  val jsonVenue = Json.obj("city"->"paris",
    "name"->"xebia",
    "country"->jsonCountry,
    "region"->"",
    "coordinates"->jsonCoordinate,
    "postCode"->"75008",
    "address"->"157",
    "address2"->"",
    "id"->"idvenue"
  )
  val jsonEvent = Json.obj(
    "id"->"idevent",
    "eventType"->"Eventbrite",
    "category"->"myCategory",
    "capacity"->10,
    "title"->"mytitle",
    "startDate"->"2012-12-13 19:00:00",
    "endDate"->"2012-12-13 24:00:00",
    "offset"->"GMT+0100",
    "tags"-> Seq("tag1","tag2"),
    "created"->"2012-11-07 01:44:54",
    "url" -> "url",
    "privacy"->"Public",
    "status"->"Live",
    "description"->"description",
    "description_txt"->"description_txt",
    "organizer"->jsonOrganizer,
    "venue"->jsonVenue
  )

  "An Event" should {
    "be read from json " in {
      todo
    }
  }
}

}