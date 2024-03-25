package it.unibo.modularization.network

trait Readable[A]:
  def read(payload: String): Either[String, A]

trait Writable[A]:
  def write(value: A): String

trait Network[M[_]]:
  def send[A](from: String, to: String, payload: A)(using Writable[A]): M[Unit]
  def receive[A](from: String)(using Readable[A]): M[Either[String, A]]
