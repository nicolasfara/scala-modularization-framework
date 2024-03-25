package it.unibo.modularization.typesystem

type HostCapabilities[A] = Host { type Capabilities <: A }
