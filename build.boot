#!/usr/bin/env boot

#tailrecursion.boot.core/version "2.5.1"

(set-env!
  :project      'zarnidict0010
  :version      "0.1.0-SNAPSHOT"
  :dependencies '[[tailrecursion/boot.task   "2.2.4"]
                  [tailrecursion/hoplon      "5.10.24"]]
  :out-path     "resources/public"
  :src-paths    #{"src/hl" "src/cljs" "src/clj"})

;; Static resources (css, images, etc.):
(add-sync! (get-env :out-path) #{"assets"})

(require '[tailrecursion.hoplon.boot :refer :all]
         '[tailrecursion.castra.task :as c])

(deftask development
  "Build zarnidict0010 for development."
  []
  (comp (watch) (hoplon {:prerender false}) (c/castra-dev-server 'zarnidict0010.api)))

(deftask dev-debug
  "Build zarnidict0010 for development with source maps."
  []
  (comp (watch) (hoplon {:pretty-print true
                         :prerender false
                         :source-map true}) (c/castra-dev-server 'zarnidict0010.api)))

(deftask production
  "Build zarnidict0010 for production."
  []
  (hoplon {:optimizations :advanced}))
