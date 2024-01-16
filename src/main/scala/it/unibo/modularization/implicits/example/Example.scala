package it.unibo.modularization.implicits.example

import it.unibo.modularization.implicits.{Capability, Host, Module}

trait HighCpu extends Capability
trait HighMemory extends Capability
trait TemperatureSensor extends Capability:
  def sense(): Double

trait EmbeddedDevice extends Host:
  given TemperatureSensor = () => 12.30

trait CloudService extends Host:
  given (HighCpu & HighMemory & TemperatureSensor) = new HighCpu with HighMemory with TemperatureSensor:
    override def sense(): Double = 10.11

class Foo(using c: HighCpu | HighMemory) extends Module[HighCpu | HighMemory, Long, Int]:
  override def apply(v1: Long): Int = c match
    case _: HighCpu => ???
    case _: HighMemory => ???

class Bar(using capabilities: TemperatureSensor) extends Module[TemperatureSensor, Int, Int]:
  override def apply(v1: Int): Int = ???

def baz(unit: Unit): Unit = ???

trait Main extends CloudService, EmbeddedDevice:
  def main(): Unit =
    Foo()
    Bar()
    baz(())