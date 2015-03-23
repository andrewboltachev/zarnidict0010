(ns zarnidict0010.rpc
  (:require-macros
    [tailrecursion.javelin :refer [defc defc=]])
  (:require
   [tailrecursion.javelin]
   [tailrecursion.castra :refer [mkremote]]))

(defc state {:random nil})
(defc error nil)
(defc loading [])

(defc= random-number (get state :random))

(defc articles-state {:articles [{:name "no articles"}]})
(defc articles-error nil)
(defc articles-loading [])

(defc= articles (get articles-state :articles))


; init

(def get-state
  (mkremote 'zarnidict0010.api/get-state state error loading)
  )

(def get-articles-state
  (mkremote 'zarnidict0010.api/get-articles-state articles-state articles-error articles-loading)
  )

(defn init []
  (get-state)
  (get-articles-state)
  (js/safeSetInterval get-state 1000)
  (js/safeSetInterval get-articles-state 1000)
  )
