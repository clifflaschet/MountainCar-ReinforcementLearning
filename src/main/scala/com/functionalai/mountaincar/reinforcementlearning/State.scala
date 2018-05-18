package com.functionalai.mountaincar.reinforcementlearning

/**
  * The state of the car is expressed by its position and velocity.
  * @param position
  * @param velocity
  */
case class State(position: Int, velocity: Int)

object State{

  val nrOfPositionBins = 30d
  val nrOfVelocityBins = 15d

  /**
    * Constructs a State from continuous position and velocity values. Both values are discretized using a basic binning approach.
    * @param position
    * @param velocity
    * @return
    */
  def from(position: Double, velocity: Double): State ={

    val minPosition = -1.2
    val maxPosition = 0.6
    val minVelocity = 0.0
    val maxVelocity = 0.07

    val discretizedPosition = ((position - minPosition)/((maxPosition - minPosition) / nrOfPositionBins)).toInt
    val discretizedVelocity = ((velocity - minVelocity)/((maxVelocity - minVelocity) / nrOfVelocityBins)).toInt

    new State(discretizedPosition, discretizedVelocity)
  }
}