package libs.eventbrite

import play.api.libs.json._
import play.api.libs.json.JsString
import play.api.libs.json.JsSuccess
import model.event._
import model.event.EventType.{Other, Eventbrite}
import model.event.Privacy.{Public, Private}
import model.event.Status.{Live, Started}
import play.api.libs.json.JsSuccess
import play.api.libs.json.JsString
import play.api.libs.json.JsNumber
import java.util.Locale
import java.text.Format
import play.api.libs.json.Format

object formats {
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
  implicit object PrivacyFormat extends Format[Privacy]{
    def reads(json: JsValue) = json match {
      case JsString(Public.name) => JsSuccess(Public)
      case JsString(Private.name) => JsSuccess(Private)
      case _ => JsError(""+json.toString + "does not represent a valid Privacy")
    }

    def writes(privacy: Privacy) = {
      JsString(privacy.name)
    }
  }
  implicit object StatusFormat extends Format[Status]{
    def reads(json: JsValue) = json match {
      case JsString(Live.name) => JsSuccess(Live)
      case JsString(Started.name) => JsSuccess(Started)
      case _ => JsError(json.toString + "does not represent a valid Status")
    }

    def writes(status: Status) = {
      JsString(status.name)
    }
  }
  implicit object EventCategoryFormat extends Format[EventCategory]{
    def reads(json: JsValue) = json match {
      case JsString(s)=>JsSuccess(EventCategory(s))
      case _ => JsError(json.toString + "does not represent a valid EventCategory")
    }

    def writes(eventCategory: EventCategory) = {
      JsString(eventCategory.name)
    }
  }

  implicit object CapacityFormat extends Format[Capacity]{
    def reads(json: JsValue) = json match {
      case JsNumber(i)=>JsSuccess(Capacity(i))
      case _ => JsError(json.toString + "does not represent a valid EventCategory")
    }

    def writes(capacity: Capacity) = {
      JsNumber(capacity.underlying)
    }
  }
  implicit object TimeZoneOffsetFormat extends Format[TimeZoneOffset]{
    def reads(json: JsValue) = json match {
      case JsNumber(i)=>JsSuccess(TimeZoneOffset(i))
      case _ => JsError(json.toString + "does not represent a valid EventCategory")
    }

    def writes(offset: TimeZoneOffset) = {
      JsNumber(offset.underlying)
    }
  }
  implicit object TagFormat extends Format[Tag]{
    def reads(json: JsValue) = json match {
      case JsString(s)=>JsSuccess(Tag(s))
      case _ => JsError(json.toString + "does not represent a valid EventCategory")
    }

    def writes(tag: Tag) = {
      JsString(tag.name)
    }
  }
  implicit object PostCodeFormat extends Format[PostCode]{
    def reads(json: JsValue) = json match {
      case JsString(s)=>JsSuccess(PostCode(s))
      case _ => JsError(json.toString + "does not represent a valid EventCategory")
    }

    def writes(postCode: PostCode) = {
      JsString(postCode.code)
    }
  }
  implicit object RegionFormat extends Format[Region]{
    def reads(json: JsValue) = json match {
      case JsString(s)=>JsSuccess(Region(s))
      case _ => JsError(json.toString + "does not represent a valid EventCategory")
    }

    def writes(region: Region) = {
      JsString(region.code)
    }
  }

  implicit object LocaleFormat extends Format[Locale]{
    val COUNTRY_AND_LANGUAGE = """([a-z][a-z])_([A-Z][A-Z])""".r

    def reads(json: JsValue) = json match {
      case JsString(COUNTRY_AND_LANGUAGE( language,country )) => JsSuccess(new Locale(language, country))
      case JsString(s)=> JsSuccess(new Locale(s))
      case _ => JsError(json.toString+" is not a valid json representation for a Locale")
    }

    def writes(locale: Locale) = {
      JsString(locale.getLanguage+"_"+locale.getCountry)
    }

  }
  implicit val countryReads=Json.reads[Country]
  implicit val countryWrites=Json.writes[Country]

  implicit val organizerReads=Json.reads[Organizer]
  implicit val organizerWrites=Json.writes[Organizer]

  implicit val coordinateReads=Json.reads[Coordinate]
  implicit val coordinateWrites=Json.writes[Coordinate]

  implicit val VenueReads=Json.reads[Venue]
  implicit val VenueWrites=Json.writes[Venue]

  implicit val EventReads=Json.reads[Event]
  implicit val EventWrites=Json.writes[Event]
}
