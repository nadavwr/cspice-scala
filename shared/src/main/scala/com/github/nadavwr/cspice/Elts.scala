package com.github.nadavwr.cspice

/**
  * @param rp    periapsis, in km
  * @param ecc   eccentricity
  * @param inc   inclination, in radians
  * @param lnode longitude of the ascending node, in radians
  * @param argp  argument of perhapsis, in radians
  * @param m0    mean anomaly at epoch, in radians
  * @param t0    the instant at which the state of the body is
  *              specified by the elements, in ephemeris seconds
  *              past J2000
  * @param mu    the gravitational parameter (MG) of the primary body,
  *              in km³/s²
  */
case class Elts(rp: Double,
                ecc: Double,
                inc: Double,
                lnode: Double,
                argp: Double,
                m0: Double,
                t0: Double,
                mu: Double) {
  override def toString: String =
    s"""Elts(rp = $rp /* km, periapsis distance */,
       |     ecc = $ecc /* eccentricity */,
       |     inc = $inc /* rad, inclination */,
       |     lnode = $lnode /* rad, longitude of the ascending node */,
       |     argp = $argp /* rad, argument of periapsis */,
       |     m0 = $m0 /* rad, mean anomaly at epoch */,
       |     t0 = $t0 /* seconds, epoch */,
       |     mu = $mu /* km³/s², gravitational parameter */)
     """.stripMargin
}
