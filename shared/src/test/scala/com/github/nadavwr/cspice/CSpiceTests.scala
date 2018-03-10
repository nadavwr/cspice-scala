package com.github.nadavwr.cspice

import utest._
import scala.math._

object CSpiceTests extends TestSuite {
  val tests: Tests = Tests {

    val earthOrbitalElements = Elts(
      rp = 1.470980735498914E8/*km*/,
      ecc = 0.01671022,
      inc = 0.00005*2*Pi/360,
      lnode = -11.26064*2*Pi/360,
      argp = 114.20783*2*Pi/360,
      m0 = 0,
      t0 = 0,
      mu = 1.32712440018e11)

    val state0 = conics(earthOrbitalElements, 0)

    val earthOrbitalPeriod = 365.256363004*86400.0025

    "the earth should rotate from one perihelion to the next" - {
      val state1 = conics(earthOrbitalElements, earthOrbitalPeriod)
      val dx = (state1.px - state0.px) / state0.px
      val dy = (state1.py - state0.py) / state0.py
      val dz = (state1.pz - state0.pz) / state0.pz
      val error = sqrt(pow(dx, 2) + pow(dy, 2) + pow(dz, 2))
      val tolerance = 1e-4

      assert(error <= tolerance)
    }

    "the earth should reach aphelion at half a year" - {
      val state1 = conics(earthOrbitalElements, earthOrbitalPeriod/2)
      val aphelion = sqrt(pow(state1.px, 2) + pow(state1.py, 2) + pow(state1.pz, 2))
      val expected = 152100000
      val error = (aphelion - expected) / expected
      val tolerance = 1e-4

      assert(error <= tolerance)
    }
  }
}
