(ns zarnidict0010.api
  (:require [tailrecursion.castra :refer [defrpc]]))

(defrpc get-state []
  {:random (rand-int 100)})
