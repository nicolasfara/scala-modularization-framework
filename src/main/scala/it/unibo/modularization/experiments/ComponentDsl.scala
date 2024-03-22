package it.unibo.modularization.experiments

import scala.util.Random

trait Capability

enum Module[C <: Capability, +Out]:
  case LocalModule(name: String, logic: C => Out)
  case CollectiveModule(name: String, logic: C => Out)

trait Network:
  def send[T](id: String)(msg: T): Unit
  def receive[T](id: String): T

final case class ComponentContainer(components: Map[String, Module[?, ?]])

final class ComponentScope:
  def add[C <: Capability, Out](m: Module[C, Out]): Unit = ???

type Composable = ComponentScope ?=> Unit

def macroSystem(init: Composable): ComponentContainer =
  given componentScope: ComponentScope = ComponentScope()
  val _ = init
  ComponentContainer(Map.empty)

def module[C <: Capability, Out](name: String)(init: C => Out)(using Network): Module.LocalModule[C, Out] =
  Module.LocalModule(name, init)

def collectiveModule[C <: Capability, Out](name: String)(init: C => Out)(using Network): Module.CollectiveModule[C, Out] =
  Module.CollectiveModule(name, init)

extension [C <: Capability, Out](m: Module[C, Out]) def asLocalResult(using Network): Out = ???

class HighCpu extends Capability
class HighMemory extends Capability

@main def main =
  given network: Network = ???
  val specification = macroSystem:
    val randomComponent = module[HighCpu, Int]("randomComponent"): _ =>
      val res1 = 12 + Random().nextInt()
      val res2 = res1 + Random().nextInt()
      res2 + 1

    val m2 = collectiveModule[HighCpu | HighMemory, Double]("M2"):
      case _: HighCpu    => randomComponent.asLocalResult * 12.0
      case _: HighMemory => 13.0
