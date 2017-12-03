package org.dominwos.app

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.stream.ActorMaterializer
import com.typesafe.scalalogging.LazyLogging
import org.dominwos.routes.ParkingServiceRoutes

import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Success}

object Boot extends ParkingServiceRoutes with LazyLogging {
  def main(args: Array[String]): Unit = {
    implicit val system: ActorSystem = ActorSystem("ParkingSystem")
    implicit val materializer = ActorMaterializer()
    val binding = Http().bindAndHandle(parkingServiceRoute, "localhost", 8080)
    binding.onComplete {
      case Success(_) => logger.debug("Server started sucessfully")
      case Failure(reason) => logger.error("Failed to start server!", reason)
    }

  }

}
