package com.github.nadavwr

import ch.jodersky.jni.nativeLoader

package object cspice extends CSpiceFunctions {

  /** @inheritdoc
    */
  override def conics(elts: Elts, et: Double): State = {
    val stateArray = binding.conics_c_wrapper(eltsToArray(elts), et)
    stateFromArray(stateArray)
  }


  /** @inheritdoc
    */
  override def oscelt(state: State, et: Double, mu: Double): Elts = {
    val eltsArray = binding.oscelt_c_wrapper(stateToArray(state), et, mu)
    eltsFromArray(eltsArray)
  }

  private type EltsValue = Array[Double]
  def eltsToArray(elts: Elts): EltsValue = elts.productIterator.map(_.asInstanceOf[Double]).toArray
  def eltsFromArray(array: EltsValue): Elts = {
    Elts(array(0), array(1), array(2), array(3), 
         array(4), array(5), array(6), array(7))
  }

  private type StateValue = Array[Double]
  def stateToArray(state: State): StateValue = state.productIterator.map(_.asInstanceOf[Double]).toArray
  def stateFromArray(array: StateValue): State =
    State(array(0), array(1), array(2),
          array(3), array(4), array(5))

  @nativeLoader("cspice-scala0")
  private object binding {
    @native def conics_c_wrapper(elts: EltsValue, et: Double): StateValue
    @native def oscelt_c_wrapper(state: StateValue, et: Double, mu: Double): EltsValue
  }
}
