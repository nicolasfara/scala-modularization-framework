package it.unibo.modularization.network

trait Readable[+A]:
  def read(payload: String): Either[String, A]

trait Writable[-A]:
  def write(value: A): String
