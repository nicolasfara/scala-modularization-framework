package it.unibo.modularization.module

enum Module[C, +Out]:
  case LocalModule(name: String, logic: C => Out)
  case CollectiveModule(name: String, logic: C => Out)
  
  def moduleName: String = this match
    case LocalModule(name, _) => name
    case CollectiveModule(name, _) => name
