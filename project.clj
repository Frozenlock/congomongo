(defproject org.clojars.frozenlock/congomongo
  "2.6.0"
  :description "Clojure-friendly API for MongoDB"
  :url "https://github.com/congomongo/congomongo"
  :mailing-list {:name "CongoMongo mailing list"
                 :archive "https://groups.google.com/forum/?fromgroups#!forum/congomongo-dev"
                 :post "congomongo-dev@googlegroups.com"}
  :license {:name "MIT License"
            :url "http://www.opensource.org/licenses/mit-license.php"
            :distribution :repo}
  :min-lein-version "2.0.0"
  :dependencies [[org.clojure/data.json "2.4.0"]
                 ^:inline-dep [org.mongodb/mongo-java-driver "3.12.10"]
                 [org.clojure/clojure "1.10.1" :scope "provided"]]
  :deploy-repositories {"releases" {:url "https://repo.clojars.org" :creds :gpg}}
  ;; if a :dev profile is added, remember to update :aliases below to
  ;; use it in each with-profile group!
  :profiles {:1.9 {:dependencies [[org.clojure/clojure "1.9.0"]]}
             :1.10 {:dependencies [[org.clojure/clojure "1.10.1"]]}}
  :test-selectors {:default (complement :mongo-4.2+)
                   :mongo-4.2+ :mongo-4.2+}
  :aliases {"test-all" ["with-profile" "default,1.9:default,1.10" "test"]}
  :plugins [[lein-cljfmt "0.8.0"]
            [thomasa/mranderson "0.5.4-fix76"]]

  :mranderson {:project-prefix "congomongo.inlined.deps"})

; lein do clean, inline-deps
; lein with-profile +plugin.mranderson/config install
