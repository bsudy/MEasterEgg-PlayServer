package controllers

import play.api._
import play.api.mvc._
import play.api.libs.iteratee.Iteratee
import play.api.libs.iteratee.Enumerator
import play.api.libs.iteratee.PushEnumerator
import play.api.libs.iteratee.Enumerator.Pushee
import akka.actor.Actor
import akka.actor.ActorSystem
import akka.actor.Props
import akka.routing.BroadcastRouter
import play.api.libs.iteratee.Concurrent

object Application extends Controller {
  
  def index = Action {
    Ok(views.html.index("Your new application is ready."))
  }

  val (out, channel) = Concurrent.broadcast[String]
  
  def sync = WebSocket.using[String] { request =>
  
    val func = (s : String) => {
      channel.push(s)
    } 
    
    val in  = Iteratee.foreach[String](func).mapDone { _ =>
      println("Disconnect")
    }
    
    val out2 = out.flatMap[String] {s: String =>
      println("ssss" + s)
      Enumerator(s)
    }
    
    (in, out2)
  }
  
}