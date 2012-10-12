package controllers

import play.api._
import play.api.mvc._

trait Application extends Controller {
  def index = Action {
    Ok("index")
  }
}
object Application extends Application
