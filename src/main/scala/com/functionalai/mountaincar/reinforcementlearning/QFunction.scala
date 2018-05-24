package com.functionalai.mountaincar.reinforcementlearning

import scala.collection.mutable.HashMap

/**
  * A Q function provides utilities (i.e. expected cumulative rewards) for any state, action pair '(s1, a)'.
  * The larger the utility, the more cumulative reward is expected to be obtained in the following states after performing 'a' in 's1'.
  */
trait QFunction{

  /**
    * Adds an observed transition to the Q function.
    * @param transition
    */
  def add(transition: Transition): Unit

  /**
    * Indicates the utility for the given state, action pair.
    * @param s
    * @param a
    */
  def utility(s: State, a: Action): Double

}

/**
  * A tabular Q function provides utilities by tracking past obtained utilities in a table structure.
  *
  * @param learningRate
  * @param gamma
  */
class TabularQFunction(learningRate: Double, gamma: Double) extends QFunction {

  //The table of utilities
  val utilities = HashMap.empty[(State, Action), Double]

  def utility(state: State, action: Action): Double = {

    //Retrieve the utility of the (state, action) pair from the table.
    //If it doesn't exist, add it with a random initial value and return this value
    utilities.get((state, action)) match{

      case Some(utility) => utility
      case None => {
        utilities.put((state, action), 1d)
        1d
      }
    }

  }

  def add(t: Transition): Unit = {

    //Calculate the utility of (s1, a)
    val s1Utility = utility(t.state1, t.action)

    //Retrieve the allowed actions for s2
    val s2AllowedActions = Action.allowedActions(t.state2)

    //For each (s2, a) pair, compute the utility
    val s2Utilities = s2AllowedActions.map(allowedAction => utility(t.state2, allowedAction))

    //Retrieve the best utility in s2
    val bestS2Utility = s2Utilities.max

    //Update s1 using the Bellman equation (temporal difference learning)
    val updatedUtility = s1Utility + learningRate * ((t.reward + gamma * bestS2Utility) - s1Utility)
    utilities.put((t.state1, t.action), updatedUtility)

  }
}