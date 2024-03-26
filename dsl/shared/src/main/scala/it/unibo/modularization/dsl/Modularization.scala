package it.unibo.modularization.dsl

import it.unibo.modularization.module.Module
import it.unibo.modularization.module.Module.{CollectiveModule, LocalModule}
import it.unibo.modularization.network.{HasDefault, Network, Readable}

object Modularization:
  final case class ComponentContainer(components: Map[String, Module[?, ?]])

  final class ComponentScope:
    def add[C, Out](m: Module[C, Out]): Unit = ???

  def macroSystem(init: ComponentScope ?=> Unit): ComponentContainer =
    given componentScope: ComponentScope = ComponentScope()
    val _ = init
    ComponentContainer(Map.empty)

  def module[C, Out](name: String)(init: C => Out): LocalModule[C, Out] = LocalModule(name, init)

  def collectiveModule[C, Out](name: String)(init: C => Out): CollectiveModule[C, Out] = CollectiveModule(name, init)

  extension [C, Out: Readable: HasDefault](module: Module[C, Out])
    def asLocalResult(using network: Network): Out =
      network.receive(module.moduleName)
