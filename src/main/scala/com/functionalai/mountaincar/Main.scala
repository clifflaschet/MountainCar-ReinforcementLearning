package com.functionalai.mountaincar

import com.functionalai.mountaincar.reinforcementlearning._

object Main extends App {

  //Initialize the car and UI
  val car = new MountainCar
  val window = new MountainCarWindow(car)

  //Initialize the Q-learning agent
  val qLearningAgent = new QLearningAgent

  //Show the performance of the agent after 0 episodes
  train(qLearningAgent, 1, 250, true)

  //Train the agent by running a number of training episodes
  train(qLearningAgent, 100, 500, false)

  //Show the performance of the agent after 100 episodes
  Thread.sleep(2000)
  train(qLearningAgent, 1, 250, true)

  //Train the agent by running a number of training episodes
  train(qLearningAgent, 200, 500, false)

  //Show the performance of the agent after 300 episodes
  Thread.sleep(2000)
  train(qLearningAgent, 1, 250, true)

  //Train the agent by running a number of training episodes
  train(qLearningAgent, 400, 500, false)

  //Show the performance of the agent after 700 episodes
  Thread.sleep(2000)
  train(qLearningAgent, 1, 250, true)

  //Train the agent by running a number of training episodes
  train(qLearningAgent, 3000, 500, false)

  //Show the performance of the agent after 3700 episodes
  Thread.sleep(2000)
  train(qLearningAgent, 1, 250, true)

  def train(agent: Agent, remainingEpisodes: Int, maxActionsPerEpisode: Int, render: Boolean): Agent = {

    //Initialize the car in a random position
    car.randomInit()
    var currentState = car.getState

    //Initialize tracking variables
    var episodeEnded = false
    var cumulativeReward = 0d
    var numberOfActionsPerformed = 0

    while (!episodeEnded) {

      //Render if needed
      if (render) {
        Thread.sleep(1)
        window.paintCar
      }

      //Stop the episode if it exceeds the maximum number of actions
      if (numberOfActionsPerformed >= 500) {
        episodeEnded = true
      }

      //Ask the agent to select an action
      val action = agent.selectAction(currentState)

      //Apply the action to the car
      car.apply(action match{
        case Left => 0
        case DoNothing => 1
        case Right => 2
      })

      //Share the effect of the action with the agent
      agent.observeEffect(Transition(currentState, action, car.getState, car.getReward))

      //Update the current state
      currentState = car.getState

      //Update tracking variables
      cumulativeReward += car.getReward
      numberOfActionsPerformed += 1
      episodeEnded = (numberOfActionsPerformed >= maxActionsPerEpisode) || car.done()
    }

    println(s"Goal was achieved, with reward $cumulativeReward at episode $remainingEpisodes.")

    if(remainingEpisodes > 1)
      train(agent, remainingEpisodes - 1, maxActionsPerEpisode, render)
    else
      agent
  }
}
