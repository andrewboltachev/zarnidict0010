;(ns zarnidict0005.db0001)
(require
 '[datomic.api :as d]
)

(def uri "datomic:dev://localhost:4334/zarnidict0005")
(println "create database"
  (d/create-database uri)
       )


(def conn (d/connect uri))

(println "conn" conn)



@(d/transact conn [{:db/id #db/id [:db.part/db]
  :db/ident :raw-article/term
  :db/valueType :db.type/string
  :db/unique :db.unique/identity
  :db/cardinality :db.cardinality/one
  :db.install/_attribute :db.part/db
  }
])

@(d/transact conn [{:db/id #db/id [:db.part/db]
  :db/ident :raw-article/content
  :db/valueType :db.type/string
  :db/cardinality :db.cardinality/one
  :db.install/_attribute :db.part/db
  }
])

