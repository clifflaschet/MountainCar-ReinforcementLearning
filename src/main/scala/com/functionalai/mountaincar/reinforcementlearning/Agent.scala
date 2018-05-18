package com.functionalai.mountaincar.reinforcementlearning

/**
  * An agent observes its environment and decides to perform actions over time.
  * The actions aim to achieve a specific goal.
  */
trait Agent{

  /**
    * Given a state, decide which action to perform.
    * @param s
    * @return
    */
  def selectAction(s: State): Action

  /**
    * Observes the effect of performing an action in state 1, transitioning to state 2.
    * @param transition
    */
  def observeEffect(transition: Transition)
}

/**
  * Q-Learning is an off-policy temporal difference reinforcement learning algorithm.
  */
class QLearningAgent extends Agent{

  val qFunction = new TabularQFunction(0.1, 0.9)
  val policy = new EpsilonGreedyPolicy(qFunction, 0.1d)

  def selectAction(s: State): Action = {

    //Retrieve the allowed actions
    val allowedActions = Action.allowedActions(s)

    //Select the best action using the policy
    policy.selectAction(s, allowedActions)
  }

  override def observeEffect(transition: Transition): Unit ={

    //Add transition to the Q function
    qFunction.add(transition)
  }

}