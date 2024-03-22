package it.unibo.modularization.implicits

trait Module[-I, +O] extends (I => O)

trait ModuleWithCapability[+Capabilities, -Inputs, +Outputs](using Capabilities) extends Module[Inputs, Outputs]

