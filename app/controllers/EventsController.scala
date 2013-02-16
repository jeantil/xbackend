package controllers

import play.api.mvc.{Action, Controller}

object EventBriteAPI{
import play.api.Play.current
  import play.api.Configuration
  lazy val APP_KEY: Configuration=current.configuration.getConfig("eventbrite").getOrElse(Configuration.empty)
  lazy val EVENT_SEARCH_URL = APP_KEY.getString("app.key") match {
    case Some(appKey)=>s"https://www.eventbrite.com/json/event_search?app_key=${appKey}&organizer=xebia"
    case _ => throw APP_KEY.globalError("Missing api.key configuration for eventbrite search [%s]".format(APP_KEY))
  }
}
trait EventsController extends Controller {

  import play.api.libs.ws.{Response, WS}
  import play.api.libs.concurrent._
  import concurrent.Future
  import play.api.libs.concurrent.Execution.Implicits._
  import play.api.libs.json.{JsSuccess, JsError, JsResult}
  import play.api.libs.json.Json
  import play.api.libs.functional.syntax._
  import model.event.Events

  def index = Action {
//    def responseToJson(result:NotWaiting[Response]):JsResult[Events]={
//      result match {
//        case Redeemed(response) => Json.fromJson[Events](response.json)
//        case _ => JsError("Eventbrite ws didn't respond")
//      }
//    }

//    def jsonToResult(json:NotWaiting[JsResult]):Result={
//      json match {
//        case success:JsSuccess => Ok(Json.toJson(success.value))
//        case error:JsError => BadRequest("")
//      }
//    }
//    Async {
//      val homePage: Future[Response] = WS.url(EventBriteAPI.EVENT_SEARCH_URL).get()
//      homePage.extend1(responseToJson).extend1(jsonToResult)
//      PurePromise(Ok(""))
//    }
    Ok("")
  }
}

object EventsController extends EventsController