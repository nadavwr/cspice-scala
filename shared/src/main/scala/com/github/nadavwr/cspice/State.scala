package com.github.nadavwr.cspice

/**
  * @param px position along the x axis, in km
  * @param py position along the y axis, in km
  * @param pz position along the z axis, in km
  * @param vx velocity along the x axis, in km
  * @param vy velocity along the y axis, in km
  * @param vz velocity along the z axis, in km
  */
case class State(px: Double, py: Double, pz: Double,
                 vx: Double, vy: Double, vz: Double) {

  override def toString: String = {
    s"""State(px = $px, py = $py, pz = $pz,
       |      vx = $vx, vy = $vy, vz = $vz)
     """.stripMargin
  }
}
