#!/usr/bin/env boot

;#tailrecursion.boot.core/version "2.5.1"

(set-env!
  :project      'zarnidict0010
  :version      "0.1.0-SNAPSHOT"
  :dependencies '[
                  ; WAT
                  [digest "1.4.4"]
                  [com.cemerick/pomegranate "0.3.0"]

                  ;[clj-tagsoup               "0.3.0"]
                  [adzerk/boot-cljs-repl     "0.1.9"]
                  [adzerk/boot-reload        "0.2.6"]
                  [pandeiro/boot-http        "0.6.2"]
                  ;[markdown-clj              "0.9.63"]
                  [adzerk/boot-cljs          "0.0-2814-0"]
                  [tailrecursion/boot-hoplon "0.1.0-SNAPSHOT"]
                  [tailrecursion/hoplon      "6.0.0-SNAPSHOT"]
                  [tailrecursion/castra "2.2.2"]

                  [com.datomic/datomic-pro "0.9.5130"]
                  ]
  ;:out-path     "resources/public"
  :source-paths    #{"src/hl" "src/cljs" "src/clj"}
  :resource-paths #{"resources/assets"}
  :target-path    "resources/public")

;; Static resources (css, images, etc.):
;(add-sync! (get-env :out-path) #{"assets"})

;(require '[tailrecursion.hoplon.boot :refer :all]
;         '[tailrecursion.castra.task :as c])
;(require          '[tailrecursion.castra.task :as c])
(require
  '[adzerk.boot-cljs :refer [cljs]]
  '[adzerk.boot-cljs-repl :refer [cljs-repl start-repl]]
  '[adzerk.boot-reload :refer [reload]]
  '[pandeiro.boot-http :refer [serve]]
  '[tailrecursion.boot-hoplon :refer [haml hoplon prerender html2cljs]]
  '[castra_task :as c]
  ;'[tailrecursion.castra.handler :as c]
  )

;(require '[demo.boot-build :refer :all])

(deftask hello_task
  "Print a friendly greeting."
  []
  (c/castra 'zarnidict0010.api)
  (println "i'm sorry very well")
  (fn [continue] (fn [fileset] (continue fileset)))
  )

(deftask dev
  "Build hoplon.io for local development."
  []
  (comp
    (watch)
    (speak)
    (haml)
    (hoplon)
    (reload)
    (cljs)
    (c/castra-dev-server
      ;'zarnidict0006.api
      )
    ;(serve)
    ;(hello_task)
    ))

(deftask prod
  "Build hoplon.io for production deployment."
  []
  (comp
    (hoplon :pretty-print true)
    (cljs :optimizations :advanced :source-map true)
    (prerender)))

'(deftask development
  "Build zarnidict0010 for development."
  []
  (comp (watch) (hoplon {:prerender false}) (c/castra-dev-server 'zarnidict0010.api)))

'(deftask dev-debug
  "Build zarnidict0010 for development with source maps."
  []
  (comp (watch) (hoplon {:pretty-print true
                         :prerender false
                         :source-map true}) (c/castra-dev-server 'zarnidict0010.api)))
