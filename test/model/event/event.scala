package model.event

import org.specs2.mutable.Specification

class EventTypeFormatsSpec extends Specification {
  import play.api.libs.json.Json.{toJson,fromJson}
  import play.api.libs.json._
  import model.event.EventType._

  "An Event Type" should {
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
}
class PrivacyFormatsSpec extends Specification {
  import play.api.libs.json.Json.{toJson,fromJson}
  import play.api.libs.json._
  import model.event.Privacy._

  "A Privacy" should {
    "be converted to json" in {
      val priv = Private
      val json=toJson(priv)
      json === JsString("private")
    }
    "be converted from json" in {
      val privacy = fromJson(JsString("public"))
      privacy ===  JsSuccess(Public)
    }
  }
}

class StatusFormatsSpec extends Specification {
  import play.api.libs.json.Json.{toJson,fromJson}
  import play.api.libs.json._
  import model.event.Status._

  "A Status" should {
    "be converted to json" in {
      val ok = OK
      val json=toJson(ok)
      json === JsString("oOK")
    }
    "be converted from json" in {
      val privacy = fromJson(JsString("OK"))
      privacy ===  JsSuccess(OK)
    }
  }
}