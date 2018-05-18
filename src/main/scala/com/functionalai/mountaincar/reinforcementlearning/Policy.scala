package com.functionalai.mountaincar.reinforcementlearning

import scala.util.Random

/**
  * A policy maps each possible state to an action.
  * An optimal policy maps states to actions in such a way, that when the agent adheres to this policy, maximum reward is obtained.
  */
trait Policy {

  def selectAction(state: State, allowedActions: Seq[Action]): Action
}

/**
  * An epsilon-greedy policy selects the action with the maximum Q-value with probability 1-epsilon, and a random action with probability epsilon.
  * The partially random behavior ensures that the policy does not converge to a local optimum.
  * @param qFunction
  * @param epsilon
  */
class EpsilonGreedyPolicy(qFunction: TabularQFunction, epsilon: Double) extends Policy{

  override def selectAction(state: State, allowedActions: Seq[Action]): Action = {

    //For each possible (state, action) pair, compute the utility using the Q function
    val utilities = allowedActions.map(action => (action, qFunction.utility(state, action)))

    //Find the highest utility
    val maxUtility = utilities.map(_._2).max

    //Make sure to choose randomly when several actions tie in utility
    //This prevents too deterministic behavior and/or getting stuck in local optima
    val actionsWithMaxUtility = utilities.filter(_._2 == maxUtility).map(_._1)

    //Randomly pick one of the actions with highest utility
    val bestAction = Random.shuffle(actionsWithMaxUtility).head

    //With probability epsilon, select a random action. Otherwise, select the best action.
    if(Random.nextDouble() < epsilon)
      Random.shuffle(allowedActions).head
    else
      bestAction
  }
}