#!/usr/bin/env boot

(set-env!
  :project      'zarnidict0010
  :version      "0.1.0-SNAPSHOT"
  :dependencies '[
                  [adzerk/boot-cljs-repl     "0.1.9"]
                  [adzerk/boot-reload        "0.2.6"]
                  [pandeiro/boot-http        "0.6.2"]
                  [adzerk/boot-cljs          "0.0-2814-0"]
                  [tailrecursion/boot-hoplon "0.1.0-SNAPSHOT"]
                  [tailrecursion/hoplon      "6.0.0-SNAPSHOT"]
                  [tailrecursion/castra "2.2.2"]

                  [com.datomic/datomic-pro "0.9.5130"]
                  ]
  :source-paths    #{"src/hl" "src/cljs" "src/clj"}
  :resource-paths #{"resources/assets"}
  :target-path    "resources/public")

(require
  '[adzerk.boot-cljs :refer [cljs]]
  '[adzerk.boot-cljs-repl :refer [cljs-repl start-repl]]
  '[adzerk.boot-reload :refer [reload]]
  '[pandeiro.boot-http :refer [serve]]
  '[tailrecursion.boot-hoplon :refer [haml hoplon prerender html2cljs]]
  '[castra_task :as c]
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
      )
    ))
