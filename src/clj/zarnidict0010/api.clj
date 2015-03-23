(ns zarnidict0010.api
  (:require [
             tailrecursion.castra :refer [defrpc]
             ]))
(require '[db0001 :as db])
(require '[datomic.api :as d])

(defrpc get-state []
  {:random (rand-int 100)})

(defn positions [x coll]
    (flatten (map-indexed #(if (= %2 x) [%1] []) coll)))

(def m "аӓбвгдеёжзийклмнҥоӧпрстуӱфхцчшщъыӹьэюя")

(defn longer [coll desired-length fill-with] (loop [a coll] (if (= (count a) desired-length) a (recur (conj a fill-with)))))

(defn mari_key_fn [getter]
  (fn [x]
  (let [x (getter x)
        char2key (fn [c]
                   (let [p (positions c (vec m))] (if-not (empty? p) (first p) -1)))]
    (mapv char2key (vec x))
    )
  )
  )

;'[:find ?x ?y :where [?e :raw-article/term ?x] [?e :raw-article/content ?y]]
(defrpc get-articles-state []
  (let [
        r0 (take 15
              (d/q
          '[:find ?x :where [?e :raw-article/term ?x]]
          (d/db db/conn))
              )
        max-len (apply max (map (comp count first) r0))
        r1 (map #(do [
                      (longer ((mari_key_fn first) %) max-len 0)
                      %]) r0)
        r (mapv last (sort-by first r1))
        ]
    {:articles
      (mapv #(do {:name (do %)}) (mapv first r))
    })
  )
