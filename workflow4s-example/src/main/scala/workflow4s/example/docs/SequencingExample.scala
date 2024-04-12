package workflow4s.example.docs

import cats.effect.IO
import workflow4s.example.docs.Context.WIO
import workflow4s.wio.SignalDef

object SequencingExample {

  // start_declarative
  val step1 = WIO.pure[MyState](MyState(1)).autoNamed()
  val step2 = WIO.pure[MyState](MyState(2)).autoNamed()
  val step3 = WIO.pure[MyState](MyState(3)).autoNamed()

  val sequence1 = step1 >>> step2 >>> step3
  val sequence2 = step1.andThen(step2).andThen(step3)
  // end_declarative

  object Dynamic {
    // start_dynamic
    val step1 = WIO.pure[MyState](MyState(1)).autoNamed()
    val step2 = WIO.pure[MyState](MyState(2)).autoNamed()
    val step3 = WIO.pure[MyState](MyState(3)).autoNamed()

    val sequence1 = for {
      a <- step1
      b <- step2
      c <- step3
    } yield c
    val sequence2 = step1.flatMap(_ => step2).flatMap(_ => step3)
    // end_dynamic
  }

}
