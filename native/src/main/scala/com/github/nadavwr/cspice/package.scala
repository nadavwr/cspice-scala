package com.github.nadavwr

import scala.scalanative.native._

package object cspice extends CSpiceFunctions {

  /** @inheritdoc
    */
  override def conics(elts: Elts, et: Double): State = {
    val statePtr = stackalloc[StateValue]
    Zone { implicit alloc =>
      binding.conics_c(eltsToPtr(elts), et, statePtr)
    }
    stateFromPtr(statePtr)
  }

  /** @inheritdoc
    */
  override def oscelt(state: State, et: Double, mu: Double): Elts = {
    val eltsPtr = stackalloc[EltsValue]
    Zone { implicit alloc =>
      binding.oscelt_c(stateToPtr(state), et, mu, eltsPtr)
    }
    eltsFromPtr(eltsPtr)
  }


  private type EltsValue = CArray[CDouble, Nat._8]
  private def eltsToPtr(elts: Elts)
                       (implicit zone: Zone): Ptr[EltsValue] = {
    val ptr = zone.alloc(sizeof[CDouble]*8).cast[Ptr[EltsValue]]
    !ptr._1 = elts.rp
    !ptr._2 = elts.ecc
    !ptr._3 = elts.inc
    !ptr._4 = elts.lnode
    !ptr._5 = elts.argp
    !ptr._6 = elts.m0
    !ptr._7 = elts.t0
    !ptr._8 = elts.mu
    ptr
  }
  private def eltsFromPtr(ptr: Ptr[EltsValue]): Elts =
    Elts(!ptr._1, !ptr._2, !ptr._3, !ptr._4,
         !ptr._5, !ptr._6, !ptr._7, !ptr._8)
  
  private type StateValue = CArray[CDouble, Nat._6]
  private def stateToPtr(state: State)
                        (implicit zone: Zone): Ptr[StateValue] = {
    val ptr = zone.alloc(sizeof[CDouble]*6).cast[Ptr[StateValue]]
    !ptr._1 = state.px
    !ptr._2 = state.py
    !ptr._3 = state.pz
    !ptr._4 = state.vx
    !ptr._5 = state.vy
    !ptr._6 = state.vz
    ptr
  }
  private def stateFromPtr(ptr: Ptr[StateValue]): State =
    State(!ptr._1, !ptr._2, !ptr._3, !ptr._4, !ptr._5, !ptr._6)

  @link("cspice")
  @extern
  private object binding {
    def conics_c(elts: Ptr[EltsValue],
                 et: CDouble,
                 state: Ptr[StateValue]): Unit = extern

    def oscelt_c(state: Ptr[StateValue],
                 et: CDouble, mu: CDouble,
                 elts: Ptr[EltsValue]): Unit = extern
  }
}

