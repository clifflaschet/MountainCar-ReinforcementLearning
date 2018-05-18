package com.functionalai.mountaincar.reinforcementlearning

/**
  * A transition represents the agent moving from state 'state1' to state 'state2', by performing action 'action'.
  * By doing so, a reward is obtained by the agent.
  * @param state1
  * @param action
  * @param state2
  * @param reward
  */
case class Transition(state1: State, action: Action, state2: State, reward: Double)
