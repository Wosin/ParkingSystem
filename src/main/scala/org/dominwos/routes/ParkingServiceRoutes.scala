package org.dominwos.routes

import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route

trait ParkingServiceRoutes {

  val parkingServiceRoute: Route =
    path("healthcheck") {
      get {
        complete(StatusCodes.OK, "Server OK")
      }
    } ~
      pathPrefix("parking") {
        pathEnd {
          post {
            entity(as[String]) { registrationNumber =>
              if (!registrationNumber.matches("{A-Za-z0-9]{5,6,7}")) {
                complete(StatusCodes.UnprocessableEntity, "Registration Number not valid")
              } else {
                complete(StatusCodes.OK, "Number " + registrationNumber + " added to system")
              }
            }
          }
        } ~
          path("total") {
            get {
              complete(StatusCodes.OK, "Total amount is:" + 0)
            }
          } ~
          path(Segment) {
            registrationNumber => {
              get {
                complete(StatusCodes.OK, registrationNumber)
              }
            }
          } ~
          path(Segment / "currentPrice") {
            registrationNumber => {
              get {
                complete(StatusCodes.OK, "Total price is: " + 0)
              }
            }
          }
      }


}



