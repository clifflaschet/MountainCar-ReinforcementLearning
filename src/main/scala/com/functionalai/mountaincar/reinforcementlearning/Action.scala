package com.functionalai.mountaincar.reinforcementlearning

/**
  * The AI can choose to do nothing, drive backwards (left), or drive forward (right).
  */
trait Action
case object Left extends Action
case object DoNothing extends Action
case object Right extends Action

object Action{

  def allowedActions(s: State): Seq[Action] = Seq(Left, DoNothing, Right)
}