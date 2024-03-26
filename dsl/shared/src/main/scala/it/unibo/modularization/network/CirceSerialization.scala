package it.unibo.modularization.network

import io.circe.parser.*
import io.circe.syntax.*
import io.circe.{Decoder, Encoder}

object CirceSerialization:
  given [A : Decoder]: Readable[A] = payload => decode[A](payload).left.map(_.toString)
  given [A : Encoder]: Writable[A] = a => a.asJson.noSpaces
