(ns somnium.congomongo.test.coerce
  (:use somnium.congomongo.coerce)
  (:use clojure.test)
  (:import (com.mongodb BasicDBObject) ))

;; insertion order is important for BasicDBObjects, since they extend
;; LinkedHashMap.  The only way to test this is to compare their JSON
;; serializations, accessible through .toString
(deftest test-coerce-index-fields
  (are [fields bdo] (is (= (.toString (coerce-index-fields fields))
                           (.toString bdo)))
       [:a :b :c] (doto (BasicDBObject.)
                    (.put "a" 1)
                    (.put "b" 1)
                    (.put "c" 1))
       [:c :b :a] (doto (BasicDBObject.)
                    (.put "c" 1)
                    (.put "b" 1)
                    (.put "a" 1))
       [:a [:b -1] :c] (doto (BasicDBObject.)
                         (.put "a" 1)
                         (.put "b" -1)
                         (.put "c" 1))
       [:c :b [:a -1]] (doto (BasicDBObject.)
                         (.put "c" 1)
                         (.put "b" 1)
                         (.put "a" -1))))
