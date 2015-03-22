(ns castra_task
  (:require
    [ring.adapter.jetty             :as jetty]
    [boot.core        :as core]
    ;[tailrecursion.boot.task.ring   :as r]
    [task_ring :as r]
    [tailrecursion.castra.handler   :as c]


   [ring.adapter.jetty             :as jetty]
   [ring.middleware.cors           :as cors]
   [ring.middleware.session        :as session]
   [ring.middleware.session.cookie :as cookie]
   [ring.middleware.reload         :as reload]
   [ring.middleware.head           :as head]
   [ring.middleware.file           :as file]
   [ring.middleware.file-info      :as file-info]))


(def server (atom nil))

(defn handler404 [request]
  {:status 404
   :headers {"Content-Type" "text/html"}
   :body "this is 404"})

(core/deftask castra-dev-server
  (println "111")
  (let [
    port    3000
    join?   false
    key     "a 16-byte secret"
    docroot (core/get-env :target-path)
    cors?    false
        ]
    (->> [
          ;(r/head)
          ;(r/dev-mode)
          ;(if cors? (r/cors #".*localhost.*"))
          ;(r/session-cookie key)
          ;(r/files docroot)
          ;(r/reload)
          ;(if (coll? namespaces) (castra namespaces) (castra [namespaces]))
          ;(do (println "do it on port" port ) (r/jetty :port port :join? join?))
          (do
            (swap! server
              #(or % (-> (->
                           ;handler404
                           (c/castra 'zarnidict0010.api)
                           (file/wrap-file docroot)
                           (file-info/wrap-file-info)
                           );(@middleware handle-404)
                  (jetty/run-jetty {:port port :join? join?}))))
            identity
            )
          ]
        (filter identity)
        (apply comp))
    (println "i'm sorry very well")
    (fn [continue] (fn [fileset] (continue fileset)))
    )
  )
