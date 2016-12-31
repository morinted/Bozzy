package bozzy

import java.util.{Timer, TimerTask}

import bozzy.controllers.MainDictionary

import scalafx.scene.control.ProgressBar

/**
  * Created by ted on 2016-04-06.
  */
object Progress {
  var progressIndicator = new ProgressBar

  def addProgressIndicator(pb: ProgressBar) {
    progressIndicator = pb
  }

  def startProgress(path: String) {
    val task = new javafx.concurrent.Task[Boolean] {
      override def call(): Boolean = {
        progressIndicator.visible = true
        var inc = 0.0
        val timer = new Timer().schedule(
          new TimerTask() {
            override def run() {
              if (inc <= 1.15) {
                progressIndicator.progress = inc
                inc = inc + 0.15
              } else {
                cancel()
              }
            }
          }, 0, 140)
        Thread.sleep(1000)
        return true
      }
      override def succeeded(): Unit = {
        MainDictionary.addDictionary(path)
        progressIndicator.visible = false
      }
    }
    val t = new Thread(task, "Add Dictionary Task")
    t.setDaemon(true)
    t.start()
  }
}
