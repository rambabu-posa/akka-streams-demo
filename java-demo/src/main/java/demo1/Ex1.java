package demo1;

import java.util.stream.IntStream;

import scala.concurrent.Future;
import scala.runtime.BoxedUnit;
import akka.actor.ActorSystem;
import akka.stream.ActorFlowMaterializer;
import akka.stream.javadsl.RunnableFlow;
import akka.stream.javadsl.Sink;
import akka.stream.javadsl.Source;

public class Ex1 {
  public static void main(String[] args) {
    ActorSystem system = ActorSystem.create("demo1");
    ActorFlowMaterializer materializer = ActorFlowMaterializer.create(system);

    // Create a source with some integer and print them
    IntStream stream = IntStream.range(1, 4);
    Source<Integer, BoxedUnit> source = Source.from(() -> stream.iterator());
    Sink<Integer, Future<BoxedUnit>> sink = Sink.foreach(x -> System.out.println(x));
    RunnableFlow<BoxedUnit> runnable = source.to(sink);
    runnable.run(materializer);
  }
}
