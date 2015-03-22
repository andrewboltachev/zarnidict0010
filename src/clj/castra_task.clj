(ns castra_task
  (:require
    [boot.core        :as core]
    ;[tailrecursion.boot.task.ring   :as r]
    [task_ring :as r]
    [tailrecursion.castra.handler   :as c]))

(core/deftask castra
  "Add the castra handler to the middleware."
  [s specs coll ""]
  (println "and have жопу do you do?")
  (r/ring-task (fn [_] (apply c/castra specs)))
  ;(fn [continue] (fn [fileset] (continue fileset)))
  )

(core/deftask castra-dev-server
  "Creates a server for development with castra. The first argument is
  a quoted namespace or a quoted vector of namespaces with Castra endpoints."
  ;[namespaces & {:keys [port join? key docroot cors?]
  ;    :or {port    3000
  ;         join?   false
  ;         key     "a 16-byte secret"
  ;         docroot (core/get-env :target-path)
  ;         cors?    false}}]
  [n namespaces coll ""]
  (let [
    port    3000
    join?   false
    key     "a 16-byte secret"
    docroot (core/get-env :target-path)
    cors?    false
        ]
    (->> [(r/head)
          (r/dev-mode)
          (if cors? (r/cors #".*localhost.*"))
          (r/session-cookie key)
          (r/files docroot)
          (r/reload)
          (if (coll? namespaces) (castra namespaces) (castra [namespaces]))
          (r/jetty :port port :join? join?)]
        (filter identity)
        (apply comp))
    (println "i'm sorry very well")
    (fn [continue] (fn [fileset] (continue fileset)))
    )
  )
