package com.functionalai.mountaincar.reinforcementlearning

/**
  * The AI can choose to do nothing, drive forward, or drive forward with extra effort.
  */
trait Action
case object DoNothing extends Action
case object Forward extends Action
case object StrongForward extends Action

object Action{

  def allowedActions(s: State): Seq[Action] = Seq(DoNothing, Forward, StrongForward)
}