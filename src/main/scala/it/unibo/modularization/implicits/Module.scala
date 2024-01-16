package it.unibo.modularization.implicits

trait Module[+Capabilities, -Inputs, +Outputs](using Capabilities) extends SimpleModule[Inputs, Outputs]

trait SimpleModule[-I, +O] extends (I => O)
