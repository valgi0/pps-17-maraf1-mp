
package it.unibo.pps2017.discovery.restAPI

import io.vertx.core.http.HttpMethod
import io.vertx.scala.ext.web.{Router, RoutingContext}
import it.unibo.pps2017.commons.remote.API.RestAPI
import it.unibo.pps2017.server.model.{GET, POST, Request, RouterResponse}


object DiscoveryAPI {

  /**
    * Trait of DiscoveryAPI.
    */
  sealed trait DiscoveryAPI extends RestAPI {

    /**
      * Convert the RestAPI to a request object to register into a router.
      *
      * @param router the router on which request will be registered.
      * @param handle the handler of the request.
      * @return a Request object build from the RestAPI.
      */
    def asRequest(router: Router, handle:(RoutingContext, RouterResponse) => Unit):Request
  }


  /**
    * RestAPI to register a new server on RestUtils server.
    */
  case object RegisterServerAPI extends DiscoveryAPI {

    override def path: String = "/registerserver"

    override def httpMethod: HttpMethod = HttpMethod.POST

    override def asRequest(router: Router, handle: (RoutingContext, RouterResponse) => Unit): Request =
      POST(router,path,handle)
  }

  /**
    * RestAPI to retrieve less busy server.
    */
  case object GetServerAPI extends DiscoveryAPI {

    override def path: String = "/getserver"

    override def httpMethod: HttpMethod = HttpMethod.GET

    override def asRequest(router: Router, handle: (RoutingContext, RouterResponse) => Unit): Request =
      GET(router,path,handle)
  }

  /**
    * RestAPI to increase the number of matches on a specified server.
    */
  case object IncreaseServerMatchesAPI extends DiscoveryAPI {


    override def httpMethod: HttpMethod = HttpMethod.POST

    override def asRequest(router: Router, handle: (RoutingContext, RouterResponse) => Unit): Request =
      POST(router, path, handle)

    override def path: String = "/increaseservermatches"
  }

  /**
    * RestAPI to decrease the number of matches on a specified server.
    */
  case object DecreaseServerMatchesAPI extends DiscoveryAPI {


    override def httpMethod: HttpMethod = HttpMethod.POST

    override def asRequest(router: Router, handle: (RoutingContext, RouterResponse) => Unit): Request =
      POST(router, path, handle)

    override def path: String = "/decreaseservermatches"
  }

  /**
    * values method, analog to java's enumeration's values() method.
    *
    * @return a Set containing all the objects in the DiscoveryAPI object.
    */
  def values: Set[DiscoveryAPI] = Set(GetServerAPI, RegisterServerAPI,
    IncreaseServerMatchesAPI, DecreaseServerMatchesAPI, RegisterMatchAPI,
    RemoveMatchAPI, GetAllMatchesAPI, RegisterSocialIDAPI, UnregisterSocialIDAPI)

  /**
    * RestAPI to register a new match on the discovery,
    */
  case object RegisterMatchAPI extends DiscoveryAPI {

    val MATCH_ID_KEY = "matchID"

    override def httpMethod: HttpMethod = HttpMethod.POST

    override def asRequest(router: Router, handle: (RoutingContext, RouterResponse) => Unit): Request =
      POST(router, path, handle)

    override def path: String = "/registermatch"
  }

  /**
    * RestAPI to Remove a match from the discovery server.
    */
  case object RemoveMatchAPI extends DiscoveryAPI {

    val MATCH_ID_KEY = "matchID"

    override def httpMethod: HttpMethod = HttpMethod.POST

    override def asRequest(router: Router, handle: (RoutingContext, RouterResponse) => Unit): Request =
      POST(router, path, handle)

    override def path: String = "/removermatch"
  }

  /**
    * RestAPI to get all the current played matches on the system.
    */
  case object GetAllMatchesAPI extends DiscoveryAPI {

    override def httpMethod: HttpMethod = HttpMethod.GET

    override def asRequest(router: Router, handle: (RoutingContext, RouterResponse) => Unit): Request =
      GET(router, path, handle)

    override def path: String = "/getallmatches"
  }

  /**
    * RestAPI for registering a new social actor on the server.
    */
  case object RegisterSocialIDAPI extends DiscoveryAPI {

    val SOCIAL_ID = "playerid"
    val SOCIAL_ACTOR = "actorref"

    override def httpMethod: HttpMethod = HttpMethod.POST

    override def asRequest(router: Router, handle: (RoutingContext, RouterResponse) => Unit): Request =
      POST(router, path, handle)

    override def path: String = "/registersocialidapi"
  }

  /**
    * RestAPI for unregistering a new social actor on the server.
    */
  case object UnregisterSocialIDAPI extends DiscoveryAPI {

    val SOCIAL_ID = "playerid"

    override def httpMethod: HttpMethod = HttpMethod.POST

    override def asRequest(router: Router, handle: (RoutingContext, RouterResponse) => Unit): Request =
      POST(router, path, handle)

    override def path: String = "/unregistersocialidapi"
  }

  /**
    * RestAPI to get all online players list.
    */
  case object GetAllOnlinePlayersAPI extends DiscoveryAPI {

    override def httpMethod: HttpMethod = HttpMethod.GET

    override def asRequest(router: Router, handle: (RoutingContext, RouterResponse) => Unit): Request =
      GET(router, path, handle)

    override def path: String = "/getallonlineplayerapi"
  }

  /**
    * Standard connection parameters used by every API.
    */
  object StandardParameters {
    val IP_KEY = "ip"
    val PORT_KEY = "port"
  }

}
