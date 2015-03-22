(ns demo.boot-build
  (:require [boot.core :as core]
            [boot.task.built-in :as task]
            [tailrecursion.castra.handler :as c]
            ))

(core/deftask hello_task
  "Print a friendly greeting."
  []
  (c/castra 'zarnidict0006.api)
  (println "i'm sorry very well")
  (fn [continue] (fn [fileset] (continue fileset)))
  )
