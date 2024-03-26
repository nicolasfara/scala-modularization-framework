package it.unibo.modularization.experiments

import scala.util.Random
import it.unibo.modularization.dsl.Modularization.*
import it.unibo.modularization.network.{HasDefault, Network}
import it.unibo.modularization.network.CirceSerialization.given

class HighCpu
class HighMemory

given HasDefault[Int] with
  def default: Int = 0

def systemSpec(using Network, HasDefault[Int]): ComponentContainer =
  macroSystem:
    val randomComponent = module[HighCpu, Int]("randomComponent"): _ =>
      val res1 = 12 + Random().nextInt()
      val res2 = res1 + Random().nextInt()
      res2 + 1

    val m2 = collectiveModule[HighCpu | HighMemory, Double]("M2"):
      case _: HighCpu => randomComponent.asLocalResult * 12.0
      case _: HighMemory => 13.0

@main def main =
  given network: Network = ???
  val specification = systemSpec
