(ns zarnidict0010.api
  (:require [
             tailrecursion.castra :refer [defrpc]
             ]))
(require '[db0001 :as db])
(require '[datomic.api :as d])

(defrpc get-state []
  {:random (rand-int 100)})


(defrpc get-articles-state []
  (let [r
        (take 100 (d/q
          ;'[:find ?x ?y :where [?e :raw-article/term ?x] [?e :raw-article/content ?y]]
          '[:find ?x :where [?e :raw-article/term ?x]]
          (d/db db/conn)))
        ]
    {:articles
      (mapv #(do {:name %}) (mapv first r))
    }))
