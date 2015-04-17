package demo1

import akka.actor.ActorSystem
import akka.stream.ActorFlowMaterializer
import akka.stream._
import akka.stream.scaladsl._

import scala.concurrent.Future
import scala.concurrent.Await
import scala.concurrent.duration.Duration
import scala.concurrent.duration._

object Ex3 extends App {
  implicit val system = ActorSystem("demo1")
  implicit val materializer = ActorFlowMaterializer()
  import system.dispatcher

  // Create a stream that folds it's elements
  val numbers = Source(1 to 10)
  val foldingSink = Sink.fold[Int, Int](0)(_ + _)
  val foldingResult = numbers.runWith(foldingSink)
  println(Await.result(foldingResult, Duration.Inf))
}