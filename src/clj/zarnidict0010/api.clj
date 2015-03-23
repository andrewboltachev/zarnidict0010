(ns zarnidict0010.api
  (:require [
             tailrecursion.castra :refer [defrpc]
             ]))
(require '[db0001 :as db])

(defrpc get-state []
  {:random (rand-int 100)})


(defrpc get-articles-state []
  {:articles [
              {:name "1"}
              {:name "20"}
              ]})
