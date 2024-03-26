package it.unibo.modularization.network

trait Network:
  def send[A: HasDefault: Writable](from: String, to: String, payload: A): Unit
  def receive[A: HasDefault: Readable](from: String): A
