package com.github.nadavwr.cspice

trait CSpiceFunctions {

  /**
    * @param elts orbital elements,
    *             see [[com.github.nadavwr.cspice.Elts Elts]]
    * @param et   the time at which the state of the orbiting body
    *             is to be determined, in ephemeris seconds past J2000
    * @return the state (position and velocity) of the body
    *         at the specified time,
    *         see [[com.github.nadavwr.cspice.State State]]
    */
  def conics(elts: Elts, et: Double): State

  /**
    * @param state the state (position and velocity) of the body
    *              at the specified time,
    *              see [[com.github.nadavwr.cspice.State State]]
    * @param et    the epoch of the input state, in ephemeris seconds
    *              past J2000
    * @param mu    the gravitational parameter (MG) of the primary body,
    *              in km³/s²
    * @return      orbital elements,
    *              see [[com.github.nadavwr.cspice.Elts Elts]]
    */
  def oscelt(state: State, et: Double, mu: Double): Elts

}
